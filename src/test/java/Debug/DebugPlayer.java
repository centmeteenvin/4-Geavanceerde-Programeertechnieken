package Debug;

import game.utilities.GameState;
import game.entity.Player;

import java.awt.*;

public class DebugPlayer extends Player {
    public DebugPlayer(Point location, double health, double size, GameState gameState) {
        super(location, health, size, gameState);
    }

    @Override
    public void visualize() {

    }
}
