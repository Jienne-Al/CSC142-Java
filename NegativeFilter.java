/**
 * Filter that inverts the colors of the image (produces a negative).
 */
public class NegativeFilter implements Filter {
    /**
     * @param pi The PixelImage to be filtered.
     */
    @Override
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData();

        for (int row = 0; row < pi.getHeight(); row++) {
            for (int col = 0; col < pi.getWidth(); col++) {
                
                Pixel p = data[row][col];

                // Invert each RGB component
                p.red = 255 - p.red;
                p.green = 255 - p.green;
                p.blue = 255 - p.blue;
            }
        }
        // Apply the effect to the image
        pi.setData(data);
    }
}