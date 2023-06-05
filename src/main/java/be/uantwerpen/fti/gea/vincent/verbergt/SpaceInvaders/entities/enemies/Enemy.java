package be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.enemies;

import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.AbstractFactory;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.Game;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.Entity;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.HittableEntity;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.Player;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.PowerUp;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.Constants;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.Input;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
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
     * The chance a bonus drops on death.
     */
    protected double dropChance = 0.25;


    /**
     * Default Constructor for Enemies.<br>
     * Is called in {@link AbstractFactory#defaultEnemyCreator(Point, int, double)} during levelLoading.
     *
     * @param location        {@link Entity#coordinate}.
     * @param health          {@link HittableEntity#health}.
     * @param size            {@link HittableEntity#size}.
     * @param abstractFactory {@link HittableEntity#abstractFactory}
     */
    public Enemy(Point location, int health, double size, AbstractFactory abstractFactory) {
        super(location, health, size, abstractFactory);
        this.speed = abstractFactory.getSettings().getEnemySpeed();
        this.dropChance = 0.50;
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

        //
        switch (currentDirection) {
            case RIGHT -> coordinate.x += speed;
            case LEFT -> coordinate.x -= speed;
        }

        //End the game if an enemy touches the bottom of the field.
        if (coordinate.y <= Constants.FIELD_Y_LOWER) {
            abstractFactory.getEntities().remove(fetchPlayer());
        }
        doEnemyUpdate();
    }

    /**
     * Checks if we need to change direction after before updates are done;
     */
    @Override
    public final void preUpdateCheck() {

        //filter all enemies
        ArrayList<Enemy> enemies = abstractFactory.getEntities().stream().filter(entity -> entity instanceof Enemy).map(entity -> (Enemy) entity).collect(Collectors.toCollection(ArrayList::new));

        // check if any of the enemies will collide with the wall
        if (enemies.stream().anyMatch(enemy -> enemy.coordinate.x - enemy.size / 2 <= Constants.FIELD_X_LOWER)) {
            currentDirection = Input.RIGHT;
            coordinate.y -= 30;
        } else if (enemies.stream().anyMatch(enemy -> enemy.coordinate.x + enemy.size / 2 >= Constants.FIELD_X_UPPER)) {
            currentDirection = Input.LEFT;
            coordinate.y -= 30;
        }
    }

    /**
     * Chance to drop powerUp.
     */
    @Override
    protected final void death() {
        if (Math.random() < dropChance) {
            PowerUp.Type[] types = PowerUp.Type.values();
            int randomIndex = new Random().nextInt(types.length);
            PowerUp.Type type = types[randomIndex];
            abstractFactory.getEntities().add(
                    abstractFactory.powerUpCreator((Point) coordinate.clone(), type)
            );
        }
        doEnemyDeath();
    }

    /**
     * Ensure the {@link #death()} is Always called.
     */
    protected abstract void doEnemyDeath();

    /**
     * Get the player from the entity list.
     *
     * @return null if the players wasn't found.
     */
    private Player fetchPlayer() {
        ArrayList<Entity> entities = abstractFactory.getEntities();
        return entities.stream()
                .filter(entity -> entity instanceof Player)
                .map(entity -> (Player) entity)
                .findFirst()
                .orElse(null);
    }
}
