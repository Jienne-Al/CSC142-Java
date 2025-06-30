/**
 * MosaicFilter applies a pixelation effect to an image by dividing it into
 * fixed-size blocks (BLOCK_SIZE x BLOCK_SIZE pixels) and replacing each block with its average color.
 * 
 * This static effect simulates a low-resolution or mosaic look, commonly used for
 * artistic purposes, privacy masking, or retro-style graphics.
 */
public class MosaicFilter implements Filter {

    private static final int BLOCK_SIZE = 5;

    /**
     * @param pi The PixelImage to be filtered.
     */
    @Override
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData();

        // Go through the image in BLOCK_SIZE x BLOCK_SIZE neighborhoods
        for (int row = 0; row <= data.length - BLOCK_SIZE; row += BLOCK_SIZE) {
            for (int col = 0; col <= data[0].length - BLOCK_SIZE; col += BLOCK_SIZE) {
                int totalRed = 0;
                int totalGreen = 0;
                int totalBlue = 0;

                // Get the total color values in the block
                for (int i = 0; i < BLOCK_SIZE; i++) {
                    for (int j = 0; j < BLOCK_SIZE; j++) {
                        Pixel p = data[row + i][col + j];
                        totalRed += p.red;
                        totalGreen += p.green;
                        totalBlue += p.blue;
                    }
                }

                // Calculate the average color (BLOCK_SIZE * BLOCK_SIZE pixels)
                int blockPixelCount = BLOCK_SIZE * BLOCK_SIZE;
                int avgRed = totalRed / blockPixelCount;
                int avgGreen = totalGreen / blockPixelCount;
                int avgBlue = totalBlue / blockPixelCount;

                // Fill the block with the average color
                for (int i = 0; i < BLOCK_SIZE; i++) {
                    for (int j = 0; j < BLOCK_SIZE; j++) {
                        data[row + i][col + j] = new Pixel(avgRed, avgGreen, avgBlue);
                    }
                }
            }
        }
        // Apply the effect to the image
        pi.setData(data);
    }
}
