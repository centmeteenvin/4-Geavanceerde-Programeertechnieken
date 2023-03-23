package Debug;

import SpaceInvaders.AbstractFactory;
import SpaceInvaders.utilities.GameState;
import SpaceInvaders.entities.Player;
import SpaceInvaders.utilities.InputController;

import java.awt.*;

public class DebugPlayer extends Player {
    public DebugPlayer(Point location, double health, double size, AbstractFactory entities, GameState gameState, InputController inputController) {
        super(location, health, size, entities, gameState, inputController);
    }

    @Override
    public void visualize() {

    }
}
