package Debug;

import SpaceInvaders.*;
import SpaceInvaders.entity.Enemy;
import SpaceInvaders.entity.Player;
import SpaceInvaders.utilities.GameState;
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
    public Enemy enemyCreator(Point location, double health, double size, Point bounds) {
        return new DebugEnemy(location, health, size, bounds);
    }

    @Override
    public Player playerCreator(Point location, double health, double size, GameState gameState) {
        return new DebugPlayer(location, health, size, gameState);
    }

}
