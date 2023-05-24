package SpaceInvaders.entities.enemies;

import SpaceInvaders.AbstractFactory;
import SpaceInvaders.Game;
import SpaceInvaders.entities.Entity;
import SpaceInvaders.entities.HittableEntity;
import SpaceInvaders.utilities.Constants;
import SpaceInvaders.utilities.Input;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

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
     * Holds the currentDirection.<br>
     * Depends on the Enemies current {@link #coordinate}.<br>
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
     */
    public Enemy(Point location, int health, double size, AbstractFactory abstractFactory) {
        super(location, health, size, abstractFactory);
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
        ArrayList<Enemy> enemies = abstractFactory.getEntities().stream().filter(entity -> entity instanceof Enemy).map(entity -> (Enemy) entity).collect(Collectors.toCollection(ArrayList::new));
        if (enemies.stream().anyMatch(enemy -> enemy.coordinate.x - enemy.size/2 <= Constants.FIELD_X_LOWER)) {
            currentDirection = Input.RIGHT;
            coordinate.y -= 10;
        }
        else if (enemies.stream().anyMatch(enemy -> enemy.coordinate.x + enemy.size/2 >= Constants.FIELD_X_UPPER)) {
            currentDirection = Input.LEFT;
            coordinate.y -= 10;
        }
        switch (currentDirection) {
            case RIGHT -> coordinate.x += speed;
            case LEFT -> coordinate.x -= speed;
        }
        doEnemyUpdate();
    }
}
