/**
 * Makes the image look wavy by shifting pixels left and right in each row.
 * It uses a sine wave to decide how much to move each row’s pixels.
 * 
 * The bigger the amplitude, the stronger the wave.
 * The wavelength controls how often the waves repeat vertically.
 */
public class RippleFilter implements Filter {
    // How many rows per wave cycle (smaller means more waves)
    private static final int WAVELENGTH = 8;

    // How far pixels move side to side
    private static final int AMPLITUDE = 10;

    // Whether to wrap pixels around edges so the ripple looks continuous
    private boolean wrapEdges = true;

    /**
     * @param pi The PixelImage to be filtered.
     */
    @Override
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData();
        Pixel[][] result = new Pixel[data.length][data[0].length];

        for (int row = 0; row < data.length; row++) {
            // calculate how much to shift this row’s pixels
            int offset = (int)(AMPLITUDE * Math.sin((double) row / WAVELENGTH));

            for (int col = 0; col < data[0].length; col++) {
                int newCol = col + offset;

                if (wrapEdges) {
                    // wrap around to the other side if needed
                    newCol = (newCol + data[0].length) % data[0].length;
                } else {
                    // clamp newCol to stay within image borders using PixelImage.coordinate
                    newCol = PixelImage.coordinate(newCol, data[0].length - 1);
                }

                result[row][col] = data[row][newCol];
            }
        }
        // Apply the effect to the image
        pi.setData(result);
    }
}
