package be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities;

import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.AbstractFactory;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.Game;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.Event;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.Input;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.InputController;

import java.awt.*;

/**
 * The Abstract implementation of the Player.<br>
 * Extends {@link HittableEntity}.<br>
 * It contains the final implementation of {@link Entity#update()}
 */
public abstract class Player extends HittableEntity {

//    /**
//     * The Gamestate.
//     * The player can interact with the gameState, that's why its here.<br>
//     * //TODO might be for reconsideration.
//     */
//    private final GameState gameState;

    /**
     * The InputController.
     * Created and passed by {@link AbstractFactory}.
     */
    private InputController inputController;

    /**
     * The speed of the object.
     * This is the amount of displacement added every frame.
     */
    private final int speed;

    /**
     * Time since last shot in milliseconds.
     * Used to prevent machineGunning.
     */
    private long lastShot = System.currentTimeMillis();

    /**
     * Default constructor for a Player.
     *
     * @param location        {@link Entity#coordinate}.
     * @param health          {@link HittableEntity#health}.
     * @param size            {@link HittableEntity#size}.
     * @param abstractFactory {@link HittableEntity#abstractFactory}
     */
    public Player(Point location, int health, double size, AbstractFactory abstractFactory) {
        super(location, health, size, abstractFactory);
        this.inputController = abstractFactory.getInputController();
        this.speed = abstractFactory.getSettings().getPlayerSpeed();
        isFriendly = true;
    }

    /**
     * Final Implementation of {@link Entity#update()}.
     * <p>
     * Checks the health and stops game if necessary.<br>
     * Fetches the Direction and updates location accordingly.<br>
     * If a shot is fired, an {@link Event.Type#SHOOT} is created.
     * </p>
     */
    @Override
    public final void doHittableEntityUpdate() {
        Input currentDirection = inputController.getDirection();

        if (currentDirection == Input.LEFT & coordinate.x >= -500) coordinate.x -= speed;
        else if (currentDirection == Input.RIGHT & coordinate.x <= 500) coordinate.x += speed;

        long shootingDelay = abstractFactory.getSettings().getPlayerShootingDelay();
        if (inputController.getShooting() == Input.SHOOT && (System.currentTimeMillis() - lastShot) >= shootingDelay) {
            lastShot = System.currentTimeMillis();
            Point bulletCoordinate = new Point(coordinate.x, coordinate.y + (int) (size / 2));
            abstractFactory.getEntities().add(abstractFactory.bulletCreator( bulletCoordinate, this));
            abstractFactory.addEvent(new Event(Event.Type.SHOOT, this));
        }
        gameState.setHealth(health);
    }

    /**
     * Disabled because no checks need to happen for now.
     */
    @Override
    public final void preUpdateCheck() {
        //Empty
    }

    /**
     * Only calls the visualisation for death.
     * Playerdeath is already in {@link Game#gameLoop()}
     */
    @Override
    protected final void death() {
    }

}
