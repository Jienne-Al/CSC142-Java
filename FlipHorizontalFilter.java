/**
 * Filter that flips the image horizontally. This class is COMPLETE. Don't
 * change it. But model your other classes (such as FlipVerticalFilter) after
 * it.
 */
public class FlipHorizontalFilter implements Filter {

    /**
     * @param pi The PixelImage to be filtered.
     */
    @Override
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData();  // Get pixel data array

        // Loop through each row of the image
        for (int row = 0; row < pi.getHeight(); row++) {
            // Only loop through half the columns to avoid double swapping
            for (int col = 0; col < pi.getWidth() / 2; col++) {
                // Swap pixel at current column with pixel at symmetrical column on the right
                Pixel temp = data[row][col];
                data[row][col] = data[row][pi.getWidth() - col - 1];
                data[row][pi.getWidth() - col - 1] = temp;
            }
        }
        // Apply the effect to the image
        pi.setData(data);
    }
}
