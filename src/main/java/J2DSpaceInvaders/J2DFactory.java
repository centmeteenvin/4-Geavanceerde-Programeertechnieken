package J2DSpaceInvaders;

import SpaceInvaders.AbstractFactory;
import SpaceInvaders.entities.Bullet;
import SpaceInvaders.entities.Enemy;
import SpaceInvaders.entities.HittableEntity;
import SpaceInvaders.entities.Player;
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
    public Enemy enemyCreator(Point location, double health, double size, AbstractFactory abstractFactory, Point bounds) {
        return null;
    }

    @Override
    public Player playerCreator(Point location, double health, double size, AbstractFactory abstractFactory,GameState gameState, InputController inputController) {
        return null;
    }
    @Override
    public Bullet bulletCreator(Point location, HittableEntity entity) {
        return null;
    }
}
