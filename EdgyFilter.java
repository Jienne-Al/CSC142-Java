/**
 * A filter that sharpens the image by enhancing edges using an "Edgy" kernel.
 */
public class EdgyFilter implements Filter {
    /**
     * @param pi The PixelImage to be filtered.
     */
    @Override
    public void filter(PixelImage pi) {
    int[][] edgy = {
            {-1, -1, -1},
            {-1,  9, -1},
            {-1, -1, -1}
        };
        // Apply the effect to the image
        pi.transform(edgy);
    }
}