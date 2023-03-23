package SpaceInvaders.entity;

import SpaceInvaders.utilities.GameState;
import SpaceInvaders.utilities.InputController;

import java.awt.*;
import java.util.ArrayList;

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
     * @param location {@link Entity#coordinate}.
     * @param health {@link HittableEntity#health}.
     * @param size {@link HittableEntity#size}.
     * @param entities {@link HittableEntity#entities}
     * @param gameState {@link #gameState}.
     * @param inputController {@link #inputController}.
     */
    public Player(Point location, double health, double size, ArrayList<Entity> entities, GameState gameState, InputController inputController) {
        super(location, health, size, entities);
        this.gameState = gameState;
        this.inputController = inputController;
    }

    /**
     * Final Implementation of {@link Entity#update()}.
     * <p>
     * Checks the health and stops game if necessary.<br>
     * Fetches the Direction and updates location accordingly.<br>
     * //TODO Shoots if indicated.
     * </p>
     */
    @Override
    public final void update() {
        if (health <= 0) {
            gameState.setPlaying(false);
        }
        else {
            switch (inputController.getDirection()) {
                case LEFT -> coordinate.x++;
                case RIGHT -> coordinate.x--;
            }
        }
    }
}
