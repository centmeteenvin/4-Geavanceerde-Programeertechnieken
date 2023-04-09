package SpaceInvaders.entities.enemies;

import SpaceInvaders.AbstractFactory;
import SpaceInvaders.entities.Entity;
import SpaceInvaders.entities.HittableEntity;

import java.awt.*;
import java.util.ArrayList;

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
     * Is called in {@link AbstractFactory#createEnemy(ArrayList)} during levelLoading.
     *
     * @param location        {@link Entity#coordinate}.
     * @param health          {@link HittableEntity#health}.
     * @param size            {@link HittableEntity#size}.
     * @param abstractFactory {@link HittableEntity#abstractFactory}
     * @param bounds          {@link Enemy#bounds}.
     * @param averageTimeToShoot {@link #averageTimeToShoot}
     */
    public ShootingEnemy(Point location, int health, double size, AbstractFactory abstractFactory, Point bounds, double averageTimeToShoot) {
        super(location, health, size, abstractFactory, bounds);
        this.averageTimeToShoot = averageTimeToShoot;
    }

    /**
     * Concrete implementation of {@link Enemy#doEnemyUpdate()}.
     * Is called at the end of {@link Enemy#doHittableEntityUpdate()}.
     */
    @Override
    public void doEnemyUpdate() {
        if (Math.random() <= Math.abs(1.0-Math.pow(20.0/3.0,1.0/(abstractFactory.getSettings().getFps()*averageTimeToShoot)))) //Using a geometric distribution to determine the trigger chance.
        {
            abstractFactory.getEntities().add(
                    abstractFactory.bulletCreator((Point) coordinate.clone(), this)
            );
        }
    }
}
