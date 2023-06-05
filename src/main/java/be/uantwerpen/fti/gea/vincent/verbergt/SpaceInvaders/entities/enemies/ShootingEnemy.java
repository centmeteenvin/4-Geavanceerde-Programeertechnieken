package be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.enemies;

import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.AbstractFactory;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.Entity;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.HittableEntity;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.Event;

import java.awt.*;

/**
 * ShootingEnemy is an {@link Enemy} object that shoots semi-randomly
 */
public abstract class ShootingEnemy extends Enemy {

    /**
     * The average time before a shot is fired.
     */
    private final double averageTimeToShoot;

    /**
     * Default Constructor for Enemies.<br>
     *
     * @param location           {@link Entity#coordinate}.
     * @param health             {@link HittableEntity#health}.
     * @param size               {@link HittableEntity#size}.
     * @param abstractFactory    {@link HittableEntity#abstractFactory}
     * @param averageTimeToShoot {@link #averageTimeToShoot}
     */
    public ShootingEnemy(Point location, int health, double size, AbstractFactory abstractFactory, double averageTimeToShoot) {
        super(location, health, size, abstractFactory);
        this.averageTimeToShoot = averageTimeToShoot;
        this.dropChance = 1;
    }

    /**
     * Concrete implementation of {@link Enemy#doEnemyUpdate()}.
     * Is called at the end of {@link Enemy#doHittableEntityUpdate()}.
     * Calculates if a shot needs to be fired using a geometric distribution.
     * This means a shot will be fired on average every {@link #averageTimeToShoot} seconds.<br>
     * If a shot is fired, an {@link Event.Type#SHOOT} is created.
     */
    @Override
    public final void doEnemyUpdate() {
        //Using a geometric distribution to determine the trigger chance.
        if (shouldShoot(averageTimeToShoot)) {
            Point bulletCoordinate = new Point(coordinate.x, coordinate.y - (int) (size / 2));
            abstractFactory.getEntities().add(
                    abstractFactory.bulletCreator(bulletCoordinate, this)
            );
            abstractFactory.addEvent(new Event(Event.Type.SHOOT, this));
        }
        doShootingEnemyUpdate();
    }


    /**
     * returns a random true of false.
     * The distribution is made so that on average a true is returned every averageTimeToShoot when called every frame.
     * @param averageTimeToShoot the average time before a shot is released.
     * @return true if it shouldShoot.
     */
    public final boolean shouldShoot(double averageTimeToShoot) {
       return Math.random() <= Math.abs(1.0 - Math.pow(20.0 / 3.0, 1.0 / (abstractFactory.getSettings().getFps() * averageTimeToShoot)));
    }

    /**
     * Subclasses can implement this method if they want extra functionality during {@link Entity#update()}.
     */
    public abstract void doShootingEnemyUpdate();
}
