import java.awt.Color; // Access the color library
import uwcse.graphics.GWindow;
import uwcse.graphics.GWindowEvent;
import uwcse.graphics.GWindowEventAdapter;
import uwcse.graphics.Rectangle;

/**
 * CSC 142 Homework 2
 * 
 * This program creates and animates a countryside scene containing apple trees,
 * moving cars, melting mountains, and growing and shrinking flowers on bushes.
 * 
 * @author Jienne Cryzelle Alegre
 */
public class CountrysideScene extends GWindowEventAdapter {
    // The graphics window that displays the picture
    private GWindow window;

    // The elements in the picture
    private Car car1, car2;                         // Two cars moving left and right
    private Mountain mountain1, mountain2, mountain3; // Three snowcapped mountains
    private AppleTree tree1, tree2, tree3, tree4;   // Four apple trees with changing apples
    private RoseBush roseBush1, roseBush2, roseBush3; // Three bushes with growing and shrinking flowers

    // To keep track of the duration of the animation
    private int animationCounter;

    /**
	 * Creates a countryside scene
	 */
    public CountrysideScene() {
        // The graphics window which is 900 by 700 pixels
        window = new GWindow("Countryside scene", 900, 700);
        this.window.setExitOnClose();

        // The ground
        Color groundColor = new Color(152, 251, 152);
        Rectangle ground = new Rectangle(0, 0, window.getWindowWidth(), window.getWindowHeight(), groundColor, true);
        this.window.add(ground);

        // the sky covers the upper quarter of the window
        Color skyColor = new Color(135, 206, 250);
        Rectangle sky = new Rectangle(0, 0, window.getWindowWidth(), window.getWindowHeight() / 4, skyColor, true);
        this.window.add(sky);

        // Add the graphics elements
        this.addGraphicsElements();

        // Code to do the animation
        this.window.addEventHandler(this);
        this.window.startTimerEvents(150); // 150 ms per frame
    }

    /**
	 * One step of the animation
	 */
    public void timerExpired(GWindowEvent we) {
        this.window.suspendRepaints();

        // Move the cars
        this.car1.moveBy(20);
        this.car2.moveBy(20);

        // Change the colors of the apples on the trees
        this.tree1.ripen();
        this.tree2.ripen();
        this.tree3.ripen();
        this.tree4.ripen();

        // Change the size of the snow on the mountains
        this.mountain1.meltSnow(50);
        this.mountain2.meltSnow(50);
        this.mountain3.meltSnow(50);

        // Growing and shrinking flowers on the bush
        if (this.animationCounter % 10 < 5) {
            this.roseBush1.growShrink(1.02);
            this.roseBush2.growShrink(1.02);
            this.roseBush3.growShrink(1.02);
        } else {
            this.roseBush1.growShrink(0.98);
            this.roseBush2.growShrink(0.98);
            this.roseBush3.growShrink(0.98);
        }

        this.window.resumeRepaints();

        // Run the animation 100 times (about 15 s)
        this.animationCounter++;
        if (this.animationCounter >= 100)
            this.window.stopTimerEvents();
    }

    /**
     * Instantiates all graphical elements in the scene
     */
    private void addGraphicsElements() {
        // Mountains
        this.mountain1 = new Mountain(100, 60, 1.5, this.window);
        this.mountain2 = new Mountain(360, 40, 2.5, this.window);
        this.mountain3 = new Mountain(230, 15, 3.0, this.window);

        // Cars
        this.car1 = new Car(30, 360, 1, Car.RIGHT_MOVING, this.window);
        this.car2 = new Car(200, 430, 1.5, Car.LEFT_MOVING, this.window);

        // Apple trees
        this.tree1 = new AppleTree(380, 300, 1.5, this.window);
        this.tree2 = new AppleTree(530, 400, 2, this.window);
        this.tree3 = new AppleTree(490, 360, 1, this.window);
        this.tree4 = new AppleTree(730, 340, 1.5, this.window);

        // My third element a RoseBush
        this.roseBush1 = new RoseBush(0, 550, 3, this.window);
        this.roseBush2 = new RoseBush(380, 530, 3, this.window);
        this.roseBush3 = new RoseBush(700, 610, 2, this.window);
    }

    /**
     * Starts the applicatoin
     */
    public static void main(String[] args) {
        new CountrysideScene();
    }

}
