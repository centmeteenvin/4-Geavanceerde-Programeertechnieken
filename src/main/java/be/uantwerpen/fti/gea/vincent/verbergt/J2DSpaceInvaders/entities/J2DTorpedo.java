package be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.entities;

import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.GraphicsContext;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.J2DFactory;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.utilities.Props;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.HittableEntity;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.projectiles.Torpedo;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.Vector2D;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * The concrete implementation of {@link Torpedo}.
 */
public class J2DTorpedo extends Torpedo {

    /**
     * The graphical context we are working in.
     */
    private final GraphicsContext graphicsContext;

    /**
     * The sprite of a torpedo
     */
    private BufferedImage sprite;

    /**
     * Save the last angle to prevent unnecessary rotations.
     */
    private double lastAngle = 0;

    /**
     * The maxSize the sprite can be.
     * Is the squareroot of 2 times its sides.
     */
    private final Point maxSize;

    /**
     * The constructor for a Torpedo.
     * @param location the initial location.
     * @param direction the initial direction.
     * @param owner the creator of the Torpedo.
     * @param factory {@link #factory}.
     * @param target the target we are aiming at.
     */
    public J2DTorpedo(Point location, Vector2D direction, HittableEntity owner, J2DFactory factory, HittableEntity target) {
        super(location, direction, owner, factory, target);
        this.graphicsContext = factory.getGraphicsContext();
        Props props = graphicsContext.getProps();
        sprite = graphicsContext.preLoader.fetchImage(props.torpedoSprite);
        maxSize = new Point((int) Math.ceil(Math.sqrt(2)*sprite.getWidth()), (int) Math.ceil(Math.sqrt(2)*sprite.getHeight()));
    }

    /**
     * Visualise using {@link #graphicsContext}.
     */
    @Override
    public void visualize() {
        double currentAngle = direction.getDirection() + Math.PI/2;
        if (Math.abs(lastAngle - currentAngle) > 0.05) {
            rotateSprite(lastAngle - currentAngle);
            lastAngle = currentAngle;
        }
        Graphics2D graphics2D = graphicsContext.getGraphics2D();
        Point screenCoordinate = graphicsContext.coordinateTranslation(coordinate);
        graphics2D.drawImage(sprite, screenCoordinate.x- 22, screenCoordinate.y - 22, 45, 45, null);
    }

    /**
     * Rotate the sprite by the given angle.
     * Overwrites {@link #sprite}.
     * @param angle the angle in radians
     */
    private void rotateSprite(double angle) {
        BufferedImage rotatedSprite = new BufferedImage(maxSize.x ,maxSize.y, BufferedImage.TYPE_INT_ARGB);
        Graphics2D rotatedGraphics = rotatedSprite.createGraphics();
        rotatedGraphics.rotate(angle , 1.0*rotatedSprite.getWidth()/2, 1.0*rotatedSprite.getHeight()/2);
        rotatedGraphics.drawImage(sprite, 0, 0, null);
        rotatedGraphics.dispose();
        sprite = rotatedSprite;
    }
}
