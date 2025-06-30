import java.awt.Color; // Access the Color class

// Access the graphics utilities in the UW library
import uwcse.graphics.GWindow;
import uwcse.graphics.Rectangle;
import uwcse.graphics.Oval;

/**
 * A class to draw an apple tree with a trunk, canopy, and apples.
 */
public class AppleTree {
    // Fields to store the tree’s position, scale, and drawing window
    private int x, y;
    private double scale;
    private GWindow window;

    /**
     * Constructs an AppleTree object and draws it in the window.
     * @param x      the x-coordinate of the tree's base
     * @param y      the y-coordinate of the tree's base
     * @param scale  the scale of the tree
     * @param window the graphics window to draw in
     */
    public AppleTree(int x, int y, double scale, GWindow window) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.window = window;

        draw(); // Draw the tree when the object is created
    }

    // Draws the entire tree by calling helper methods
    private void draw() {
        drawTrunk();
        drawCanopy();
        drawApples();
    }

    // Draws the tree trunk
    private void drawTrunk() {
        int w = (int) (60 * scale); // Width base for scaling
        int h = (int) (120 * scale); // Height of the trunk
        int trunkWidth = (int) (0.5 * w);
        int trunkHeight = h;

        Color brown = new Color(110, 67, 37); // Brown color for the trunk

        // Create the trunk rectangle
        Rectangle trunk = new Rectangle(x + (int) (1.15 * w), y + h, trunkWidth, trunkHeight, brown, true);
        
        //Draw the trunk rectangle
        window.add(trunk);
    }

    // Draws the green leafy canopy of the tree
    private void drawCanopy() {
        int w = (int) (60 * scale);
        int h = (int) (120 * scale);
        int canopyRadius = (int) (0.9 * w);
        int cx = x + w;
        int cy = y + h;

        Color lightGreen = new Color(52, 148, 38); // Light green for leaves

        // Five overlapping ovals to form the canopy
        Oval c1 = new Oval(cx, cy, canopyRadius, canopyRadius, lightGreen, true);
        Oval c2 = new Oval(cx - (int) (0.6 * canopyRadius), cy + (int) (0.2 * canopyRadius), canopyRadius, canopyRadius, lightGreen, true);
        Oval c3 = new Oval(cx + (int) (0.6 * canopyRadius), cy + (int) (0.2 * canopyRadius), canopyRadius, canopyRadius, lightGreen, true);
        Oval c4 = new Oval(cx - (int) (0.3 * canopyRadius), cy - (int) (0.4 * canopyRadius), canopyRadius, canopyRadius, lightGreen, true);
        Oval c5 = new Oval(cx + (int) (0.3 * canopyRadius), cy - (int) (0.4 * canopyRadius), canopyRadius, canopyRadius, lightGreen, true);

        //Draw parts of the canopy
        window.add(c1);
        window.add(c2);
        window.add(c3);
        window.add(c4);
        window.add(c5);
    }

    // Draws five red apples on the tree canopy
    private void drawApples() {
        int w = (int) (60 * scale);
        int h = (int) (120 * scale);
        int canopyRadius = (int) (0.9 * w);
        int cx = x + w;
        int cy = y + h;
        int appleSize = (int) (0.1 * w);

        Color appleRed = new Color(230, 62, 46); // Bright red for apples

        // Position apples on each canopy oval
        Oval apple1 = new Oval(cx + canopyRadius / 2 - appleSize / 2,
                               cy + canopyRadius / 2 - appleSize / 2, appleSize, appleSize, appleRed, true);

        Oval apple2 = new Oval(cx - (int) (0.6 * canopyRadius) + canopyRadius / 2 - appleSize / 2 - 10,
                               cy + (int) (0.2 * canopyRadius) + canopyRadius / 2 - appleSize / 2 + 10,
                               appleSize, appleSize, appleRed, true);

        Oval apple3 = new Oval(cx + (int) (0.6 * canopyRadius) + canopyRadius / 2 - appleSize / 2 + 10,
                               cy + (int) (0.2 * canopyRadius) + canopyRadius / 2 - appleSize / 2 + 10,
                               appleSize, appleSize, appleRed, true);

        Oval apple4 = new Oval(cx - (int) (0.3 * canopyRadius) + canopyRadius / 2 - appleSize / 2 - 15,
                               cy - (int) (0.4 * canopyRadius) + canopyRadius / 2 - appleSize / 2 - 10,
                               appleSize, appleSize, appleRed, true);

        Oval apple5 = new Oval(cx + (int) (0.3 * canopyRadius) + canopyRadius / 2 - appleSize / 2 + 15,
                               cy - (int) (0.4 * canopyRadius) + canopyRadius / 2 - appleSize / 2 - 10,
                               appleSize, appleSize, appleRed, true);

        window.add(apple1);
        window.add(apple2);
        window.add(apple3);
        window.add(apple4);
        window.add(apple5);
    }
}
