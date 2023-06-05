package be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.entities.bosses;

import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.GraphicsContext;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.J2DFactory;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.utilities.ImageService;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.utilities.Props;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.enemies.bosses.Nabula;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * J2D implementation of {@link Nabula}.
 */
public class J2DNabula extends Nabula{


    /**
     * The Graphical context we are working in.
     */
    private final GraphicsContext graphicsContext;

    /**
     * The sprite for a Nabula boss.
     */
    private final BufferedImage sprite;

    /**
     * The default constructor.
     * @param location the starting location.
     * @param factory {@link #abstractFactory}.
     */
    public J2DNabula(Point location, J2DFactory factory) {
        super(location, factory);
        this.graphicsContext = factory.getGraphicsContext();
        Props props = factory.properties;
        sprite = graphicsContext.preLoader.fetchImage(props.nabulaSprite);
    }

    @Override
    public void visualize() {
        Graphics2D graphics2D = graphicsContext.getGraphics2D();
        Point screenCoordinate = graphicsContext.coordinateTranslation(coordinate);
        int screenSize = graphicsContext.sizeTranslation(size);
        graphics2D.drawImage(ImageService.redOverlay(sprite, (int) (1 + 254.0*(maxHealth-health)/(maxHealth))), screenCoordinate.x - screenSize/2, screenCoordinate.y - screenSize/2, screenSize, screenSize, null);
    }

}
