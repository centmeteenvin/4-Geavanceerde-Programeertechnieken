package J2DSpaceInvaders.entities;

import J2DSpaceInvaders.GraphicsContext;
import J2DSpaceInvaders.J2DFactory;
import SpaceInvaders.AbstractFactory;
import SpaceInvaders.entities.Enemy;
import SpaceInvaders.entities.Entity;
import SpaceInvaders.entities.HittableEntity;

import java.awt.*;
import java.util.ArrayList;

/**
 * J2D Concrete implementation of {@link Enemy}.
 * <p>
 * Implements the {@link Enemy#visualize()} method.
 * </p>
 */
public class J2DEnemy extends Enemy {
    /**
     * {@link J2DFactory#graphicsContext}
     */
    private GraphicsContext graphicsContext;

    /**
     * Default Constructor for Enemies.<br>
     * Is called in {@link AbstractFactory#createEnemy(ArrayList)} during levelLoading.
     *
     * @param location   {@link Entity#coordinate}.
     * @param health     {@link HittableEntity#health}.
     * @param size       {@link HittableEntity#size}.
     * @param j2DFactory {@link HittableEntity#abstractFactory}
     * @param bounds     {@link #bounds}.
     */
    public J2DEnemy(Point location, int health, double size, J2DFactory j2DFactory, Point bounds) {
        super(location, health, size, j2DFactory, bounds);
        this.graphicsContext = j2DFactory.getGraphicsContext();
    }

    /**
     * Called after updates.
     */
    @Override
    public void visualize() {
        Graphics2D graphics2D = graphicsContext.getGraphics2D();
        graphics2D.setColor(new Color(0xA70101));
        Point screenCoordinate = graphicsContext.coordinateTranslation(coordinate);
        int screenSize = graphicsContext.sizeTranslation(size);
        graphics2D.fillRect(screenCoordinate.x - screenSize / 2, screenCoordinate.y - screenSize / 2, screenSize, screenSize);
    }
}
