package be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.entities.bosses;

import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.GraphicsContext;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.J2DFactory;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.utilities.Props;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.enemies.bosses.Exterminator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Exterminator is a boss that appears in level 3. It shoots on average one bullet every 10 seconds.
 * The boss is worth 10 points.
 * They have 10 health.
 * They have a size of 100
 */
public class J2DExterminator extends Exterminator {

    /**
     * {@link J2DFactory#graphicsContext}
     */
    private GraphicsContext graphicsContext;

    /**
     * Buffered image containing the sprite that needs to be drawn.
     */
    private BufferedImage sprite;

    /**
     * The original sprite. {@link #sprite} can be colored.
     */
    private final BufferedImage originalSprite;

    /**
     * The constructor for an J2D Exterminator.
     * @param location The starting location
     * @param factory {@link #abstractFactory}.
     */
    public J2DExterminator(Point location, J2DFactory factory) {
        super(location, factory);
        this.graphicsContext = factory.getGraphicsContext();
        Props props = factory.properties;
        try {
            originalSprite = ImageIO.read(new File(props.exterminatorSprite));
            sprite = new BufferedImage(originalSprite.getWidth(), originalSprite.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D graphics2D = sprite.createGraphics();
            graphics2D.drawImage(originalSprite, 0, 0,null);
            graphics2D.dispose();
        } catch (IOException e) {
            System.out.println("Sprite for DefaultEnemy Not found");
            throw new RuntimeException(e);
        }
    }

    /**
     * Add a certain amount of red to {@link #sprite} with {@link #originalSprite} as the base.
     * @param red
     */
    private void redOverlay(double red) {
        BufferedImage overlay = new BufferedImage(originalSprite.getWidth(), originalSprite.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics2D = overlay.createGraphics();
        graphics2D.drawImage(originalSprite,0 ,0 ,null);
//        graphics2D.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5F));
//        graphics2D.setColor(Color.RED);
//        graphics2D.fillRect(0,0, sprite.getWidth(), sprite.getHeight());
        for (int x = 0; x < overlay.getWidth(); x++) {
            for (int y = 0; y < overlay.getHeight(); y++) {
                Color color = new Color(overlay.getRGB(x, y), true);
                Color modifiedColor = new Color(
                        (int) Math.min(color.getRed()+red, 255),
                        color.getGreen(),
                        color.getBlue(),
                        color.getAlpha()
                );
                overlay.setRGB(x, y, modifiedColor.getRGB());
            }
        }
        graphics2D.dispose();
        sprite = overlay;
    }

    /**
     * Visualizes this.
     */
    @Override
    public void visualize() {
        Graphics2D graphics2D = graphicsContext.getGraphics2D();
        Point screenCoordinate = graphicsContext.coordinateTranslation(coordinate);
        int screenSize = graphicsContext.sizeTranslation(size);
        graphics2D.drawImage(sprite, screenCoordinate.x - screenSize/2, screenCoordinate.y - screenSize/2, screenSize, screenSize, null);
        redOverlay(1 + 254.0*(maxHealth-health)/(maxHealth));
    }
}
