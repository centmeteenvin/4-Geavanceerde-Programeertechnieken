package be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.utilities;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Image service class wrapper to do some operations on Images.
 */
public class ImageService {

    /**
     * Add a certain amount of red to all the pixels and store it in a new BufferedImage.
     * @param image the image we want to make more red.
     * @param red the amount of red we want to add.
     * @return a new buffered image copy with extra red.
     */
    public static BufferedImage redOverlay(BufferedImage image, int red) {
        BufferedImage overlay = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = overlay.createGraphics();
        graphics2D.drawImage(image,0 ,0 ,null);
        for (int x = 0; x < overlay.getWidth(); x++) {
            for (int y = 0; y < overlay.getHeight(); y++) {
                Color color = new Color(overlay.getRGB(x, y), true);
                Color modifiedColor = new Color(
                        Math.min(color.getRed()+red, 255),
                        color.getGreen(),
                        color.getBlue(),
                        color.getAlpha()
                );
                overlay.setRGB(x, y, modifiedColor.getRGB());
            }
        }
        graphics2D.dispose();
        return overlay;
    }

    /**
     * Return a copy of the original
     *
     * @param original the orignal photo
     * @return a new buffered image.
     */
    public static BufferedImage copy(BufferedImage original) {
        BufferedImage copy = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = copy.createGraphics();
        graphics2D.drawImage(original, 0, 0, null);
        graphics2D.dispose();
        return copy;
    }
}
