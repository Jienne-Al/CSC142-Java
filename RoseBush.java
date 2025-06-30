import java.awt.Color; // Access the Color class

// Access the graphics utilities in the UW library
import uwcse.graphics.GWindow;
import uwcse.graphics.Oval;

/**
 * Represents a rose bush with multiple flowers.
 * Each flower has a unique size and color.
 */
public class RoseBush {
    private int x, y; // Location of the rose bush
    private double scale; // Scale of the drawing
    private GWindow window; // Graphics window to display the rose bush

    /**
     * Constructs a rose bush at the given location with the given scale in the graphics window.
     * 
     * @param x      x-coordinate of the rose bush's location
     * @param y      y-coordinate of the rose bush's location
     * @param scale  scale factor for the rose bush's size
     * @param window the graphics window where the rose bush will be drawn
     */
    public RoseBush(int x, int y, double scale, GWindow window) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.window = window;

        draw(); // Draw the rose bush and flowers
    }

    /**
     * Draws the rose bush, its flowers, and other components in the scene.
     */
    private void draw() {
        // Draw the base of the bush (dark green oval)
        int bushWidth = (int) (150 * scale); // Adjusted for scale
        int bushHeight = (int) (100 * scale);
        Color darkGreen = new Color(0, 100, 0); // Dark green color for the bush base
        Oval bush = new Oval(x, y, bushWidth, bushHeight, darkGreen, true);
        window.add(bush); // Add bush to the window

        // Draw flowers using the helper method
        drawFlower(x + 90, y + 60, 2.0, Color.WHITE, new Color(244, 101, 160)); // Pink
        drawFlower(x + 300, y + 30, 1.8, Color.WHITE, new Color(150, 101, 244)); // Purple
        drawFlower(x + 250, y + 100, 0.9, Color.WHITE, new Color(246, 230, 99)); // Yellow
        drawFlower(x + 180, y + 50, 2.2, Color.WHITE, new Color(255, 102, 102)); // Coral flower
        drawFlower(x + 210, y + 80, 1.0, Color.WHITE, new Color(102, 204, 255)); // Light blue flower
    }

    /**
     * Draws a single flower with a center and 4 petals (top, bottom, left, right).
     * 
     * @param centerX     X coordinate of the flower's center
     * @param centerY     Y coordinate of the flower's center
     * @param scale       Scale factor for the flower
     * @param centerColor Color of the flower center
     * @param petalColor  Color of the flower petals
     */
    private void drawFlower(int centerX, int centerY, double scale, Color centerColor, Color petalColor) {
        int centerSize = (int) (4 * scale);
        int petalW = (int) (6 * scale);
        int petalH = (int) (12 * scale);

        // Draw the center of the flower
        window.add(new Oval(centerX - centerSize / 2, centerY - centerSize / 2, centerSize, centerSize, centerColor, true));

        // Draw the petals of the flower
        window.add(new Oval(centerX - petalW / 2, centerY - petalH - 1, petalW, petalH, petalColor, true));
        window.add(new Oval(centerX - petalW / 2, centerY + 1, petalW, petalH, petalColor, true));
        window.add(new Oval(centerX - petalH - 1, centerY - petalW / 2, petalH, petalW, petalColor, true));
        window.add(new Oval(centerX + 1, centerY - petalW / 2, petalH, petalW, petalColor, true));
    }
}
