/**
 * A filter that applies a Laplacian edge detection to an image.
 */
public class LaplacianFilter implements Filter {
    /**
     * @param pi The PixelImage to be filtered.
     */
    @Override
    public void filter(PixelImage pi) {
    int[][] laplacian = {
            {-1, -1, -1},
            {-1,  8, -1},
            {-1, -1, -1}
        };
        // Apply the effect to the image
        pi.transform(laplacian); 
    }
}