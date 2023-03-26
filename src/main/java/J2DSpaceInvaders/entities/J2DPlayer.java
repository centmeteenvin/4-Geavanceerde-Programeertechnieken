package J2DSpaceInvaders.entities;

import J2DSpaceInvaders.GraphicsContext;
import J2DSpaceInvaders.J2DFactory;
import SpaceInvaders.AbstractFactory;
import SpaceInvaders.entities.Entity;
import SpaceInvaders.entities.HittableEntity;
import SpaceInvaders.entities.Player;

import java.awt.*;

public class J2DPlayer extends Player {

    /**
     * {@link J2DFactory#graphicsContext}
     */
    private GraphicsContext graphicsContext;

    /**
     * Default constructor for a Player.
     * Loads graphicsContext from J2DFactory.
     * @param location        {@link Entity#coordinate}.
     * @param health          {@link HittableEntity#health}.
     * @param size            {@link HittableEntity#size}.
     * @param j2dFactory {@link HittableEntity#abstractFactory}
     */
    public J2DPlayer(Point location, int health, double size, J2DFactory j2dFactory) {
        super(location, health, size, j2dFactory);
        this.graphicsContext = j2dFactory.getGraphicsContext();
    }

    /**
     * Called after updates.
     */
    @Override
    public void visualize() {
        Graphics2D graphics2D = graphicsContext.getGraphics2D();
        graphics2D.setColor(new Color(0x01019A));
        Point screenCoordinate = graphicsContext.coordinateTranslation(coordinate);
        int screenSize = graphicsContext.sizeTranslation(size);
        graphics2D.fillRect(screenCoordinate.x, screenCoordinate.y, screenSize, screenSize);
    }
}
