/**
 * A filter that simulates a thermal camera effect by mapping pixel brightness to heat colors.
 * Implements the Filter interface.
 * 
 * This filter calculates the luminance (brightness) of each pixel and assigns colors 
 * ranging from dark blue (cold) to white (very hot) based on the brightness level,
 * creating a thermal heat map effect.
 */
public class ThermalFilter implements Filter {

    // Constants for the relative luminance of RGB components (human eye sensitivity)
    private static final double RED_LUMINANCE = 0.299;
    private static final double GREEN_LUMINANCE = 0.587;
    private static final double BLUE_LUMINANCE = 0.114;

    // Brightness thresholds for thermal mapping
    private static final int THRESHOLD_DARK_BLUE = 50;
    private static final int THRESHOLD_BLUE = 100;
    private static final int THRESHOLD_YELLOW = 150;
    private static final int THRESHOLD_ORANGE = 200;

    // Intensity levels
    private static final int INTENSITY_MIN = 0;
    private static final int INTENSITY_MODERATE = 128;
    private static final int INTENSITY_MAX = 255;

    /**
     * @param pi The PixelImage to be filtered.
     */
    @Override
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData();  // Get all pixels from the image

        // Iterate over every pixel in the image
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[0].length; col++) {
                Pixel p = data[row][col];

                // Calculate brightness using weighted sum of RGB (luminance)
                int gray = (int)(RED_LUMINANCE * p.red + GREEN_LUMINANCE * p.green + BLUE_LUMINANCE * p.blue);

                // Map brightness to thermal colors
                if (gray < THRESHOLD_DARK_BLUE) {
                    // Dark blue for cold regions
                    data[row][col] = new Pixel(INTENSITY_MIN, INTENSITY_MIN, INTENSITY_MODERATE);
                } else if (gray < THRESHOLD_BLUE) {
                    // Blue for cool regions
                    data[row][col] = new Pixel(INTENSITY_MIN, INTENSITY_MIN, INTENSITY_MAX);
                } else if (gray < THRESHOLD_YELLOW) {
                    // Yellow for warm regions
                    data[row][col] = new Pixel(INTENSITY_MAX, INTENSITY_MAX, INTENSITY_MIN);
                } else if (gray < THRESHOLD_ORANGE) {
                    // Orange for hot regions
                    data[row][col] = new Pixel(INTENSITY_MAX, INTENSITY_MODERATE, INTENSITY_MIN);
                } else {
                    // White for very hot regions
                    data[row][col] = new Pixel(INTENSITY_MAX, INTENSITY_MAX, INTENSITY_MAX);
                }
            }
        }
        // Apply the effect to the image
        pi.setData(data);  // Update the image with new thermal colors
    }
}
