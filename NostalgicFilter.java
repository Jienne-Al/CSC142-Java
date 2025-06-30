import java.util.Random;
/**
 * A filter that simulates a nostalgic, vintage effect by combining a soft blur,
 * sepia tone, light desaturation, and subtle grain.
 */

public class NostalgicFilter implements Filter {
    private static final int GRAIN_RANGE = 10;
    private static final Random rand = new Random();
    /**
     * This utilizes the gaussian blur weights but in 5x5 neighborhood
     */
    private static final int[][] nostalgic = {
        {1, 1, 1, 1, 1},
        {1, 2, 2, 2, 1},
        {1, 2, 4, 2, 1},
        {1, 2, 2, 2, 1},
        {1, 1, 1, 1, 1}
    };
  
    /**
     * @param pi The PixelImage to be filtered.
     */
    @Override
    public void filter(PixelImage pi) {
        Pixel[][] original = pi.getData();
        Pixel[][] blurred = apply5x5Blur(original, nostalgic);
        pi.setData(blurred);

        Pixel[][] data = pi.getData();
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[0].length; col++) {
                Pixel p = data[row][col];

                // Apply sepia tone
                int r = (int)(0.393 * p.red + 0.769 * p.green + 0.189 * p.blue);
                int g = (int)(0.349 * p.red + 0.686 * p.green + 0.168 * p.blue);
                int b = (int)(0.272 * p.red + 0.534 * p.green + 0.131 * p.blue);

                // Light desaturation
                r = (r + p.red) / 2;
                g = (g + p.green) / 2;
                b = (b + p.blue) / 2;

                // Add grain
                r += rand.nextInt(GRAIN_RANGE * 2 + 1) - GRAIN_RANGE;
                g += rand.nextInt(GRAIN_RANGE * 2 + 1) - GRAIN_RANGE;
                b += rand.nextInt(GRAIN_RANGE * 2 + 1) - GRAIN_RANGE;

                // Clamp and apply
                data[row][col] = new Pixel(
                    PixelImage.color(r),
                    PixelImage.color(g),
                    PixelImage.color(b)
                );
            }
        }

        pi.setData(data);
    }

    private Pixel[][] apply5x5Blur(Pixel[][] input, int[][] kernel) {
        int height = input.length;
        int width = input[0].length;
        Pixel[][] output = new Pixel[height][width];

        int kernelSum = 0; 

        // Loop through each value in the kernel to calculate the sum
        for (int i = 0; i < kernel.length; i++) {
        	for (int j = 0; j < kernel[i].length; j++) {
        		kernelSum += kernel[i][j];
        	}
        }

        if (kernelSum == 0) {
        	kernelSum = 1;
        }

        // Copy borders as-is
        for (int row = 0; row < height; row++)
            for (int col = 0; col < width; col++)
                output[row][col] = input[row][col];

        // Apply blur to inner area
        for (int row = 2; row < height - 2; row++) {
            for (int col = 2; col < width - 2; col++) {
                int r = 0, g = 0, b = 0;

                for (int i = -2; i <= 2; i++) {
                    for (int j = -2; j <= 2; j++) {
                        Pixel p = input[row + i][col + j];
                        int weight = kernel[i + 2][j + 2];
                        r += p.red * weight;
                        g += p.green * weight;
                        b += p.blue * weight;
                    }
                }

                output[row][col] = new Pixel(
                    PixelImage.color(r / kernelSum),
                    PixelImage.color(g / kernelSum),
                    PixelImage.color(b / kernelSum)
                );
            }
        }

        return output;
    }
}
