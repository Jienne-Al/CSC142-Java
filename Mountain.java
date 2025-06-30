import java.awt.Color; // Access the Color class

// Access the graphics utilities in the UW library
import uwcse.graphics.GWindow;
import uwcse.graphics.Triangle;

/**
 * Represents a mountain with a snow-capped peak.
 * The mountain is drawn in a graphics window at a specific location and scale.
 */
public class Mountain {
    private int x, y; // Location of the mountain
    private double scale; // Scale of the drawing
    private GWindow window; // Graphics window to display the mountain

    /**
     * Constructs a mountain at the given location with the given scale in the graphics window.
     * 
     * @param x      x-coordinate of the mountain's location
     * @param y      y-coordinate of the mountain's location
     * @param scale  scale factor for the mountain's size
     * @param window the graphics window where the mountain will be drawn
     */
    public Mountain(int x, int y, double scale, GWindow window) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.window = window;
        
        draw(); // Calls the private method to draw the mountain
    }

    /**
     * Draws the mountain and its snow-capped peak.
     */
    private void draw() {
        int height = (int) (100 * scale); // Height of the mountain
        int x1 = x, y1 = y;
        int x2 = x - height / 2, y2 = y + height;
        int x3 = x + height / 2, y3 = y + height;

        // Draw the mountain (triangle)
        Triangle mountain = new Triangle(x1, y1, x2, y2, x3, y3, Color.GRAY, true);
        window.add(mountain);

        // Add the snow on top of the mountain
        int snowWidth = (int) (height * 0.3);
        int snowHeight = (int) (height * 0.3);
        int sx2 = x - snowWidth / 2, sy2 = y + snowHeight;
        int sx3 = x + snowWidth / 2, sy3 = y + snowHeight;

        // Draw the snow-capped peak (triangle)
        Triangle snow = new Triangle(x, y, sx2, sy2, sx3, sy3, Color.WHITE, true);
        window.add(snow);
    }
}
