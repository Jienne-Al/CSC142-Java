import java.awt.image.*;

/**
 * Provides an interface to a picture as an array of Pixels
 */
public class PixelImage {
	private BufferedImage myImage;
	private int width;
	private int height;

	/**
	 * Map this PixelImage to a real image
	 * 
	 * @param bi
	 *            The image
	 */
	public PixelImage(BufferedImage bi) {
		// initialise instance variables
		this.myImage = bi;
		this.width = bi.getWidth();
		this.height = bi.getHeight();
	}

	/**
	 * Return the width of the image
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Return the height of the image
	 */
	public int getHeight() {
		return this.height;
	}

	/**
	 * Return the BufferedImage of this PixelImage
	 */
	public BufferedImage getImage() {
		return this.myImage;
	}

	/**
	 * Return the image's pixel data as an array of Pixels. The first coordinate
	 * is the x-coordinate, so the size of the array is [width][height], where
	 * width and height are the dimensions of the array
	 * 
	 * @return The array of pixels
	 */
	public Pixel[][] getData() {
		Raster r = this.myImage.getRaster();
		Pixel[][] data = new Pixel[r.getHeight()][r.getWidth()];
		int[] samples = new int[3];

		for (int row = 0; row < r.getHeight(); row++) {
			for (int col = 0; col < r.getWidth(); col++) {
				samples = r.getPixel(col, row, samples);
				Pixel newPixel = new Pixel(samples[0], samples[1], samples[2]);
				data[row][col] = newPixel;
			}
		}

		return data;
	}

	/**
	 * Set the image's pixel data from an array. This array matches that
	 * returned by getData(). It is an error to pass in an array that does not
	 * match the image's dimensions or that has pixels with invalid values (not
	 * 0-255)
	 * 
	 * @param data
	 *            The array to pull from
	 */
	public void setData(Pixel[][] data) {
		int[] pixelValues = new int[3]; // a temporary array to hold r,g,b
										// values
		WritableRaster wr = this.myImage.getRaster();

		if (data.length != wr.getHeight()) {
			throw new IllegalArgumentException("Array size does not match");
		} else if (data[0].length != wr.getWidth()) {
			throw new IllegalArgumentException("Array size does not match");
		}

		for (int row = 0; row < wr.getHeight(); row++) {
			for (int col = 0; col < wr.getWidth(); col++) {
				pixelValues[0] = data[row][col].red;
				pixelValues[1] = data[row][col].green;
				pixelValues[2] = data[row][col].blue;
				wr.setPixel(col, row, pixelValues);
			}
		}
	}

	/**
	*Applies a 3x3 weighted filter to the image.
	*@param weights A 3x3 array of integers representing the convolution kernel. 
	*/
	public void transform(int[][] weights) {
	    int weightSum = 0;

	    // Sum the weights
	    for (int i = 0; i < 3; i++) {
	        for (int j = 0; j < 3; j++) {
	            weightSum += weights[i][j];
	        }
	    }
	    if (weightSum == 0) {
	    	weightSum = 1;
	    }

	    Pixel[][] dataFrom = this.getData();
	    Pixel[][] dataTo = this.getData();

	    for (int row = 1; row < height - 1; row++) {
	        for (int col = 1; col < width - 1; col++) {
	            int red = 0, green = 0, blue = 0;

	            for (int i = -1; i <= 1; i++) {
	                for (int j = -1; j <= 1; j++) {
	                    Pixel p = dataFrom[row + i][col + j];
	                    int weight = weights[i + 1][j + 1];
	                    red += p.red * weight;
	                    green += p.green * weight;
	                    blue += p.blue * weight;
	                }
	            }

	            red = color(red / weightSum);
	            green = color(green / weightSum);
	            blue = color(blue / weightSum);

	            dataTo[row][col] = new Pixel(red, green, blue);
	        }
	    }

	    this.setData(dataTo);
	}

	// Helper method to keep values between 0 and 255
	public static int color(int value) {
		if (value < 0) {
			return 0;
		}
		if (value > 255) {
			return 255;
		}
		return value;
	}

	public static int coordinate(int value, int max) {
		if (value < 0) {
        return 0;
        }
        if (value > max) {
        return max;
        }
        return value;
    }

}