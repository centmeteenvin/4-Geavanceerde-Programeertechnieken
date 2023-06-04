package be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.projectiles;

import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.Entity;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.HittableEntity;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.projectiles.Bullet;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.Vector2D;

import java.awt.*;

/**
 * A missile is slower than a {@link Bullet} but has heat seeking capabilities.
 * Is used as base class.
 */
public abstract class Missile extends Projectile {

    /**
     * The target of the missile.
     */
    private final HittableEntity target;

    /**
     * Updates the direction of missile.
     */
    @Override
    protected void doProjectileUpdate() {
        Vector2D targetVector = new Vector2D(target.coordinate.x, target.coordinate.y);
        double angle
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
