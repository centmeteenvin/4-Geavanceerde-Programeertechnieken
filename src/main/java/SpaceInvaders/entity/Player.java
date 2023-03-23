package SpaceInvaders.entity;

import SpaceInvaders.utilities.GameState;
import SpaceInvaders.utilities.Input;
import SpaceInvaders.utilities.InputController;

import java.awt.*;
import java.util.ArrayList;

public abstract class Player extends HittableEntity {
    private final GameState gameState;
    private InputController inputController;

    public Player(Point location, double health, double size, GameState gameState, InputController inputController) {
        super(location, health, size);
        this.gameState = gameState;
        this.inputController = inputController;
    }

    @Override
    public void update(ArrayList<Entity> entities) {
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
