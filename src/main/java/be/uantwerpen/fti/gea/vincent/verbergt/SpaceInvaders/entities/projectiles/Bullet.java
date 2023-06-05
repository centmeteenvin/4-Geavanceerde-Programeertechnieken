package be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.projectiles;

import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.AbstractFactory;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.Entity;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.HittableEntity;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.Player;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.Vector2D;

import java.awt.*;


/**
 * Abstract Class Template for bullets.
 * These objects should be created in {@link AbstractFactory#bulletCreator(Point, HittableEntity)}.
 */
public abstract class Bullet extends Projectile {

    /**
     * Default Constructor for Entities.
     *
     * @param location {@link Entity#coordinate}.
     * @param owner    {@link #owner}.
     * @param factory  {@link #factory}.
     */
    public Bullet(Point location, HittableEntity owner, AbstractFactory factory) {
        super(location,new Vector2D(0, -1) , owner, factory);
        if (owner.isFriendly()) direction.y *= -1;
        double speed = factory.getSettings().getBulletSpeed();
        direction.x *= speed;
        direction.y *= speed;
    }

    /**
     * Final Implementation of {@link Entity#update()}.
     * Depending on ownership moves the bullet up or down.
     */
    @Override
    public final void doProjectileUpdate() {
        //Empty
    }

    /**
     * Called when a collision occurs.
     * This method should call the hitEntities {@link HittableEntity#getHit(int)} method.
     * Is implemented by the subclasses.
     *
     * @param hitEntity the entity we are colliding with.
     */
    @Override
    protected void doHit(HittableEntity hitEntity) {
        int damageFactor = 1;
        if (owner instanceof Player player) {
            if (player.isDoubleDamage()) {
                damageFactor = 5;
            }
        }
        hitEntity.getHit(factory.getSettings().bulletDamage*damageFactor);
    }

    /**
     * Disabled, because no checks should be done for Bullet
     */
    @Override
    public final void  preUpdateCheck() {
        //Empty
    }
}
