package be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.entities;

import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.GraphicsContext;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.J2DFactory;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.utilities.PreLoader;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.PowerUp;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The visual implementation of the powerUp
 */
public class J2DPowerUp extends PowerUp {

    /**
     * The Graphical Context we are working in.
     */
    private final GraphicsContext graphicsContext;

    /**
     * The constructor of the PowerUp
     *
     * @param location starting location
     * @param factory  {@link #abstractFactory}
     * @param type     The type of the powerUp.
     */
    public J2DPowerUp(Point location, J2DFactory factory, Type type) {
        super(location, factory, type);
        this.graphicsContext = factory.getGraphicsContext();
    }

    @Override
    public void visualize() {
        PreLoader preLoader = graphicsContext.preLoader;
        Graphics2D graphics2D = graphicsContext.getGraphics2D();
        Point screenCoordinate = graphicsContext.coordinateTranslation(coordinate);
        BufferedImage sprite;
        int screenSize = graphicsContext.sizeTranslation(size);
        switch (type) {
            case HEATH_PACKET -> {
                sprite = preLoader.fetchImage(graphicsContext.getProps().hearthPowerUpSprite);
            }
            case DOUBLE_DAMAGE -> {
                sprite = preLoader.fetchImage(graphicsContext.getProps().doubleDamagePowerUpSprite);
            }
            case EXPLOSION -> {
                sprite = preLoader.fetchImage(graphicsContext.getProps().explosionPowerUpSprite);
            }
            case SHRINK -> {
                sprite = preLoader.fetchImage(graphicsContext.getProps().shrinkPowerUpSprite);
            }
            default -> sprite = null;
        }
        graphics2D.drawImage(sprite, screenCoordinate.x - screenSize/2, screenCoordinate.y - screenSize/2, screenSize, screenSize, null);
    }
}
