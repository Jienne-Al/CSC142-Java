import java.awt.Color;  // Access the Color class

// Access the graphics utilities in the UW library
import uwcse.graphics.GWindow;
import uwcse.graphics.Rectangle;

/**
 * CSC 142 Homework 1
 * 
 * This program creates a vibrant countryside scene with mountains, apple trees, 
 * cars, and rose bushes.
 * 
 * @author Jienne Cryzelle Alegre
 */

public class CountrysideScene {
    /**
     * Creates a countryside scene with mountains, rose bushes, cars, and apple trees.
     */
    public CountrysideScene() {
        // Create the graphics window with dimensions 900x700 pixels
        GWindow window = new GWindow("Countryside Scene", 900, 700);
        window.setExitOnClose();

        // Create the ground - pale green color
        Color groundColor = new Color(152, 251, 152); // Pale green
        Rectangle ground = new Rectangle(0, 0, window.getWindowWidth(), window.getWindowHeight(), groundColor, true);
        window.add(ground);

        // Create the sky - light sky blue, covering the upper half of the window
        Color skyColor = new Color(135, 206, 250); // Light sky blue
        Rectangle sky = new Rectangle(0, 0, window.getWindowWidth(), window.getWindowHeight() / 2, skyColor, true);
        window.add(sky);

        // Draw the elements in the window
        // Create mountains at various locations and scales
        new Mountain(100, 205, 1.5, window);
        new Mountain(360, 115, 2.5, window);
        new Mountain(230, 85, 3, window);

        // Add rose bushes at different locations
        new RoseBush(-15, 550, 3, window);
        new RoseBush(380, 500, 3, window);
        new RoseBush(700, 610, 2, window);

        // Create cars at different locations and scales
        new Car(30, 400, 1, window);
        new Car(200, 430, 1.5, window);

        // Create apple trees at various locations and scales
        new AppleTree(380, 70, 1.5, window);
        new AppleTree(530, 0, 2, window);
        new AppleTree(490, 255, 1, window);
        new AppleTree(730, 200, 1.5, window);

        // Show the scene
        window.doRepaint();
    }

    /**
     * Starts the application by creating an instance of the countryside scene
     */
    public static void main(String[] args) {
        new CountrysideScene();
    }
}
