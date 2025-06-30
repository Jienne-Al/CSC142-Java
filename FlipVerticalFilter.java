/**
 * Filter that flips the image vertically.
 */
public class FlipVerticalFilter implements Filter {

    /**
     * @param pi The PixelImage to be filtered.
     */
    @Override
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData();  // Get the pixel data array
        
        // Loop through only half the rows to avoid double swapping
        for (int row = 0; row < pi.getHeight() / 2; row++) {
            for (int col = 0; col < pi.getWidth(); col++) {
            // Swap the pixel at (row, col) with the pixel at the corresponding
                // position mirrored vertically (height - row - 1, col)
                Pixel temp = data[row][col];
                data[row][col] = data[pi.getHeight() - row - 1][col];
                data[pi.getHeight() - row - 1][col] = temp;
            }
        }
        // Apply the effect to the image
        pi.setData(data);
    }
}