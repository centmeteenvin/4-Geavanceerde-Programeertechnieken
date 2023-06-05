package be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.projectiles;

import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.AbstractFactory;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.HittableEntity;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.Vector2D;

import java.awt.*;

/**
 * A Torpedo is a {@link Missile} That deals 10 damage to the target it hits.
 */
public abstract class Torpedo extends Missile {

    /**
     * The default constructor for a Torpedo.
     * @param location the starting location.
     * @param direction the initial direction.
     * @param owner the {@link HittableEntity} that fires the torpedo.
     * @param factory {@link #factory}
     * @param target the target we are aiming at.
     */
    public Torpedo(Point location, Vector2D direction, HittableEntity owner, AbstractFactory factory, HittableEntity target) {
        super(location, direction, owner, factory, target);
    }

    @Override
    protected final void doHit(HittableEntity hitEntity) {
        hitEntity.getHit(2);
    }

    @Override
    public final void preUpdateCheck() {
        //EMPTY
    }
}
