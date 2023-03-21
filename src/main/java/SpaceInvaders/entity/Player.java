package SpaceInvaders.entity;

import SpaceInvaders.utilities.GameState;
import SpaceInvaders.utilities.Input;

import java.awt.*;
import java.util.ArrayList;

public abstract class Player extends HittableEntity {
    private final GameState gameState;
    private Input currentInput = Input.LEFT;

    public Player(Point location, double health, double size, GameState gameState) {
        super(location, health, size);
        this.gameState = gameState;
    }

    @Override
    public void update(ArrayList<Entity> entities) {
        if (health <= 0) {
            gameState.setPlaying(false);
        }
        else {
            switch (currentInput) {
                case LEFT -> coordinate.x++;
                case RIGHT -> coordinate.x--;
            }
        }
    }

    public void setCurrentInput(Input currentInput) {
        this.currentInput = currentInput;
    }
}
