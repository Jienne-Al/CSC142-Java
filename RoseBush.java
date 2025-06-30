import java.awt.Color; // Access the color library
import uwcse.graphics.GWindow;
import uwcse.graphics.Oval;

/**
 * Represents a rose bush with five flowers, each with a unique color, size, and position.
 * 
 * The following flowers are drawn with specific colors:
 * Flower 1: Pink
 * Flower 2: Purple
 * Flower 3: Yellow
 * Flower 4: Coral
 * Flower 5: Light Blue
 */
public class RoseBush {
    private int x, y; // Location of the rose bush
    private double scale; // Scale of the drawing
    private GWindow window; // Graphics window to display the rose bush

    // Bush base
    private Oval bush;

    // Flower 1 (Pink)
    public Oval center1, petal1a, petal1b, petal1c, petal1d;
    // Flower 2 (Purple)
    public Oval center2, petal2a, petal2b, petal2c, petal2d;
    // Flower 3 (Yellow)
    public Oval center3, petal3a, petal3b, petal3c, petal3d;
    // Flower 4 (Coral)
    public Oval center4, petal4a, petal4b, petal4c, petal4d;
    // Flower 5 (Light Blue)
    public Oval center5, petal5a, petal5b, petal5c, petal5d;

    /**
     * Constructs a rose bush at the given location with the given scale in the graphics window.
     * The bush and flowers are drawn immediately upon creation.
     * 
     * @param x The x-coordinate of the rose bush's location.
     * @param y The y-coordinate of the rose bush's location.
     * @param scale The scale factor for resizing the bush and flowers.
     * @param window The graphics window in which the rose bush is displayed.
     */
    public RoseBush(int x, int y, double scale, GWindow window) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.window = window;

