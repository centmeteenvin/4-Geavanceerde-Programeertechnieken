package SpaceInvaders.entities.enemies;

import SpaceInvaders.AbstractFactory;
import SpaceInvaders.Game;
import SpaceInvaders.entities.Entity;
import SpaceInvaders.entities.HittableEntity;
import SpaceInvaders.utilities.Input;

import java.awt.*;
import java.util.ArrayList;

/**
 * Abstract class for enemy objects.
 * <p>
 * This class defines behaviour for Enemy Objects.<br>
 * It mainly implements {@link Entity#update()} method.<br>
 * SHOULD NOT be used for concrete implementations, use {@link DefaultEnemy} instead.<br>
 * </p>
 */
public abstract class Enemy extends HittableEntity {

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
     * Default Constructor for Enemies.<br>
     * Is called in {@link SpaceInvaders.AbstractFactory#defaultEnemyCreator(Point, int, double, Point)} during levelLoading.
     *
     * @param location        {@link Entity#coordinate}.
     * @param health          {@link HittableEntity#health}.
     * @param size            {@link HittableEntity#size}.
     * @param abstractFactory {@link HittableEntity#abstractFactory}
     * @param bounds          {@link #bounds}.
     */
    public Enemy(Point location, int health, double size, AbstractFactory abstractFactory, Point bounds) {
        super(location, health, size, abstractFactory);
        this.bounds = bounds;
        this.speed = abstractFactory.getSettings().getEnemySpeed();
    }

    /**
     * Abstract method to extend An Enemies' abstract logic.
     * Is called at the end of {@link Enemy#doHittableEntityUpdate()}.
     */
    public abstract void doEnemyUpdate();

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
        if (coordinate.x < bounds.x) {
            currentDirection = Input.RIGHT;
        } else if (coordinate.x > bounds.y) {
            currentDirection = Input.LEFT;
        }
        switch (currentDirection) {
            case RIGHT -> coordinate.x = coordinate.x + speed;
            case LEFT -> coordinate.x = coordinate.x - speed;
        }
        doEnemyUpdate();
    }
}
