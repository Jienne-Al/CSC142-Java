import java.awt.Color;
import uwcse.graphics.GWindow;
import uwcse.graphics.Rectangle;
import uwcse.graphics.Oval;

/**
 * A class to represent a simple car animation.
 * The car can move left or right in the window.
 */
public class Car {
    // Location and the scale of the car
    private int x;
    private int y;
    private double scale;

    // The graphics window where the car will be drawn
    private GWindow window;

    // Direction the car is currently moving in (LEFT_MOVING or RIGHT_MOVING)
    private int direction;

    // Color of the car
    private Color pink = new Color(255, 187, 223);

    // Constants to represent direction values
    public static final int LEFT_MOVING = 0;
    public static final int RIGHT_MOVING = 1;

    // Graphical components that make up the visual representation of the car
    private Oval topBody;
    private Oval tire1;
    private Oval tire2;
    private Oval headlight;
    private Rectangle bottomBody;
    private Rectangle carWindow;

    /**
     * Constructs a Car object with given position, scale, direction, and window.
     *
     * @param x         The x-coordinate of the car
     * @param y         The y-coordinate of the car
     * @param scale     The scale of the car
     * @param direction The direction the car is moving (LEFT_MOVING or RIGHT_MOVING)
     * @param window    The graphics window to draw the car in
     */
    public Car(int x, int y, double scale, int direction, GWindow window) {
        this.x = x;
        this.y = y;
        this.scale = scale;
        this.direction = direction;
        this.window = window;

        this.drawCar(); // Draw the car
    }

    /**
     * Draws the full car by calling individual helper methods.
     */
    private void drawCar() {
        drawTopCar();
        drawBottomCar();
        drawTires();
        drawHeadlightAndWindow();
    }

    /**
     * Draws the top (rounded) part of the car.
     */
    private void drawTopCar() {
        int baseWidth = (int) (120 * scale);
        int baseHeight = (int) (60 * scale);

        int topWidth = (int) (0.5 * baseWidth);
        int topHeight = baseHeight;
        topBody = new Oval(x + (int) (0.1 * baseWidth), y, topWidth, topHeight, pink, true);
        window.add(topBody);
    }

    /**
     * Draws the bottom rectangular part of the car.
     */
    private void drawBottomCar() {
        int baseWidth = (int) (120 * scale);
        int baseHeight = (int) (60 * scale);

        int trunkHeight = (int) (0.5 * baseHeight);
        bottomBody = new Rectangle(x - (int) (0.17 * baseWidth), y + (int) (0.5 * baseHeight),
                baseWidth, trunkHeight, pink, true);
        window.add(bottomBody);
    }

    /**
     * Draws the two tires of the car.
     */
    private void drawTires() {
        int baseWidth = (int) (120 * scale);
        int baseHeight = (int) (60 * scale);

        int tireSize = (int) (0.15 * baseWidth);
        tire1 = new Oval(x - (int) (0.08 * baseWidth), y + (int) (0.8 * baseHeight), tireSize, tireSize, Color.BLACK, true);
        tire2 = new Oval(x + (int) (0.6 * baseWidth), y + (int) (0.8 * baseHeight), tireSize, tireSize, Color.BLACK, true);
        window.add(tire1);
        window.add(tire2);
    }

    /**
     * Draws the headlight and window depending on the direction the car is facing.
     */
    private void drawHeadlightAndWindow() {
        int baseWidth = (int) (120 * scale);
        int baseHeight = (int) (60 * scale);

        int lightSize = (int) (0.12 * baseWidth);
        int windowWidth = (int) (0.3 * baseWidth);
        int windowHeight = (int) (0.3 * baseHeight);

        if (direction == LEFT_MOVING) {
            headlight = new Oval(x - (int) (0.17 * baseWidth), y + (int) (0.5 * baseHeight),
                    lightSize, lightSize, Color.YELLOW, true);
            carWindow = new Rectangle(x + (int) (0.1 * baseWidth), y + (int) (0.2 * baseHeight),
                    windowWidth, windowHeight, Color.WHITE, true);
        } else {
            headlight = new Oval(x + (int) (0.72 * baseWidth), y + (int) (0.5 * baseHeight),
                    lightSize, lightSize, Color.YELLOW, true);
            carWindow = new Rectangle(x + (int) (0.3 * baseWidth), y + (int) (0.2 * baseHeight),
                    windowWidth, windowHeight, Color.WHITE, true);
        }

        window.add(headlight);
        window.add(carWindow);
    }

    /**
     * Removes all car components from the window to allow redrawing.
     */
    public void eraseCar() {
        window.remove(topBody);
        window.remove(bottomBody);
        window.remove(tire1);
        window.remove(tire2);
        window.remove(headlight);
        window.remove(carWindow);
    }

    /**
     * Moves the car horizontally in its current direction.
     * Reverses direction if it hits the edge of the window.
     *
     * @param dx The displacement along the x-axis
     */
    public void moveBy(int dx) {
        eraseCar();

        if (x <= 0) {
            direction = RIGHT_MOVING;
        } else if (x >= window.getWindowWidth()) {
            direction = LEFT_MOVING;
        }

        if (direction == RIGHT_MOVING) {
            x += dx;
        } else {
            x -= dx;
        }

        drawCar();
    }
}
