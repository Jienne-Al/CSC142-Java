/**
 * A filter that brightens the image by a default percentage (e.g., 30%).
 */
public class BrightenFilter implements Filter {
    // Default brighten percentage (30%)
    private static final int DEFAULT_PERCENTAGE = 30;

    /**
     * @param pi The PixelImage to be filtered.
     */
    @Override
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData(); // Get pixel data from image
        
        // Calculate amount to brighten each color component by (30% of 255)
        int amount = (int)(DEFAULT_PERCENTAGE * 255 / 100.0);

        // Loop through every pixel in the image
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[0].length; col++) {
                Pixel p = data[row][col];
                
                // Brighten each color component by adding the amount,
                // but make sure it doesn't go over the maximum value (255)
                int r = Math.min(255, p.red + amount);
                int g = Math.min(255, p.green + amount);
                int b = Math.min(255, p.blue + amount);
                
                // Replace pixel with new brightened pixel
                data[row][col] = new Pixel(r, g, b);
            }
        }
        // Apply the effect to the image
        pi.setData(data);
    }
}