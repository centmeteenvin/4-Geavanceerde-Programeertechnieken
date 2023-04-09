package SpaceInvaders.entities.enemies;

import SpaceInvaders.AbstractFactory;
import SpaceInvaders.entities.Entity;
import SpaceInvaders.entities.HittableEntity;

import java.awt.*;

/**
 * The default enemy class.
 * <p>
 *     Inherits from {@link Enemy}.
 *     This is the class that should be inherited if you need a normal enemy.
 * </p>
 */
public abstract class DefaultEnemy extends Enemy{
    /**
     * Default Constructor for Default:q
     * Enemies.<br>
     * Is called in {@link AbstractFactory#defaultEnemyCreator(Point, int, double, Point)} during levelLoading.
     *
     * @param location        {@link Entity#coordinate}.
     * @param health          {@link HittableEntity#health}.
     * @param size            {@link HittableEntity#size}.
     * @param abstractFactory {@link HittableEntity#abstractFactory}
     * @param bounds          {@link #bounds}.
     */
    public DefaultEnemy(Point location, int health, double size, AbstractFactory abstractFactory, Point bounds) {
        super(location, health, size, abstractFactory, bounds);
    }

    /**
     * Overwritten and finalized to prevent any code being overwritten.
     */
    @Override
    public final void doEnemyUpdate() {

    }
}