        this.drawBush();
        this.drawFlowers();
    }

    /**
     * Draws the base of the rose bush as an oval with a dark green color.
     */
    private void drawBush() {
        int bushWidth = (int) (150 * scale);
        int bushHeight = (int) (100 * scale);
        Color darkGreen = new Color(0, 100, 0);
        bush = new Oval(x, y, bushWidth, bushHeight, darkGreen, true);
        window.add(bush);
    }

    /**
     * Grows or shrinks the rose bush and redraws the flowers with the new scale.
     * 
     * @param factor The scaling factor: values greater than 1 grow the bush, and values less than 1 shrink it.
     */
    public void growShrink(double factor) {
        this.scale *= factor;
        eraseFlowers();
        drawFlowers();
    }

    /**
     * Draws the five flowers with different colors and positions relative to the bush.
     */
    private void drawFlowers() {
        // Flower 1: Pink
        drawFlower1(x + 90, y + 60, this.scale - 1, Color.WHITE, new Color(244, 101, 160));
        // Flower 2: Purple
        drawFlower2(x + 300, y + 30, this.scale - 0.5, Color.WHITE, new Color(150, 101, 244));
        // Flower 3: Yellow
        drawFlower3(x + 250, y + 100, this.scale - 2, Color.WHITE, new Color(246, 230, 99));
        // Flower 4: Coral
        drawFlower4(x + 180, y + 50, this.scale - 1.75, Color.WHITE, new Color(255, 102, 102));
        // Flower 5: Light Blue
        drawFlower5(x + 210, y + 80, this.scale - 1, Color.WHITE, new Color(102, 204, 255));
    }

    /**
     * Erases all flowers from the graphics window.
     */
    public void eraseFlowers() {
        // Remove all the parts of each flower
        window.remove(center1); window.remove(petal1a); window.remove(petal1b);
        window.remove(petal1c); window.remove(petal1d);

        window.remove(center2); window.remove(petal2a); window.remove(petal2b);
        window.remove(petal2c); window.remove(petal2d);

        window.remove(center3); window.remove(petal3a); window.remove(petal3b);
        window.remove(petal3c); window.remove(petal3d);

        window.remove(center4); window.remove(petal4a); window.remove(petal4b);
        window.remove(petal4c); window.remove(petal4d);

        window.remove(center5); window.remove(petal5a); window.remove(petal5b);
        window.remove(petal5c); window.remove(petal5d);
    }

    /**
     * Draws the first flower (Pink) at the given coordinates with the specified scale.
     * 
     * @param cx The x-coordinate of the flower's center.
     * @param cy The y-coordinate of the flower's center.
     * @param s The scale factor for resizing the flower.
     * @param centerColor The color of the flower's center.
     * @param petalColor The color of the flower's petals.
     */
    private void drawFlower1(int cx, int cy, double s, Color centerColor, Color petalColor) {
        int c = (int) (4 * s), pw = (int) (6 * s), ph = (int) (12 * s);
        center1 = new Oval(cx - c / 2, cy - c / 2, c, c, centerColor, true);
        petal1a = new Oval(cx - pw / 2, cy - ph - 1, pw, ph, petalColor, true);
        petal1b = new Oval(cx - pw / 2, cy + 1, pw, ph, petalColor, true);
        petal1c = new Oval(cx - ph - 1, cy - pw / 2, ph, pw, petalColor, true);
        petal1d = new Oval(cx + 1, cy - pw / 2, ph, pw, petalColor, true);
        window.add(center1); window.add(petal1a); window.add(petal1b); window.add(petal1c); window.add(petal1d);
    }

    /**
     * Draws the second flower (Purple) at the given coordinates with the specified scale.
     * 
     * @param cx The x-coordinate of the flower's center.
     * @param cy The y-coordinate of the flower's center.
     * @param s The scale factor for resizing the flower.
     * @param centerColor The color of the flower's center.
     * @param petalColor The color of the flower's petals.
     */
    private void drawFlower2(int cx, int cy, double s, Color centerColor, Color petalColor) {
        int c = (int) (4 * s), pw = (int) (6 * s), ph = (int) (12 * s);
        center2 = new Oval(cx - c / 2, cy - c / 2, c, c, centerColor, true);
        petal2a = new Oval(cx - pw / 2, cy - ph - 1, pw, ph, petalColor, true);
        petal2b = new Oval(cx - pw / 2, cy + 1, pw, ph, petalColor, true);
        petal2c = new Oval(cx - ph - 1, cy - pw / 2, ph, pw, petalColor, true);
        petal2d = new Oval(cx + 1, cy - pw / 2, ph, pw, petalColor, true);
        window.add(center2); window.add(petal2a); window.add(petal2b); window.add(petal2c); window.add(petal2d);
    }

    /**
     * Draws the third flower (Yellow) at the given coordinates with the specified scale.
     * 
     * @param cx The x-coordinate of the flower's center.
     * @param cy The y-coordinate of the flower's center.
     * @param s The scale factor for resizing the flower.
     * @param centerColor The color of the flower's center.
     * @param petalColor The color of the flower's petals.
     */
    private void drawFlower3(int cx, int cy, double s, Color centerColor, Color petalColor) {
        int c = (int) (4 * s), pw = (int) (6 * s), ph = (int) (12 * s);
        center3 = new Oval(cx - c / 2, cy - c / 2, c, c, centerColor, true);
        petal3a = new Oval(cx - pw / 2, cy - ph - 1, pw, ph, petalColor, true);
        petal3b = new Oval(cx - pw / 2, cy + 1, pw, ph, petalColor, true);
        petal3c = new Oval(cx - ph - 1, cy - pw / 2, ph, pw, petalColor, true);
        petal3d = new Oval(cx + 1, cy - pw / 2, ph, pw, petalColor, true);
        window.add(center3); window.add(petal3a); window.add(petal3b); window.add(petal3c); window.add(petal3d);
    }

    /**
     * Draws the fourth flower (Coral) at the given coordinates with the specified scale.
     * 
     * @param cx The x-coordinate of the flower's center.
     * @param cy The y-coordinate of the flower's center.
     * @param s The scale factor for resizing the flower.
     * @param centerColor The color of the flower's center.
     * @param petalColor The color of the flower's petals.
     */
    private void drawFlower4(int cx, int cy, double s, Color centerColor, Color petalColor) {
        int c = (int) (4 * s), pw = (int) (6 * s), ph = (int) (12 * s);
        center4 = new Oval(cx - c / 2, cy - c / 2, c, c, centerColor, true);
        petal4a = new Oval(cx - pw / 2, cy - ph - 1, pw, ph, petalColor, true);
        petal4b = new Oval(cx - pw / 2, cy + 1, pw, ph, petalColor, true);
        petal4c = new Oval(cx - ph - 1, cy - pw / 2, ph, pw, petalColor, true);
        petal4d = new Oval(cx + 1, cy - pw / 2, ph, pw, petalColor, true);
        window.add(center4); window.add(petal4a); window.add(petal4b); window.add(petal4c); window.add(petal4d);
    }

    /**
     * Draws the fifth flower (Light Blue) at the given coordinates with the specified scale.
     * 
     * @param cx The x-coordinate of the flower's center.
     * @param cy The y-coordinate of the flower's center.
     * @param s The scale factor for resizing the flower.
     * @param centerColor The color of the flower's center.
     * @param petalColor The color of the flower's petals.
     */
    private void drawFlower5(int cx, int cy, double s, Color centerColor, Color petalColor) {
        int c = (int) (4 * s), pw = (int) (6 * s), ph = (int) (12 * s);
        center5 = new Oval(cx - c / 2, cy - c / 2, c, c, centerColor, true);
        petal5a = new Oval(cx - pw / 2, cy - ph - 1, pw, ph, petalColor, true);
        petal5b = new Oval(cx - pw / 2, cy + 1, pw, ph, petalColor, true);
        petal5c = new Oval(cx - ph - 1, cy - pw / 2, ph, pw, petalColor, true);
        petal5d = new Oval(cx + 1, cy - pw / 2, ph, pw, petalColor, true);
        window.add(center5); window.add(petal5a); window.add(petal5b); window.add(petal5c); window.add(petal5d);
    }
}
