package be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.entities;

import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.GraphicsContext;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.J2DFactory;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.utilities.Props;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.Entity;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.HittableEntity;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.projectiles.Bullet;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The J2D implementation of a bullet.
 * <p>
 * Implements {@link Bullet#visualize()}.
 * </p>
 */
public class J2DBullet extends Bullet {
    /**
     * {@link J2DFactory#graphicsContext}.
     */
    private final GraphicsContext graphicsContext;

    /**
     * Buffered image containing the sprite that needs to be drawn.
     * Depends the eventual sprite depends on {@link #owner}
     */
    private final BufferedImage sprite;

    /**
     * Default Constructor for Entities.
     *
     * @param location {@link Entity#coordinate}
     * @param owner    {@link #owner}
     * @param factory  used to retrieve {@link #graphicsContext} and {@link J2DFactory#properties}.
     */
    public J2DBullet(Point location, HittableEntity owner, J2DFactory factory) {
        super(location, owner, factory);
        this.graphicsContext = factory.getGraphicsContext();
        Props props = factory.properties;
        if (owner.isFriendly()) {
            sprite = graphicsContext.preLoader.fetchImage(props.friendlyBulletSprite);
        } else {
            sprite = graphicsContext.preLoader.fetchImage(props.hostileBulletSprite);
        }
    }

    /**
     * Called after updates.
     */
    @Override
    public void visualize() {
        Graphics2D graphics2D = graphicsContext.getGraphics2D();
        graphics2D.setColor(new Color(255, 255, 255));
        Point screenCoordinates = graphicsContext.coordinateTranslation(coordinate);
        graphics2D.drawImage(sprite, screenCoordinates.x - 2, screenCoordinates.y - 45 / 2, 8, 45, null);
    }
}
