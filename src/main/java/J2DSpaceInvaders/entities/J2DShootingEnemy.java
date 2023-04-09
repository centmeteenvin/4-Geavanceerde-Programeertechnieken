package J2DSpaceInvaders.entities;

import J2DSpaceInvaders.GraphicsContext;
import J2DSpaceInvaders.J2DFactory;
import SpaceInvaders.AbstractFactory;
import SpaceInvaders.entities.Entity;
import SpaceInvaders.entities.HittableEntity;
import SpaceInvaders.entities.enemies.Enemy;
import SpaceInvaders.entities.enemies.ShootingEnemy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * J2D Concrete implementation of {@link SpaceInvaders.entities.enemies.ShootingEnemy}.
 * <p>
 * Implements the {@link SpaceInvaders.entities.enemies.ShootingEnemy#visualize()} method.
 * </p>
 */
public class J2DShootingEnemy extends ShootingEnemy {
    /**
     * {@link J2DFactory#graphicsContext}
     */
    private GraphicsContext graphicsContext;

    /**
     * Buffered image containing the sprite that needs to be drawn.
     * Sprite is located @ resources/J2D/shooting_enemy.png
     */
    private final BufferedImage sprite;

    {
        try {
            sprite = ImageIO.read(new File("src/main/resources/J2D/shooting_enemy.png"));
        } catch (IOException e) {
            System.out.println("Sprite for ShootingEnemy Not found");
            throw new RuntimeException(e);
        }
    }

    /**
     * Default Constructor for ShootingEnemies.<br>
     * Is called in {@link AbstractFactory#shootingEnemyCreator(Point, int, double, Point, double)} during levelLoading.
     *
     * @param location   {@link Entity#coordinate}.
     * @param health     {@link HittableEntity#health}.
     * @param size       {@link HittableEntity#size}.
     * @param j2DFactory {@link HittableEntity#abstractFactory}
     * @param bounds     {@link #bounds}.
     */
    public J2DShootingEnemy(Point location, int health, double size, J2DFactory j2DFactory, Point bounds, double averageTimeToShoot) {
        super(location, health, size, j2DFactory, bounds, averageTimeToShoot);
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
        graphics2D.drawImage(sprite, screenCoordinate.x - screenSize / 2, screenCoordinate.y - screenSize / 2, screenSize, screenSize, null);
    }

}
