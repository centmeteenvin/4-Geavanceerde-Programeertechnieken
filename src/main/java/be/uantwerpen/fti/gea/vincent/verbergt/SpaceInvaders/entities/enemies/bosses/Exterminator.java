package be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.enemies.bosses;

import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.AbstractFactory;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.enemies.ShootingEnemy;

import java.awt.*;

/**
 * Exterminator is a boss that appears in level 3. It shoots on average one bullet every 5-ish seconds.
 * The boss is worth 10 points.
 * They have 10 health.
 * They have a size of 100
 */
public abstract class Exterminator extends ShootingEnemy {
    /**
     * The constructor for this
     * @param location starting location
     * @param abstractFactory {@link #abstractFactory}
     */
    public Exterminator(Point location, AbstractFactory abstractFactory) {
        super(location, 10, 100, abstractFactory, 5);
    }

    /**
     * Add 10 points to the score.
     */
    @Override
    protected void death() {
        gameState.addScore(10);
    }

    /**
     * Left empty, not needed.
     */
    @Override
    public final void doHittableEnemyUpdate() {
        //Empty
    }
}
