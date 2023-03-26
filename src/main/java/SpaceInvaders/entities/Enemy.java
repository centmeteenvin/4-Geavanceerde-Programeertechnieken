package SpaceInvaders.entities;

import SpaceInvaders.AbstractFactory;
import SpaceInvaders.Game;
import SpaceInvaders.utilities.Input;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Abstract class for enemy objects.
 * <p>
 * This class defines behaviour for Enemy Objects.<br>
 * It mainly implements {@link Entity#update()} method.
 * </p>
 */
public abstract class Enemy extends HittableEntity{

    /**
     * This Point object defines the path of the Enemy.
     * <p>
     * bounds.x indicates the leftmost coordinate of an Enemy.<br>
     * bound.y indicate the rightmost coordinate of an Enemy.<br>
     * </p>
     */
    private final Point bounds;

    /**
     * Holds the currentDirection.<br>
     * Depends on the Enemies current {@link #coordinate} in function of it's {@link #bounds}.<br>
     */
    private Input currentDirection = Input.LEFT;

    /**
     * The speed of the object.
     * This is the amount of displacement added every frame.
     */
    private final int speed;

    /**
     * The average time before a shot is fired.
     */
    private final double averageTimeToShoot = 2.5;

    /**
     * Default Constructor for Enemies.<br>
     * Is called in {@link SpaceInvaders.AbstractFactory#createEnemy(ArrayList)} during levelLoading.
     * @param location {@link Entity#coordinate}.
     * @param health {@link HittableEntity#health}.
     * @param size  {@link HittableEntity#size}.
     * @param abstractFactory {@link HittableEntity#abstractFactory}
     * @param bounds {@link #bounds}.
     */
    public Enemy(Point location, int health, double size, AbstractFactory abstractFactory, Point bounds) {
        super(location, health, size, abstractFactory);
        this.bounds = bounds;
        this.speed = abstractFactory.getSettings().getEnemySpeed();
    }

    /**
     * The doHittableEntityUpdate method for an enemy.
     * <p>
     * This method is called every frame in {@link Game#gameLoop() gameLoop()}.<br>
     * This method is final because it defines the behaviour of an Enemy.<br>
     * Inherited classes are not allowed to change the behaviour of the game.<br>
     * Checks health and direction.<br>
     * </p>
     */
    @Override
    public final void doHittableEntityUpdate() {
        if (health <= 0) {
            abstractFactory.getEntities().remove(this);
        }
        else {
            if (coordinate.x < bounds.x) {
                currentDirection = Input.RIGHT;
            }
            else if (coordinate.x > bounds.y) {
                currentDirection = Input.LEFT;
            }
            switch (currentDirection) {
                case RIGHT -> coordinate.x = coordinate.x + speed;
                case LEFT -> coordinate.x = coordinate.x - speed;
            }
            if (Math.random() <= Math.abs(1.0-Math.pow(20.0/3.0,1.0/(abstractFactory.getSettings().getFps()*averageTimeToShoot)))) //Using a geometric distribution to determine the trigger chance.
            {
                System.out.println("Shooting");
                abstractFactory.getEntities().add(
                        abstractFactory.createBullet((Point) coordinate.clone(), this)
                );
            }
        }
    }
}
