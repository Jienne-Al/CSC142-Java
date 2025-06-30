/**
 * HOMEWORK REPORT
 *
 * Additional Filters Implemented and Submitted:
 * - Nostalgic Filter: Applies a 5x5 blur followed by a grayscale transformation to evoke a vintage, softened look.
 * - Thermal Filter: Inspired by macOS Photobooth, this filter maps pixel brightness values to a thermal color gradient, simulating heat vision.
 * - Brighten Filter: Increases image brightness by approximately 30%, enhancing overall luminance without washing out colors.
 * - Mosaic Filter: Creates a pixelated effect by averaging colors in 5x5 blocks, producing a distinctive blocky, low-resolution style.
 * - Pixel Swap Filter: Generates a randomized marble-like texture by swapping pixels within a configurable radius, adding visual distortion.
 * - Ripple Effect Filter: Produces horizontal sine-wave distortions by offsetting pixels based on trigonometric functions, simulating water ripples.
 * - Pencil Sketch Filter: Combines grayscale conversion, Laplacian edge detection, and negative inversion to emulate a hand-drawn pencil sketch.
 *
 * What Works and What Doesn't:
 * - Most filters operate as intended.
 * - All filters manipulate pixel data correctly, using clamping to maintain RGB values within the valid 0–255 range.
 * - Some filters, such as Edgy and Laplacian, can generate harsh or noisy artifacts depending on image complexity.
 * - Surprisingly, the mosaic filter isn't dynamic when we try to click on the effect twice unlike others because it replaces
 *   each 5x5 block with its average color, and on reapplication, the blocks are already flat/uniform.
 * - BrightenFilter builds up intensity on each use because it adds fixed brightness to current pixel values, clamped at 255,
 *   whereas MosaicFilter does not compound effects since pixel blocks become uniform.
 * - MosaicFilter only works neatly on images where both width and height are multiples of the block size (example: 5); otherwise,
 *   right and bottom edges remain untouched.
 * - PixelSwapFilter may produce unintended visual glitches if pixels are swapped in-place without copying data beforehand.
 * - Some filters (like Ripple) apply transformations that wrap around edges, which can confuse beginners expecting linear movement.
 * - Grayscale conversion works best using luminance-weighted formulas rather than averaging RGB values equally; the former
 *   gives a more natural grayscale output.
 * - It’s easy to overlook the need to call 'setData()'' after applying a transformation — forgetting this can result in
 *   filters appearing to "not work" when in fact the data was never updated on screen.
 * - Reapplying a filter only shows visual change if the filter builds on the modified state of the image — using the same
 *   effect on an already-filtered image may look identical if the data has been flattened (like with Mosaic or Grayscale).
 *
 * Surprises and Challenges:
 * - It was amazing to discover how filters can be layered or composed to create new effects by reusing existing logic.
 * - Realizing that a digital image is fundamentally a 2D array of pixels with numerical color values helped deepen our understanding.
 * - Implementing clamping logic to handle out-of-bounds pixel coordinates and colors during convolution proved essential to avoid errors and produce desirable effects.s
 * - Using a 5x5 convolution kernel often produced noticeably smoother and more natural blur effects compared to 3x3 kernels.
 * 
 * - The hardest filters to implement were the Pixel Swap and Ripple Effect filters. We attempted to create these entirely from scratch,
 *   without relying much on any reference code or standard templates. Neither of us were particularly strong in arithmetic or applied math,
 *   so we had to consult textbooks and even reach out to math professors to develop the necessary formulas.
 * 
 * - The Pixel Swap filter required extensive experimentation with displacement patterns, randomization boundaries,
 *   and clamping techniques to produce a distortion that looked chaotic but still visually reminiscent of marble textures.
 * 
 * - The Ripple Effect filter was especially complex. We built it through repeated trial and error with trigonometric functions.
 *   We tested different sine wave parameters—frequency, amplitude, and phase offset—by observing real-time output and fine-tuning until
 *   the distortion realistically simulated gentle water ripples.
 *
 * Overall, we had a spectacular experience figuring out what works and what doesn't. Also, we had fun trying out new filters on images.
 * The filters significantly expand SnapShop's image processing capabilities, showcasing core concepts of convolution, pixel-level manipulation, and creative visual effects
 * commonly found in professional image editing software.
 */


/**
 * A class to configure the SnapShop application
 * 
 * @authors NganThiThanhNguyen
 * @authors JienneCryzelleAlegre
 * 
 * @version 2025-06-14
 */
public class SnapShopConfiguration {
    /**
     * Method to configure the SnapShop. Call methods like addFilter and
     * setDefaultFilename here.
     * 
     * @param theShop A pointer to the application
     */
    public static void configure(SnapShop theShop) {
        // Set the default image to display
        theShop.setDefaultFilename("billg.jpg");

        // Basic transformations
        theShop.addFilter(new FlipHorizontalFilter(), "Flip Horizontal");
        theShop.addFilter(new FlipVerticalFilter(), "Flip Vertical");
        theShop.addFilter(new NegativeFilter(), "Negative");

        // Standard convolution filters
        theShop.addFilter(new GaussianBlurFilter(), "Gaussian Blur");
        theShop.addFilter(new LaplacianFilter(), "Laplacian");
        theShop.addFilter(new UnsharpMaskFilter(), "Unsharp Mask");
        theShop.addFilter(new EdgyFilter(), "Edgy");

        // Custom filters
        theShop.addFilter(new BrightenFilter(), "Brighten");
        theShop.addFilter(new MosaicFilter(), "Mosaic");
        theShop.addFilter(new PixelSwapFilter(), "Pixel Swap");
        theShop.addFilter(new NostalgicFilter(), "Nostalgic");
        theShop.addFilter(new ThermalFilter(), "Thermal Mask");
        theShop.addFilter(new PencilSketchFilter(), "Pencil Sketch");
        theShop.addFilter(new RippleFilter(), "Ripple");
    }
}