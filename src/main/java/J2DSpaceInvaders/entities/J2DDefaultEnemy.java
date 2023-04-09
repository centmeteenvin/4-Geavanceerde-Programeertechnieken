package J2DSpaceInvaders.entities;

import J2DSpaceInvaders.GraphicsContext;
import J2DSpaceInvaders.J2DFactory;
import SpaceInvaders.AbstractFactory;
import SpaceInvaders.entities.enemies.DefaultEnemy;
import SpaceInvaders.entities.enemies.Enemy;
import SpaceInvaders.entities.Entity;
import SpaceInvaders.entities.HittableEntity;

import java.awt.*;

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
     * Default Constructor for Enemies.<br>
     * Is called in {@link AbstractFactory#defaultEnemyCreator(Point, int, double, Point)} during levelLoading.
     *
     * @param location   {@link Entity#coordinate}.
     * @param health     {@link HittableEntity#health}.
     * @param size       {@link HittableEntity#size}.
     * @param j2DFactory {@link HittableEntity#abstractFactory}
     * @param bounds     {@link #bounds}.
     */
    public J2DDefaultEnemy(Point location, int health, double size, J2DFactory j2DFactory, Point bounds) {
        super(location, health, size, j2DFactory, bounds);
        this.graphicsContext = j2DFactory.getGraphicsContext();
    }

    /**
     * Called after updates.
     */
    @Override
    public void visualize() {
        Graphics2D graphics2D = graphicsContext.getGraphics2D();
        graphics2D.setColor(new Color((int) 255.0 * health / maxHealth, 0, 0));
        Point screenCoordinate = graphicsContext.coordinateTranslation(coordinate);
        int screenSize = graphicsContext.sizeTranslation(size);
        graphics2D.fillRect(screenCoordinate.x - screenSize / 2, screenCoordinate.y - screenSize / 2, screenSize, screenSize);
    }
}
