package Debug;

import SpaceInvaders.utilities.GameState;
import SpaceInvaders.entity.Player;
import SpaceInvaders.utilities.InputController;

import java.awt.*;

public class DebugPlayer extends Player {
    public DebugPlayer(Point location, double health, double size, GameState gameState, InputController inputController) {
        super(location, health, size, gameState, inputController);
    }

    @Override
    public void visualize() {

    }
}
