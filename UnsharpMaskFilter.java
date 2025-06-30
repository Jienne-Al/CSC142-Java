/**
 * Filter that sharpens the image using Unsharp Masking.
 * Enhances edges by subtracting a blurred version from the original image.
 */
public class UnsharpMaskFilter implements Filter {
/**
     * @param pi The PixelImage to be filtered.
     */
    @Override
    public void filter(PixelImage pi) {
    int[][] unsharpMask = {
            {-1, -2, -1},
            {-2, 28, -2},
            {-1, -2, -1}
        };
        // Apply the effect to the image
        pi.transform(unsharpMask);
    }
}