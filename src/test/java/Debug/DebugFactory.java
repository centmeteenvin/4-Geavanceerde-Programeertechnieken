package Debug;

import SpaceInvaders.*;
import SpaceInvaders.entities.Bullet;
import SpaceInvaders.entities.Enemy;
import SpaceInvaders.entities.HittableEntity;
import SpaceInvaders.entities.Player;
import SpaceInvaders.utilities.GameState;
import SpaceInvaders.utilities.InputController;
import SpaceInvaders.utilities.Settings;

import java.awt.*;

public class DebugFactory extends AbstractFactory {

    public DebugFactory() {
        gameState = new GameState();
        gameState.setCurrentLevel(1);
        gameState.setPlaying(true);
        settings = new Settings();
        settings.setFps(1);
    }
    @Override
    public void initialize() {

    }

    @Override
    public void render() {

    }

    @Override
    public Enemy enemyCreator(Point location, double health, double size, AbstractFactory abstractFactory,Point bounds) {
        return new DebugEnemy(location, health, size, abstractFactory,bounds);
    }

    @Override
    public Player playerCreator(Point location, double health, double size, AbstractFactory abstractFactory,GameState gameState, InputController inputController) {
        return new DebugPlayer(location, health, size,abstractFactory, gameState, inputController);
    }

    /**
     * This factory is called when a {@link HittableEntity} Shoots.
     *
     * @param location {@link Bullet#coordinate}.
     * @param entity   {@link Bullet#owner}.
     * @return a reference to the Bullet object.
     */
    @Override
    public Bullet bulletCreator(Point location, HittableEntity entity) {
        return null;
    }

}
