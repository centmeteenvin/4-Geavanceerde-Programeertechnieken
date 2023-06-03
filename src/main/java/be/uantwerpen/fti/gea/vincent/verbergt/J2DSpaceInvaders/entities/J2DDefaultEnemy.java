package be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.entities;

import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.utilities.Props;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.enemies.DefaultEnemy;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.GraphicsContext;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.J2DFactory;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.AbstractFactory;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.Entity;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.HittableEntity;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * J2D Concrete implementation of {@link DefaultEnemy}.
 * <p>
 * Implements the {@link DefaultEnemy#visualize()} method.
 * </p>
 */
public class J2DDefaultEnemy extends DefaultEnemy {
    /**
     * {@link J2DFactory#graphicsContext}
     */
    private GraphicsContext graphicsContext;

    /**
     * Buffered image containing the sprite that needs to be drawn.
     * Sprite is located @ resources/J2D/enemy_default.png
     */
    private final BufferedImage sprite;

    /**
     * Default Constructor for Enemies.<br>
     * Is called in {@link AbstractFactory#defaultEnemyCreator(Point, int, double)} during levelLoading.
     *
     * @param location   {@link Entity#coordinate}.
     * @param health     {@link HittableEntity#health}.
     * @param size       {@link HittableEntity#size}.
     * @param j2DFactory {@link HittableEntity#abstractFactory}
     */
    public J2DDefaultEnemy(Point location, int health, double size, J2DFactory j2DFactory) {
        super(location, health, size, j2DFactory );
        this.graphicsContext = j2DFactory.getGraphicsContext();
        Props props = j2DFactory.properties;
        try {
            sprite = ImageIO.read(new File(props.defaultEnemySprite));
        } catch (IOException e) {
            System.out.println("Sprite for DefaultEnemy Not found");
            throw new RuntimeException(e);
        }
    }

    /**
     * Called after updates.
     */
    @Override
    public void visualize() {
        Graphics2D graphics2D = graphicsContext.getGraphics2D();
//        graphics2D.setColor(new Color((int) 255.0 * health / maxHealth, 0, 0));
        Point screenCoordinate = graphicsContext.coordinateTranslation(coordinate);
        int screenSize = graphicsContext.sizeTranslation(size);
        graphics2D.drawImage(sprite, screenCoordinate.x - screenSize / 2, screenCoordinate.y - screenSize / 2, screenSize, screenSize, null);
    }

}
