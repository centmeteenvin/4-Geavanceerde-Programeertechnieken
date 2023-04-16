package J2DSpaceInvaders.entities;

import J2DSpaceInvaders.GraphicsContext;
import SpaceInvaders.entities.Bullet;
import SpaceInvaders.entities.Entity;
import SpaceInvaders.entities.HittableEntity;
import SpaceInvaders.utilities.Settings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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
     * Buffered image containing the sprite that needs to be drawn.
     * Sprite is located @ resources/J2D/bullet.png
     */
    private final BufferedImage sprite;

    {
        try {
            sprite = ImageIO.read(new File("src/main/resources/J2D/bullet.png"));
        } catch (IOException e) {
            System.out.println("Sprite for ShootingEnemy Not found");
            throw new RuntimeException(e);
        }
    }
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
        graphics2D.setColor(new Color(255, 255, 255));
        Point screenCoordinates = graphicsContext.coordinateTranslation(coordinate);
        graphics2D.drawImage(sprite, screenCoordinates.x-2,screenCoordinates.y-5, 4, 10, null);
    }
}
