import java.awt.Color; // Access the color library
import uwcse.graphics.GWindow;
import uwcse.graphics.Triangle;

/**
 * Represents a mountain with a snow-capped peak.
 * The snow on the mountain gradually melts and regenerates.
 */
public class Mountain {
    // Position and scale of the mountain
    private int x, y;
    private double scale;

    // Graphics window to draw in
    private GWindow window;

    // Snow triangle and its dimensions
    private Triangle snow;               // Snowcap graphic
    private int originalSnowWidth;       // Original snow width before melting
    private int originalSnowHeight;      // Original snow height before melting
    private int currentSnowWidth;        // Current width during melting cycle
    private int currentSnowHeight;       // Current height during melting cycle

    /**
     * Constructs a mountain with a snowcap at a given position, scale, and window.
     *
     * @param x      The x-coordinate of the mountain peak
     * @param y      The y-coordinate of the mountain peak
     * @param scale  The scale factor of the mountain
     * @param window The graphics window where the mountain will be drawn
     */
    public Mountain(int x, int y, double scale, GWindow window) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.window = window;

        drawMountain();  // Draw the main mountain body
        initSnow();      // Initialize snow dimensions
        drawSnow();      // Draw the snowcap
    }

    /**
     * Draws the gray mountain using a filled triangle.
     * The mountain is centered at (x, y) and scales with the given factor.
     */
    private void drawMountain() {
        int height = (int) (100 * scale); // Scale mountain height
        int baseLeftX = x - height / 2;   // Left base corner
        int baseRightX = x + height / 2;  // Right base corner
        int baseY = y + height;           // Base Y-coordinate

        // Create and add the gray triangle representing the mountain
        Triangle mountain = new Triangle(x, y, baseLeftX, baseY, baseRightX, baseY, Color.GRAY, true);
        window.add(mountain);
    }

    /**
     * Initializes the snowcap size based on the mountain height.
     * Stores the original snow size for melting cycle resets.
     */
    private void initSnow() {
        int mountainHeight = (int) (100 * scale); // Calculate the mountain height
        originalSnowWidth = (int) (mountainHeight * 0.3);  // Initial snow width
        originalSnowHeight = (int) (mountainHeight * 0.3); // Initial snow height
        currentSnowWidth = originalSnowWidth;  // Set current width
        currentSnowHeight = originalSnowHeight; // Set current height
    }

    /**
     * Draws the snowcap using the current snow width and height.
     * The snow is represented as a white triangle at the top of the mountain.
     */
    private void drawSnow() {
        int sx2 = x - currentSnowWidth / 2;    // Left point of the snow triangle
        int sy2 = y + currentSnowHeight;       // Y-coordinate of the left point
        int sx3 = x + currentSnowWidth / 2;    // Right point of the snow triangle
        int sy3 = y + currentSnowHeight;       // Y-coordinate of the right point

        // Create and add the snow triangle
        snow = new Triangle(x, y, sx2, sy2, sx3, sy3, Color.WHITE, true);
        window.add(snow);
    }

    /**
     * Simulates the melting of snow by shrinking its size.
     * Once fully melted, the snow resets to its original size.
     *
     * @param dx The displacement along the x-axis (not used in the method, but could be for future enhancements)
     */
    public void meltSnow(int dx) {
        if (snow != null) {
            window.remove(snow);  // Remove current snowcap from the window
        }

        // Shrink the snow dimensions
        currentSnowWidth -= 2;
        currentSnowHeight -= 2;

        // Reset snow when fully melted
        if (currentSnowWidth <= 0) {
            currentSnowWidth = originalSnowWidth;
            currentSnowHeight = originalSnowHeight;
        }

        drawSnow();  // Redraw the snowcap with updated dimensions
    }
}
