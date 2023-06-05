package be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.utilities;

import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.GraphicsContext;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.J2DFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * The class for locating and visualising an explosion.
 */
public class Spark {

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
    private final ArrayList<BufferedImage> frames;

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
     * The Constructor for a spark.
     * @param coordinate the location of the spark.
     * @param factory factory containing {@link GraphicsContext} and {@link Props}.
     */
    public Spark(Point coordinate, J2DFactory factory) {
        this.coordinate = coordinate;
        this.graphicsContext = factory.getGraphicsContext();
        this.frames = graphicsContext.preLoader.fetchAnimation(factory.properties.sparkSprite);
        numberOfFrames = this.frames.size();
    }

    /**
     * Draw the spark on the gameScreen.
     */
    public void visualize() {
        Graphics2D graphics2D = graphicsContext.getGraphics2D();
        Point screenCoordinate = graphicsContext.coordinateTranslation(coordinate);
        graphics2D.drawImage(frames.get(frameNumber), screenCoordinate.x, screenCoordinate.y, 25, 30, null);
        divider++;
        if (divider > 0) {
            divider = 0;
            frameNumber++;
        }
    }
}
