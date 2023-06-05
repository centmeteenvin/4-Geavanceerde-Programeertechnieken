package be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.projectiles;

import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.AbstractFactory;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.HittableEntity;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.Vector2D;

import java.awt.*;

/**
 * A missile is slower than a {@link Bullet} but has heat seeking capabilities.
 * Is used as base class.
 * Inheriting classes should implement the {@link #doHit(HittableEntity)} method.
 */
public abstract class Missile extends Projectile {

    /**
     * The target of the missile.
     */
    private final HittableEntity target;

    /**
     * The constructor of a missile
     * @param location the Starting {@link #coordinate}.
     * @param direction the starting {@link #direction}.
     * @param owner the owner of the missile {@link #owner}.
     * @param factory {@link #factory}.
     * @param target The target the missile is tracking {@link #target}.
     */
    public Missile(Point location, Vector2D direction, HittableEntity owner, AbstractFactory factory, HittableEntity target) {
        super(location, direction, owner, factory);
        this.target = target;
    }

    /**
     * Updates the direction of missile.
     * Calculates the angle between to vector from this to the target and the current direction.
     * Rotates the direction vector by this angle.
     */
    @Override
    protected final void doProjectileUpdate() {
        direction.rotate(calculateAngle() * 0.025);
    }

    /**
     * Calculate the angle from the current direction to the target..
     * @return the angle in radians
     */
    protected final double calculateAngle() {
        Vector2D vector0ToTarget = new Vector2D(target.coordinate.x, target.coordinate.y);
        Vector2D vector2D0ToThis = new Vector2D(coordinate.x, coordinate.y);
        Vector2D vector2DThisToTarget = vector0ToTarget.subtract(vector2D0ToThis);
        return vector2DThisToTarget.angleFrom(direction);
    }


    /**
     * Is called on every object before {@link #update()}.
     * Use this to check for certain conditions that could influence behaviour.
     * This method should only change the fields of the class itself.
     */
    @Override
    public void preUpdateCheck() {

    }
}
