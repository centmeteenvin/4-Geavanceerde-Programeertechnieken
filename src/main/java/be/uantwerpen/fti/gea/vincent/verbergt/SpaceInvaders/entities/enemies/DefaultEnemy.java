package be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.enemies;

import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.AbstractFactory;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.Entity;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.HittableEntity;

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
     * Is called in {@link AbstractFactory#defaultEnemyCreator(Point, int, double)} during levelLoading.
     *
     * @param location        {@link Entity#coordinate}.
     * @param health          {@link HittableEntity#health}.
     * @param size            {@link HittableEntity#size}.
     * @param abstractFactory {@link HittableEntity#abstractFactory}
     */
    public DefaultEnemy(Point location, int health, double size, AbstractFactory abstractFactory) {
        super(location, health, size, abstractFactory);
        isFriendly = false;
    }

    /**
     * Overwritten and finalized to prevent any code being overwritten.
     */
    @Override
    public final void doEnemyUpdate() {

    }

    /**
     * Adds 1 point to the player score.
     */
    @Override
    protected final void death() {
        gameState.addScore(1);
    }

}
