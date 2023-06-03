package be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities;

import java.awt.*;

/**
 * A missile is slower than a {@link Bullet} but has heat seeking capabilities and deal double damage.
 */
public abstract class Missile extends Entity{

    /**
     * The target of the missile.
     */
    private final HittableEntity target;

    /**
     * The direction of the missile.
     * Saved as a vector.
     */
    private final Point direction;

    private final int speed;
    public Missile(Point location, HittableEntity target,double initialDirection) {
        super(location);
        this.target = target;
        speed = 0;
        direction = null;


    }

    /**
     * Called after the updates are gathered
     */
    @Override
    public void update() {

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
