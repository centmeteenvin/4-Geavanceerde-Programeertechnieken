package be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.utilities;

import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.GraphicsContext;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.J2DFactory;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The class for locating and visualising an explosion.
 */
public class Explosion {

    /**
     * The center of the explosion.
     */
    public Point coordinate;

    /**
     * The graphical context.
     */
    private final GraphicsContext graphicsContext;

    /**
     * A sorted list of the frames from the gif.
     */
    private ArrayList<BufferedImage> frames;

    /**
     * Total number of frames.
     */
    public int numberOfFrames;

    /**
     * The current frame number.
     */
    public int frameNumber = 0;

    /**
     * This is used to slow down the animation.
     */
    private int divider = 0;

    /**
     * The constructor for an explosion.
     * @param coordinate the starting coordinate.
     * @param factory factory containing {@link GraphicsContext} and {@link Props}.
     */
    public Explosion(Point coordinate, J2DFactory factory) {
        this.coordinate = coordinate;
        this.graphicsContext = factory.getGraphicsContext();
        this.frames = getFrames(new File(factory.properties.explosionSprite));
        numberOfFrames = this.frames.size();
    }


    /**
     * Fetch the frames from a gif and store them as bufferedImages in an array.
     * @param gif the file of the gif
     * @return ArrayList with sorted bufferedImages.
     */
    private ArrayList<BufferedImage> getFrames(File gif) {
        try {
            ArrayList<BufferedImage> frames = new ArrayList<>();
            ImageReader imageReader = ImageIO.getImageReadersByFormatName("gif").next();
            ImageInputStream imageStream = ImageIO.createImageInputStream(gif);
            imageReader.setInput(imageStream);

            int totalFrames = imageReader.getNumImages(true);
            for (int i = 0; i < totalFrames; i++) {
                frames.add(imageReader.read(i));
            }
            return frames;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Draw the explosion on the gameScreen.
     */
    public void visualize() {
        Graphics2D graphics2D = graphicsContext.getGraphics2D();
        Point screenCoordinate = graphicsContext.coordinateTranslation(coordinate);
        graphics2D.drawImage(frames.get(frameNumber), screenCoordinate.x, screenCoordinate.y, 50, 50, null);
        divider++;
        if (divider > 2) {
            divider = 0;
            frameNumber++;
        }
        coordinate.y+= 2*numberOfFrames/(frameNumber+1);
    }
}
