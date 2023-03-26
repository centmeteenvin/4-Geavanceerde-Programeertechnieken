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
     * Default constructor for a Player.
     *
     * @param location        {@link Entity#coordinate}.
     * @param health          {@link HittableEntity#health}.
     * @param size            {@link HittableEntity#size}.
     * @param abstractFactory {@link HittableEntity#abstractFactory}
     */
    public Player(Point location, double health, double size, AbstractFactory abstractFactory) {
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
                case LEFT -> coordinate.x--;
                case RIGHT -> coordinate.x++;
            }
            if (inputController.getShooting() == Input.SHOOT) {
                abstractFactory.getEntities().add(abstractFactory.createBullet((Point) coordinate.clone(), this));
            }
        }
    }
}
