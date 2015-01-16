import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;

public class App {

	public static void resize(String inputImagePath, String outputImagePath,
			int scaledWidth, int scaledHeight) throws IOException {

		File inputFile = new File(inputImagePath);
		BufferedImage inputImage = ImageIO.read(inputFile);

		BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());

		Graphics2D g2d = outputImage.createGraphics();
		g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
		g2d.dispose();

		String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);

		ImageIO.write(outputImage, formatName, new File(outputImagePath));
	}

	public static void resize(String inputImagePath, String outputImagePath,
			double percent) throws IOException {
		
		File inputFile = new File(inputImagePath);
		BufferedImage inputImage = ImageIO.read(inputFile);
		
		int scaledWidth = (int) (inputImage.getWidth() * percent / 100);
		int scaledHeight = (int) (inputImage.getHeight() * percent / 100);
		
		resize(inputImagePath, outputImagePath, scaledWidth, scaledHeight);
	}

	public static void main(String[] args) throws Exception {

		if (args.length == 4) /* Width and height were inputed */
		{
			try {
				App.resize(args[0], args[1], Integer.parseInt(args[2]), Integer.parseInt(args[3]));
			} catch (NumberFormatException exc) {
				System.out.println("Values of width or height are incorrect");
			} catch (IIOException exc) {
				System.out.println("Input file doesn't exist");
			} catch (FileNotFoundException exc) {
				System.out.println("Invalid path of output file");
			}
			
		} else if (args.length == 3) { /* Percents were inputed */
			try {
				App.resize(args[0], args[1], Integer.parseInt(args[2]));
			} catch (NumberFormatException exc) {
				System.out.println("Value of percents is incorrect");
			} catch (IIOException exc) {
				System.out.println("Input file doesn't exist");
			} catch (FileNotFoundException exc) {
				System.out.println("Invalid path of output file");
			}
			
		} else if (args.length != 3 | args.length != 4) { /* Incorrect arguments */
			System.out.println("Wrong number of arguments. Type these args: [input_file_path output_file_path width height] OR [input_file_path output_file_path percents]");
		}

	}
}