package ImageScanner;

import java.awt.Frame;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import net.sourceforge.javaocr.ocrPlugins.mseOCR.CharacterRange;
import net.sourceforge.javaocr.ocrPlugins.mseOCR.OCRScanner;
import net.sourceforge.javaocr.ocrPlugins.mseOCR.TrainingImage;
import net.sourceforge.javaocr.ocrPlugins.mseOCR.TrainingImageLoader;
import net.sourceforge.javaocr.scanner.PixelImage;

public class ImageScanner {
    public static void main(String[] args) throws Exception {
        OCRScanner scanner = new OCRScanner();
        TrainingImageLoader loader = new TrainingImageLoader();
        Frame frame = new Frame();
        for (int i = 35; i <= 126; i++) {
            HashMap<Character, ArrayList<TrainingImage>> trainingImageMap = new HashMap<Character, ArrayList<TrainingImage>>();
            loader.load(frame, "OCR Test2/" + i + ".png", new CharacterRange(i), trainingImageMap);
            scanner.addTrainingImages(trainingImageMap);
        }       

        Image image = ImageIO.read(new File("asciiArialTest.png"));
        PixelImage pixelImage = new PixelImage(image);
        pixelImage.toGrayScale(true);
        pixelImage.filter();

        String text = scanner.scan(image, 0, 0, 0, 0, null);
        System.out.println(" :::" +text+ ":::");
    }
}