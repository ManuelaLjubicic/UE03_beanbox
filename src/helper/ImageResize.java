package helper;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by manue on 29.11.2015.
 */
public class ImageResize {


    public static BufferedImage scale(BufferedImage image, int maxSize){
        int width = image.getWidth();
        int height = image.getHeight();
        double newWidth = maxSize;
        double newHeight = maxSize;
        double scale;

        if(width > height){
            scale = newWidth / width;
            newHeight = height * scale;
        } else {
            scale = newHeight / height;
            newWidth = width * scale;
        }
        return resizeImage(image, (int) newWidth, (int) newHeight);
    }

    private static BufferedImage resizeImage(BufferedImage originalImage,  int width, int height){
        BufferedImage resizedImage = new BufferedImage(width, height, originalImage.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();
        return resizedImage;
    }
}
