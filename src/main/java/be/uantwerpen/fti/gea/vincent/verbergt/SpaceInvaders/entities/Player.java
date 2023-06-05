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

    /**
     * The InputController.
     * Created and passed by {@link AbstractFactory}.
     */
    private final InputController inputController;

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
     * Indicates if player should deal double damage.
     */
    private boolean doubleDamage = false;

    /**
     * The thread that keeps track of the double damage time.
     */
    private Thread doubleDamgeTrackerThread = new Thread();

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

    /**
     * Add a specific amount of health to the player and notify {@link be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.GameState}.
     * @param amount The amount of health added.
     */
    public final void addHealth(int amount) {
        this.health += amount;
        abstractFactory.getGameState().setHealth(health);
    }

    /**
     * Get the doubleDamage field.
     * synchronized to prevent concurrency between threads.
     * @return {@link #doubleDamage}
     */
    public synchronized boolean isDoubleDamage() {
        return doubleDamage;
    }

    /**
     * Set the double damage field to active.
     * Then spawns a thread the sleeps for 10s and disables the doubleDamage afterwards.
     */
    public void doubleDamage() {
        doubleDamage = true;
        doubleDamgeTrackerThread.interrupt();
        doubleDamgeTrackerThread = new Thread(() -> {
            try {
                Thread.sleep(5000);
                synchronized (this) {
                    doubleDamage = false;
                }
            } catch (InterruptedException ignored) {

            }
        });
        doubleDamgeTrackerThread.start();
    }

    /**
     * Shrink the player by 75% for 10 seconds.
     */
    public void shrink() {
        size *= 3.0/4;
        new Thread(() -> {
            try {
                Thread.sleep(10000);
                synchronized (this) {
                    size *= 4.0/3;
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

}
