package J2DSpaceInvaders;

import SpaceInvaders.AbstractFactory;
import SpaceInvaders.entity.Enemy;
import SpaceInvaders.entity.Player;
import SpaceInvaders.utilities.GameState;
import SpaceInvaders.utilities.InputController;

import java.awt.*;

public class J2DFactory extends AbstractFactory {


    @Override
    public void initialize() {

    }

    @Override
    public void render() {

    }

    @Override
    public Enemy enemyCreator(Point location, double health, double size, Point bounds) {
        return null;
    }

    @Override
    public Player playerCreator(Point location, double health, double size, GameState gameState, InputController inputController) {
        return null;
    }
}
