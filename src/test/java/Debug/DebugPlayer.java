package Debug;

import SpaceInvaders.utilities.GameState;
import SpaceInvaders.entity.Player;

import java.awt.*;

public class DebugPlayer extends Player {
    public DebugPlayer(Point location, double health, double size, GameState gameState) {
        super(location, health, size, gameState);
    }

    @Override
    public void visualize() {

    }
}
