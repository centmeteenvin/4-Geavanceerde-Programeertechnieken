package J2DSpaceInvaders.entities;

import J2DSpaceInvaders.GraphicsContext;
import SpaceInvaders.entities.Bullet;
import SpaceInvaders.entities.Entity;
import SpaceInvaders.entities.HittableEntity;
import SpaceInvaders.utilities.Settings;

import java.awt.*;

/**
 * The J2D implementation of a bullet.
 * <p>
 * Implements {@link Bullet#visualize()}.
 * </p>
 */
public class J2DBullet extends Bullet {
    /**
     * {@link J2DSpaceInvaders.J2DFactory#graphicsContext}.
     */
    private GraphicsContext graphicsContext;

    /**
     * Default Constructor for Entities.
     *
     * @param location {@link Entity#coordinate}
     * @param owner    {@link #owner}
     * @param settings Used as a getter for {@link #speed}
     * @param graphicsContext {@link #graphicsContext}
     */
    public J2DBullet(Point location, HittableEntity owner, Settings settings, GraphicsContext graphicsContext) {
        super(location, owner, settings);
        this.graphicsContext = graphicsContext;
    }

    /**
     * Called after updates.
     */
    @Override
    public void visualize() {
        Graphics2D graphics2D = graphicsContext.getGraphics2D();
        graphics2D.setColor(new Color(0));
        Point screenCoordinates = graphicsContext.coordinateTranslation(coordinate);
        graphics2D.fillRect(screenCoordinates.x,screenCoordinates.y, 3, 10);
    }
}
