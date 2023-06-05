package be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.entities;

import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.GraphicsContext;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.J2DFactory;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.utilities.ImageService;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.utilities.Props;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.Entity;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.HittableEntity;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Concrete Implementation of {@link Player}.
 * <p>
 * Implements the {@link Player#visualize()} method.
 * </p>
 */
public class J2DPlayer extends Player {

    /**
     * {@link J2DFactory#graphicsContext}
     */
    private final GraphicsContext graphicsContext;

    /**
     * Buffered image containing the sprite that needs to be drawn.
     * Sprite is located @ resources/J2D/player.png
     */
    private final BufferedImage sprite;

    /**
     * Default constructor for a Player.
     * Loads graphicsContext from J2DFactory.
     *
     * @param location   {@link Entity#coordinate}.
     * @param health     {@link HittableEntity#health}.
     * @param size       {@link HittableEntity#size}.
     * @param j2dFactory {@link HittableEntity#abstractFactory}
     */
    public J2DPlayer(Point location, int health, double size, J2DFactory j2dFactory) {
        super(location, health, size, j2dFactory);
        this.graphicsContext = j2dFactory.getGraphicsContext();
        Props props = j2dFactory.properties;
        sprite = graphicsContext.preLoader.fetchImage(props.playerSprite);
    }

    /**
     * Called after updates.
     */
    @Override
    public void visualize() {
        BufferedImage finalSprite = ImageService.copy(sprite);
        if (isDoubleDamage()) {
            finalSprite = ImageService.redOverlay(finalSprite,100);
        }
        Graphics2D graphics2D = graphicsContext.getGraphics2D();
        Point screenCoordinate = graphicsContext.coordinateTranslation(coordinate);
        int screenSize = graphicsContext.sizeTranslation(size);
        graphics2D.drawImage(finalSprite, screenCoordinate.x - screenSize / 2, screenCoordinate.y - screenSize / 2, screenSize, screenSize, null);
    }

}
