package SpaceInvaders.entities;

import SpaceInvaders.AbstractFactory;
import SpaceInvaders.utilities.GameState;
import SpaceInvaders.utilities.Input;
import SpaceInvaders.utilities.InputController;

import java.awt.*;

/**
 * The Abstract implementation of the Player.<br>
 * Extends {@link HittableEntity}.<br>
 * It contains the final implementation of {@link Entity#update()}
 */
public abstract class Player extends HittableEntity {

    /**
     * The Gamestate.
     * The player can interact with the gameState, that's why its here.<br>
     * //TODO might be for reconsideration.
     */
    private final GameState gameState;

    /**
     * The InputController.
     * Created and passed by {@link SpaceInvaders.AbstractFactory}.
     */
    private InputController inputController;

    /**
     * The speed of the object.
     * This is the amount of displacement added every frame.
     */
    private final int speed = 10;

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
        this.gameState = abstractFactory.getGameState();
        this.inputController = abstractFactory.getInputController();
    }

    /**
     * Final Implementation of {@link Entity#update()}.
     * <p>
     * Checks the health and stops game if necessary.<br>
     * Fetches the Direction and updates location accordingly.<br>
     * </p>
     */
    @Override
    public final void doHittableEntityUpdate() {
        if (health <= 0) {
            gameState.setPlaying(false);
        }
        else {
            switch (inputController.getDirection()) {
                case LEFT -> coordinate.x = coordinate.x - speed;
                case RIGHT -> coordinate.x = coordinate.x + speed;
            }

            long shootingDelay = abstractFactory.getSettings().getPlayerShootingDelay();
            if (inputController.getShooting() == Input.SHOOT && (System.currentTimeMillis() - lastShot) >=  shootingDelay) {
                lastShot = System.currentTimeMillis();
                abstractFactory.getEntities().add(abstractFactory.createBullet((Point) coordinate.clone(), this));
            }
        }
    }
}
