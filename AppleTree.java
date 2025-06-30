import java.awt.Color; // Access the color library
import uwcse.graphics.GWindow;
import uwcse.graphics.Rectangle;
import uwcse.graphics.Oval;

/**
 * A class to draw an apple tree with a trunk, canopy, and apples.
 */
public class AppleTree {
    // The graphics window this tree belongs to
    private GWindow window;
    private int x, y;

    // Graphics elements making up the tree
    private Rectangle trunk;
    private Oval canopy;
    private Oval apple1, apple2, apple3, apple4, apple5;

    // Scaling factor for the tree
    private double scale;

    // Dimensions based on scale
    private int trunkWidth, trunkHeight;
    private int canopyRadius;
    private int appleSize;

    /**
     * Creates an AppleTree and draws it at the specified position.
     *
     * @param x      the x-coordinate of the tree's base
     * @param y      the y-coordinate of the tree's base (from top of window)
     * @param scale  the scale factor for the tree's size
     * @param window the graphics window to draw in
     */
    public AppleTree(int x, int y, double scale, GWindow window) {
        this.window = window;
        this.x = x;
        this.y = y;
        this.scale = scale;

        this.trunkWidth = (int) (60 * this.scale);
        this.trunkHeight = (int) (120 * this.scale);
        this.canopyRadius = (int) (50 * this.scale);
        this.appleSize = (int) (5 * this.scale);

        this.drawAppleTree();
    }

    /**
     * Draws the apple tree including the trunk, canopy, and apples.
     */
    private void drawAppleTree() {
        drawTrunk();
        drawCanopy();
        drawApples();
    }

    /**
     * Draws the apple tree trunk based on the stored x and y coordinates.
     */
    private void drawTrunk() {
        Color brown = new Color(110, 67, 37); // Trunk color
        int trunkX = x + (int) (1.15 * trunkWidth); // Adjust x for trunk position
        int trunkY = y - trunkHeight; // Adjust y for trunk position

        trunk = new Rectangle(trunkX, trunkY, trunkWidth / 2, trunkHeight, brown, true);
        window.add(trunk);
    }

    /**
     * Draws the canopy of the apple tree based on the stored x and y coordinates.
     * The canopy is represented by multiple overlapping ovals to simulate foliage.
     */
    private void drawCanopy() {
        int cx = x + trunkWidth;
        int cy = y - trunkHeight;
        Color green = new Color(52, 148, 38); // Canopy color

        canopy = new Oval(cx, cy, canopyRadius, canopyRadius, green, true);
        window.add(canopy);

        // Additional overlapping ovals for fuller look
        window.add(new Oval(cx - (int) (0.6 * canopyRadius), cy + (int) (0.2 * canopyRadius),
                canopyRadius, canopyRadius, green, true));
        window.add(new Oval(cx + (int) (0.6 * canopyRadius), cy + (int) (0.2 * canopyRadius),
                canopyRadius, canopyRadius, green, true));
        window.add(new Oval(cx - (int) (0.3 * canopyRadius), cy - (int) (0.4 * canopyRadius),
                canopyRadius, canopyRadius, green, true));
        window.add(new Oval(cx + (int) (0.3 * canopyRadius), cy - (int) (0.4 * canopyRadius),
                canopyRadius, canopyRadius, green, true));
    }

    /**
     * Draws five apples on the apple tree based on the stored x and y coordinates.
     */
    private void drawApples() {
        int cx = x + trunkWidth; // Center x of canopy
        int cy = y - trunkHeight; // Top y of canopy
        Color appleRed = new Color(230, 62, 46); // Apple color

        apple1 = new Oval(cx + canopyRadius / 2 - appleSize / 2, cy + canopyRadius / 2 - appleSize / 2,
                appleSize, appleSize, appleRed, true);
        apple2 = new Oval(cx - (int) (0.6 * canopyRadius) + canopyRadius / 2 - appleSize / 2 - 10,
                cy + (int) (0.2 * canopyRadius) + canopyRadius / 2 - appleSize / 2 + 10,
                appleSize, appleSize, appleRed, true);
        apple3 = new Oval(cx + (int) (0.6 * canopyRadius) + canopyRadius / 2 - appleSize / 2 + 10,
                cy + (int) (0.2 * canopyRadius) + canopyRadius / 2 - appleSize / 2 + 10,
                appleSize, appleSize, appleRed, true);
        apple4 = new Oval(cx - (int) (0.3 * canopyRadius) + canopyRadius / 2 - appleSize / 2 - 15,
                cy - (int) (0.4 * canopyRadius) + canopyRadius / 2 - appleSize / 2 - 10,
                appleSize, appleSize, appleRed, true);
        apple5 = new Oval(cx + (int) (0.3 * canopyRadius) + canopyRadius / 2 - appleSize / 2 + 15,
                cy - (int) (0.4 * canopyRadius) + canopyRadius / 2 - appleSize / 2 - 10,
                appleSize, appleSize, appleRed, true);

        window.add(apple1);
        window.add(apple2);
        window.add(apple3);
        window.add(apple4);
        window.add(apple5);
    }

    /**
     * Cycles the color of all apples: Green → Yellow → Red → Green.
     */
    public void ripen() {
        cycleAppleColor(apple1);
        cycleAppleColor(apple2);
        cycleAppleColor(apple3);
        cycleAppleColor(apple4);
        cycleAppleColor(apple5);
    }

    /**
     * Cycles the color of a given apple.
     * Changes from Green → Yellow → Red → Green.
     *
     * @param apple The apple whose color is to be cycled
     */
    private void cycleAppleColor(Oval apple) {
        Color c = apple.getColor();

        if (c.equals(Color.GREEN)) {
            apple.setColor(Color.YELLOW);
        } else if (c.equals(Color.YELLOW)) {
            apple.setColor(Color.RED);
        } else {
            apple.setColor(Color.GREEN);
        }
    }
}
