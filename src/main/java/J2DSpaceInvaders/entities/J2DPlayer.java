package J2DSpaceInvaders.entities;

import J2DSpaceInvaders.GraphicsContext;
import J2DSpaceInvaders.J2DFactory;
import SpaceInvaders.AbstractFactory;
import SpaceInvaders.entities.Entity;
import SpaceInvaders.entities.HittableEntity;
import SpaceInvaders.entities.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Concrete Implementation of {@link Player}.
 * <p>
 * Implements the {@link Player#visualize()} method.
 * </p>
 */
public class J2DPlayer extends Player {

    /**
     * {@link J2DFactory#graphicsContext}
     */
    private GraphicsContext graphicsContext;

    /**
     * Buffered image containing the sprite that needs to be drawn.
     * Sprite is located @ resources/J2D/player.png
     */
    private final BufferedImage sprite;

    {
        try {
            sprite = ImageIO.read(new File("src/main/resources/J2D/player.png"));
        } catch (IOException e) {
            System.out.println("Sprite for ShootingEnemy Not found");
            throw new RuntimeException(e);
        }
    }
    /**
     * Default constructor for a Player.
     * Loads graphicsContext from J2DFactory.
     *
     * @param location   {@link Entity#coordinate}.
     * @param health     {@link HittableEntity#health}.
     * @param size       {@link HittableEntity#size}.
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
        graphics2D.drawImage(sprite, screenCoordinate.x - screenSize / 2, screenCoordinate.y - screenSize / 2, screenSize, screenSize, null);
    }
}
