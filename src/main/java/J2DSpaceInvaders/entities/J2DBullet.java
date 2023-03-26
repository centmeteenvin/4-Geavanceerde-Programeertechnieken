package J2DSpaceInvaders.entities;

import J2DSpaceInvaders.GraphicsContext;
import SpaceInvaders.entities.Bullet;
import SpaceInvaders.entities.Entity;
import SpaceInvaders.entities.HittableEntity;

import java.awt.*;

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
     */
    public J2DBullet(Point location, HittableEntity owner, GraphicsContext graphicsContext) {
        super(location, owner);
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
