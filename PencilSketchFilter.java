/*
 * A filter that simulates a pencil sketch effect by:
 * Converting the image to grayscale,
 * Highlighting edges using Laplacian edge detection,
 * Inverting the image to look like black pencil on white paper.
 */
public class PencilSketchFilter implements Filter {

    // Luminance weights based on human eye sensitivity to colors
    private static final double RED_LUMINANCE = 0.299;
    private static final double GREEN_LUMINANCE = 0.587;
    private static final double BLUE_LUMINANCE = 0.114;

    /**
     * @param pi The PixelImage to be filtered.
     */
    @Override
    public void filter(PixelImage pi) {
        // Convert the image to grayscale
        grayscale(pi);

        // Detect edges to create "pencil lines"
        Filter laplacian = new LaplacianFilter();
        laplacian.filter(pi);

        // Invert colors to mimic black pencil on white paper
        Filter negative = new NegativeFilter();
        negative.filter(pi);
    }

    /**
     * Convert the image to grayscale using weighted average of RGB components.
     *
     * @param pi The PixelImage to be filtered.
     */
    private void grayscale(PixelImage pi) {
        Pixel[][] data = pi.getData();

        // Loop through all pixels and calculate grayscale using weighted sum
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[0].length; col++) {
                Pixel p = data[row][col];

                // Weighted sum for human-perceived brightness
                int gray = (int) (RED_LUMINANCE * p.red + GREEN_LUMINANCE * p.green + BLUE_LUMINANCE * p.blue);

                // Set all color channels to the grayscale value
                data[row][col] = new Pixel(gray, gray, gray);
            }
        }

        // Apply the effect to the image
        pi.setData(data);
    }
}
