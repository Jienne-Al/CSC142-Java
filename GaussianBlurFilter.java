/**
 * A filter that applies a Gaussian blur to an image.
 */
public class GaussianBlurFilter implements Filter {

    /**
     * @param pi The PixelImage to be filtered.
     */
    @Override
    public void filter(PixelImage pi) {
        int[][] gaussian = {
            {1, 2, 1},
            {2, 4, 2},
            {1, 2, 1}
        };
        // Apply the effect to the image
        pi.transform(gaussian);
    }
}