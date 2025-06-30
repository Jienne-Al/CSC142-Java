import java.util.Random;

/**
 * This filter applies a pixel swap effect by swapping each pixel with another pixel
 * at a random offset within a maximum swap distance.
 * It demonstrates concepts related to coordinate translation and randomness,
 * where pixels move within a neighborhood defined by swapDistance.
 */
public class PixelSwapFilter implements Filter {
    // Maximum distance a pixel can be swapped horizontally or vertically (default 3)
    private int swapDistance = 3;

    // Random swapping
    private Random rand = new Random();

    /**
     * @param pi The PixelImage to be filtered.
     */
    @Override
    public void filter(PixelImage pi) {
        Pixel[][] data = pi.getData();
        pi.getHeight(); -> data.length;
        pi.getWidth(); -> data[0].length;

        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[0].length; col++) {
                // pick a random offset within swapDistance in both row and col
                int offsetRow = row + rand.nextInt(2 * swapDistance + 1) - swapDistance;
                int offsetCol = col + rand.nextInt(2 * swapDistance + 1) - swapDistance;

                // clamp offsets using PixelImage.coordinate method
                offsetRow = PixelImage.coordinate(offsetRow, data.length - 1);
                offsetCol = PixelImage.coordinate(offsetCol, data[0].length - 1);

                // swap pixels
                Pixel temp = data[row][col];
                data[row][col] = data[offsetRow][offsetCol];
                data[offsetRow][offsetCol] = temp;
            }
        }
        // Apply the effect to the image
        pi.setData(data);
    }
}
