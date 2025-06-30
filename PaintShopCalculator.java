/**
 * PaintShopCalculator calculates the amount of paint needed to cover
 * a room based on its dimensions and provides a breakdown of paint
 * containers required and the total cost, applying discounts if eligible.
 *
 * Supports multiple container sizes (5 gallons down to half-pints),
 * calculates surface area in square inches, converts to gallons,
 * and determines optimal container combinations and costs.
 *
 * Also applies a discount if the cents in total cost meet specific criteria.
 * 
 * Author: Jienne Cryzelle Alegre
 */
public class PaintShopCalculator {

    // Paint container prices (in USD)
    private static final double PRICE_PER_FIVE_GALLON = 124.00;
    private static final double PRICE_PER_GALLON = 25.10;
    private static final double PRICE_PER_HALF_GALLON = 13.00;
    private static final double PRICE_PER_QUART_COUNT = 6.70;
    private static final double PRICE_PER_PINT_COUNT = 3.95;
    private static final double PRICE_PER_HALF_PINT_COUNT = 2.25;

    // Half-Pints Per Gallon
    private static final int HALF_PINTS_PER_FIVE_GALLON = 80; // 5 gallons = 80 half-pints
    private static final int HALF_PINTS_PER_GALLON = 16; // 1 gallon = 16 half-pints
    private static final int HALF_PINTS_PER_HALF_GALLON = 8; // 0.5 gallon = 8 half-pints
    private static final int HALF_PINTS_PER_QUART = 4; // 0.25 gallon = 4 half-pints
    private static final int HALF_PINTS_PER_PINT = 2; // 0.125 gallon = 2 half-pints

    // Area that can be painted with one gallon of paint (in square inches)
    private static final double AREA_PER_GALLON = 25000.0; // in square inches
    private static final double SQR_INCHES_PER_SQR_FOOT = 144.0;
    private static final double INCHES_PER_ONE_FOOT = 12.0;

    // Total gallons of paint required based on area
    private double totalGallonsNeeded;

    // Total paintable area of the room in square inches
    private double totalAreaSqIn;

    // Number of containers of each size needed
    private int fiveGallonCount;
    private int gallonCount;
    private int halfGallonCount;
    private int quartCount;
    private int pintCount;
    private int halfCount;

    // Cost per container needed
    private double costPerFiveGallonCount;
    private double costPerGallonCount;
    private double costPerOneHalfGallonCount;
    private double costPerQuartCount;
    private double costPerPintCount;
    private double costPerHalfPintCount;

    // Cost Breakdown
    private double totalCostBeforeDiscount;
    private double totalCostAfterDiscount;

    // Discount Flag
    private boolean discountApplied;

    /**
	 * Initializes this PaintShopCalculator with the room measurements.
     * Converts feet and inches measurements into decimal feet, calculates
     * total surface area, determines paint needed, and breaks down container counts and costs.
	 * 
	 * @param heightFeet   the number of feet of the height measurement
	 * @param heightInches the number of inches of the height measurement
	 * @param widthFeet    the number of feet of the width measurement
	 * @param widthInches  the number of inches of the width measurement
	 * @param lengthFeet   the number of feet of the length measurement
	 * @param lengthInches the number of inches of the length measurement
	 */
    public PaintShopCalculator(int heightFeet, int heightInches, int lengthFeet, int lengthInches, int widthFeet,
                                int widthInches) {
        // Convert height, width, and length to decimal feet
        double heightTotalFeet = heightFeet + (heightInches / INCHES_PER_ONE_FOOT); 
        double widthTotalFeet = widthFeet + (widthInches / INCHES_PER_ONE_FOOT);
        double lengthTotalFeet = lengthFeet + (lengthInches / INCHES_PER_ONE_FOOT);

        // Calculate total paintable area (walls + ceiling) in square feet
        double wallAreaSqFt = 2 * heightTotalFeet * (widthTotalFeet + lengthTotalFeet);
        double ceilingAreaSqFt = widthTotalFeet * lengthTotalFeet;
        double totalAreaSqFt = wallAreaSqFt + ceilingAreaSqFt;

        // Convert total area to square inches
        this.totalAreaSqIn = totalAreaSqFt * SQR_INCHES_PER_SQR_FOOT;

        // Calculate total gallons of paint needed
        this.totalGallonsNeeded = totalAreaSqIn / AREA_PER_GALLON;

        // Calculate total half-pints (smallest unit) needed, rounded up
        int totalHalfPints = (int) Math.ceil(totalGallonsNeeded * HALF_PINTS_PER_GALLON);

        // Calculate number of containers from largest to smallest
        this.fiveGallonCount = totalHalfPints / HALF_PINTS_PER_FIVE_GALLON;
        totalHalfPints %= HALF_PINTS_PER_FIVE_GALLON;

        this.gallonCount = totalHalfPints / HALF_PINTS_PER_GALLON;
        totalHalfPints %= HALF_PINTS_PER_GALLON;

        this.halfGallonCount = totalHalfPints / HALF_PINTS_PER_HALF_GALLON;
        totalHalfPints %= HALF_PINTS_PER_HALF_GALLON;

        this.quartCount = totalHalfPints / HALF_PINTS_PER_QUART;
        totalHalfPints %= HALF_PINTS_PER_QUART;

        this.pintCount = totalHalfPints / HALF_PINTS_PER_PINT;   
        totalHalfPints %= HALF_PINTS_PER_PINT;
        
        this.halfCount = totalHalfPints; // Half-pint gallon is the remainder rounded up

        // Calculate cost for each container size
        this.costPerFiveGallonCount = fiveGallonCount * PRICE_PER_FIVE_GALLON;
        this.costPerGallonCount = gallonCount * PRICE_PER_GALLON;
        this.costPerOneHalfGallonCount = halfGallonCount * PRICE_PER_HALF_GALLON;
        this.costPerQuartCount = quartCount * PRICE_PER_QUART_COUNT;
        this.costPerPintCount = pintCount * PRICE_PER_PINT_COUNT;
        this.costPerHalfPintCount = halfCount * PRICE_PER_HALF_PINT_COUNT;

        // Initially assume no discount
        discountApplied = false;

        // Compute total cost before discount
        this.totalCostBeforeDiscount = costPerFiveGallonCount + costPerGallonCount + costPerOneHalfGallonCount + costPerQuartCount + costPerPintCount + costPerHalfPintCount;

        // Calculate cents to determine discount eligibility
        int cents = (int) Math.round(totalCostBeforeDiscount * 100) % 100;

        // Mutable variable for total cost after discount
        this.totalCostAfterDiscount = totalCostBeforeDiscount;

        // Apply discount if cents are odd and divisible by 5
        if ((cents % 2 != 0) && (cents % 5 == 0)) {
            if (fiveGallonCount > 0) {
                totalCostAfterDiscount -= PRICE_PER_FIVE_GALLON;
                discountApplied = true;
            } else if (gallonCount > 0) {
                totalCostAfterDiscount -= PRICE_PER_GALLON;
                discountApplied = true;
            } else if (halfGallonCount > 0) {
                totalCostAfterDiscount -= PRICE_PER_HALF_GALLON;
                discountApplied = true;
            } else if (quartCount > 0) {
                totalCostAfterDiscount -= PRICE_PER_QUART_COUNT;
                discountApplied = true;
            } else if (pintCount > 0) {
                totalCostAfterDiscount -= PRICE_PER_PINT_COUNT;
                discountApplied = true;
            } else if (halfCount > 0) {
                totalCostAfterDiscount -= PRICE_PER_HALF_PINT_COUNT;
                discountApplied = true;
            }
        }

    }

