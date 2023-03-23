package SpaceInvaders.entity;

import SpaceInvaders.Game;
import SpaceInvaders.utilities.Input;

import java.awt.*;
import java.util.ArrayList;

/**
 * Abstract class for enemy objects.
 * <p>
 * This class defines behaviour for Enemy Objects.<br>
 * It mainly implements {@link Entity#update(ArrayList)} method.
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
     * Default Constructor for Enemies.<br>
     * Is called in {@link SpaceInvaders.AbstractFactory#createEnemy(ArrayList)} during levelLoading.
     * @param location The enemies location.
     * @param health The initial health of the Enemy.
     * @param size  The size of the enemy, important for collision detection.
     * @param bounds The path of the enemy.
     */
    public Enemy(Point location, double health, double size, Point bounds) {
        super(location, health, size);
        this.bounds = bounds;
    }

    /**
     * The update method for an enemy.
     * <p>
     * This method is called every frame in {@link Game#gameLoop() gameLoop()}.<br>
     * This method is final because it defines the behaviour of an Enemy.<br>
     * Inherited classes are not allowed to change the behaviour of the game.<br>
     * Checks health and direction.<br>
     * </p>
     * @param entities {@link Game#entities entity list} is passed because an Enemy can interact with other {@link Entity entities}.
     */
    @Override
    public final void update(ArrayList<Entity> entities) {
        if (health <= 0) {
            entities.remove(this);
        }
        else {
            if (coordinate.x < bounds.x) {
                currentDirection = Input.RIGHT;
            }
            else if (coordinate.x > bounds.y) {
                currentDirection = Input.LEFT;
            }
            switch (currentDirection) {
                case RIGHT -> coordinate.x++;
                case LEFT -> coordinate.x--;
            }
        }
    }
}
