package J2DSpaceInvaders.entities;

import J2DSpaceInvaders.GraphicsContext;
import J2DSpaceInvaders.J2DFactory;
import SpaceInvaders.AbstractFactory;
import SpaceInvaders.entities.Entity;
import SpaceInvaders.entities.Wall;

import java.awt.*;

/**
 * The J2D implementation of {@link SpaceInvaders.entities.Wall}.
 */
public class J2DWall extends Wall {

    /**
     * {@link J2DFactory#graphicsContext}
     */
    private GraphicsContext graphicsContext;

    /**
     * Default Wall Constructor
     *
     * @param location        {@link Entity#coordinate}
     * @param health          {@link #health}
     * @param size            {@link #size}
     * @param abstractFactory {@link #abstractFactory}
     */
    public J2DWall(Point location, int health, double size, J2DFactory abstractFactory) {
        super(location, health, size, abstractFactory);
        this.graphicsContext = abstractFactory.getGraphicsContext();
    }

    /**
     * Called after updates.
     */
    @Override
    public void visualize() {
        Graphics2D graphics2D = graphicsContext.getGraphics2D();
        graphics2D.setColor(Color.green);
        Point screenCoordinate = graphicsContext.coordinateTranslation(coordinate);
        int screenSize = graphicsContext.sizeTranslation(size);
        graphics2D.fillRect(screenCoordinate.x - screenSize/2, screenCoordinate.y - screenSize/2, screenSize, screenSize);
    }
}