    /**
     * Returns a formatted summary string of the paint calculation,
     * including surface area, gallons needed, container breakdown, costs,
     * and any discounts applied.
     * 
     * @return formatted string summary of the paint calculation and invoice
     */
    public String toString() {
         // Introductory lines: total area and gallons needed
        String line1 = "The room is " + (int)this.totalAreaSqIn + " square inches.\n"
                     + "You will need " + String.format("%.3f", this.totalGallonsNeeded) + " gallons of paint.\n";

        // Section header for breakdown of the invoice
        String line2 = "Here is your invoice:\n\n";

        // Detailed breakdown line by line
        String breakdown = "";

        // Check conditions to display container or containers and the cost of each galllon needed
        if (this.fiveGallonCount > 0) {
            if (this.fiveGallonCount == 1) {
                breakdown += "\t1 five-gallon container\t$" + String.format("%.2f", PRICE_PER_FIVE_GALLON) + "\n";
            } else {
                breakdown += "\t" + fiveGallonCount + " five-gallon containers\t$" + String.format("%.2f", this.costPerFiveGallonCount) + "\n";
            }
        }
        if (this.gallonCount > 0) {
            if (gallonCount == 1) {
                breakdown += "\t1 one-gallon container\t$" + String.format("%.2f", PRICE_PER_GALLON) + "\n";
            } else {
                breakdown += "\t" + gallonCount + " one-gallon containers\t$" + String.format("%.2f", this.costPerGallonCount) + "\n";
            }
        }

        if (this.halfGallonCount > 0) {
            if (this.halfGallonCount == 1) {
                breakdown += "\t1 half-gallon container\t$" + String.format("%.2f", PRICE_PER_HALF_GALLON) + "\n";
            } else {
                breakdown += "\t" + halfGallonCount + " half-gallon containers\t$" + String.format("%.2f", this.costPerOneHalfGallonCount) + "\n";
            }
        }

        if (this.quartCount > 0) {
            if (this.quartCount == 1) {
                breakdown += "\t1 quart container\t$" + String.format("%.2f", PRICE_PER_QUART_COUNT) + "\n";
            } else {
                breakdown += "\t" + quartCount + " quart containers\t$" + String.format("%.2f", this.costPerQuartCount) + "\n";
            }
        }

        if (this.pintCount > 0) {
            if (this.pintCount == 1) {
                breakdown += "\t1 pint container\t$" + String.format("%.2f", PRICE_PER_PINT_COUNT) + "\n";
            } else {
                breakdown += "\t" + pintCount + " pint containers\t$" + String.format("%.2f", this.costPerPintCount) + "\n";
            }
        }

        if (this.halfCount == 1) {
                breakdown += "\t1 half-pint container\t$" + String.format("%.2f", PRICE_PER_HALF_PINT_COUNT) + "\n";
        }
        

        // Divider
        String line = "\t------------------------------\n";

        // Displays the total before the discount
        String total = "\tTOTAL:\t\t$" + String.format("%.2f", this.totalCostBeforeDiscount) + "\n\n";

        // Checks if discount was applied, then this statement will be displayed
        String discountInfo = "";
        if (discountApplied) {
            discountInfo += "The amount of cents in the total price is odd and divisible by 5.\n" +
                            "One of the largest containers is free. Hurrah!\n\n";
        }

        // Displays the total after the discount, formatted two decimal places
        String totalLine = "Your final total is $" + String.format("%.2f", this.totalCostAfterDiscount) + ".\n\n";

        // Quote
        String goodbyeQuote = "\tThank you for shopping at Paint Shop!";

        // Combines all parts into one final summary string
        return line1 + line2 + breakdown + line + total + discountInfo + totalLine + goodbyeQuote;
    }
}
