package Debug;

import SpaceInvaders.entity.Entity;
import SpaceInvaders.utilities.GameState;
import SpaceInvaders.entity.Player;
import SpaceInvaders.utilities.InputController;

import java.awt.*;
import java.util.ArrayList;

public class DebugPlayer extends Player {
    public DebugPlayer(Point location, double health, double size, ArrayList<Entity> entities, GameState gameState, InputController inputController) {
        super(location, health, size, entities, gameState, inputController);
    }

    @Override
    public void visualize() {

    }
}
