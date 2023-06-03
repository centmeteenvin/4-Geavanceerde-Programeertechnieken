package be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.entities.bosses;

import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.GraphicsContext;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.J2DFactory;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.utilities.Props;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.enemies.bosses.Exterminator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Exterminator is a boss that appears in level 3. It shoots on average one bullet every 10 seconds.
 * The boss is worth 10 points.
 * They have 10 health.
 * They have a size of 100
 */
public class J2DExterminator extends Exterminator {

    /**
     * {@link J2DFactory#graphicsContext}
     */
    private GraphicsContext graphicsContext;

    /**
     * Buffered image containing the sprite that needs to be drawn.
     */
    private final BufferedImage sprite;

    public J2DExterminator(Point location, J2DFactory factory) {
        super(location, factory);
        this.graphicsContext = factory.getGraphicsContext();
        Props props = factory.properties;
        try {
            sprite = ImageIO.read(new File(props.exterminatorSprite));
        } catch (IOException e) {
            System.out.println("Sprite for DefaultEnemy Not found");
            throw new RuntimeException(e);
        }
    }

    /**
     * Visualizes this.
     */
    @Override
    public void visualize() {
        Graphics2D graphics2D = graphicsContext.getGraphics2D();
        Point screenCoordinate = graphicsContext.coordinateTranslation(coordinate);
        int screenSize = graphicsContext.sizeTranslation(size);
        graphics2D.drawImage(sprite, screenCoordinate.x - screenSize/2, screenCoordinate.y - screenSize/2, screenSize, screenSize, null);
    }
}
