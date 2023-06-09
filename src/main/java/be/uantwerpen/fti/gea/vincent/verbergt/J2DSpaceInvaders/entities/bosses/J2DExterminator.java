package be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.entities.bosses;

import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.GraphicsContext;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.J2DFactory;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.utilities.ImageService;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.utilities.Props;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.enemies.bosses.Exterminator;

import java.awt.*;
import java.awt.image.BufferedImage;

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
    private final GraphicsContext graphicsContext;

    /**
     * Buffered image containing the sprite that needs to be drawn.
     */
    private final BufferedImage sprite;


    /**
     * The constructor for an J2D Exterminator.
     * @param location The starting location
     * @param factory {@link #abstractFactory}.
     */
    public J2DExterminator(Point location, J2DFactory factory) {
        super(location, factory);
        this.graphicsContext = factory.getGraphicsContext();
        Props props = factory.properties;
        sprite = graphicsContext.preLoader.fetchImage(props.exterminatorSprite);
    }

    /**
     * Visualizes this.
     */
    @Override
    public void visualize() {
        Graphics2D graphics2D = graphicsContext.getGraphics2D();
        Point screenCoordinate = graphicsContext.coordinateTranslation(coordinate);
        int screenSize = graphicsContext.sizeTranslation(size);
        graphics2D.drawImage(ImageService.redOverlay(sprite, (int) (1 + 254.0*(maxHealth-health)/(maxHealth))), screenCoordinate.x - screenSize/2, screenCoordinate.y - screenSize/2, screenSize, screenSize, null);
    }
}
