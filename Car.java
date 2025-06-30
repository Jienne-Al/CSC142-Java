import java.awt.Color; // Access the Color class

// Access the graphics utilities in the UW library
import uwcse.graphics.GWindow;
import uwcse.graphics.Oval;
import uwcse.graphics.Rectangle;

/**
 * Represents a car with a rectangular body and circular tires.
 * The car can be drawn facing either left or right.
 */
public class Car {
    private int x, y; // Location of the car
    private double scale; // Scale of the drawing
    private GWindow window; // Graphics window to display the car

    /**
     * Constructs a car at the given location with the given scale in the graphics window.
     *
     * @param x      x-coordinate of the car's location
     * @param y      y-coordinate of the car's location
     * @param scale  scale factor for the car's size
     * @param window the graphics window where the car will be drawn
     */
    public Car(int x, int y, double scale, GWindow window) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.window = window;
        
        draw(); // Draw the car when it is created
    }

    /**
     * Draws the car and its components.
     */
    private void draw() {
        int baseWidth = (int) (120 * scale); // Base width of the car
        int baseHeight = (int) (60 * scale); // Base height of the car

        Color pink = new Color(255, 187, 223);

        // Draw the top of the car (oval)
        int topWidth = (int) (0.5 * baseWidth);
        int topHeight = baseHeight;
        Oval topBody = new Oval(x + (int)(0.1 * baseWidth), y, topWidth, topHeight, pink, true);
        window.add(topBody);

        // Draw the bottom of the car (rectangle)
        int trunkWidth = baseWidth;
        int trunkHeight = (int)(0.5 * baseHeight);
        Rectangle bottomBody = new Rectangle(x - (int)(0.17 * baseWidth), y + (int)(0.5 * baseHeight), trunkWidth, trunkHeight, pink, true);
        window.add(bottomBody);

        // Draw the tires
        int tireSize = (int)(0.15 * baseWidth);
        Oval tire1 = new Oval(x - (int)(0.08 * baseWidth), y + (int)(0.8 * baseHeight), tireSize, tireSize, Color.BLACK, true);
        Oval tire2 = new Oval(x + (int)(0.6 * baseWidth), y + (int)(0.8 * baseHeight), tireSize, tireSize, Color.BLACK, true);
        window.add(tire1);
        window.add(tire2);

        // Sizes for the headlights and windows
        int lightSize = (int)(0.12 * baseWidth);
        int windowWidth = (int)(0.3 * baseWidth);
        int windowHeight = (int)(0.3 * baseHeight);

        // Use math.random to randomize the direction of the headlights and window
        if (Math.random() >= 0.5) {
            // Headlight on left side
            Oval headlight = new Oval(x - (int)(0.17 * baseWidth), y + (int)(0.5 * baseHeight), lightSize, lightSize, Color.YELLOW, true);
            window.add(headlight);

            // Window shifted more to the left
            int windowX = x + (int)(0.1 * baseWidth);
            int windowY = y + (int)(0.2 * baseHeight);
            Rectangle carWindow = new Rectangle(windowX, windowY, windowWidth, windowHeight, Color.WHITE, true);
            window.add(carWindow);
        } else {
            // Headlight on right side
            Oval headlight = new Oval(x + (int)(0.72 * baseWidth), y + (int)(0.5 * baseHeight), lightSize, lightSize, Color.YELLOW, true);
            window.add(headlight);

            // Window shifted more to the right
            int windowX = x + (int)(0.3 * baseWidth);
            int windowY = y + (int)(0.2 * baseHeight);
            Rectangle carWindow = new Rectangle(windowX, windowY, windowWidth, windowHeight, Color.WHITE, true);
            window.add(carWindow);
        }
    }
}
