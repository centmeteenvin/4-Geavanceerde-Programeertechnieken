package Debug;

import SpaceInvaders.*;
import SpaceInvaders.entities.Bullet;
import SpaceInvaders.entities.enemies.DefaultEnemy;
import SpaceInvaders.entities.HittableEntity;
import SpaceInvaders.entities.Player;
import SpaceInvaders.entities.enemies.ShootingEnemy;
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
    public DefaultEnemy defaultEnemyCreator(Point location, int health, double size, Point bounds) {
        return new DebugDefaultEnemy(location, health, size, this,bounds);
    }

    /**
     * This factory is called when a level is loaded.<br>
     * This should be overwritten returning an Enemy object.
     *
     * @param location           Point that defines the starting location.
     * @param health             double that defines the starting health.
     * @param size               double thate defines the sie, needed for collision detection.
     * @param bounds             point that defines the maximum movement in x-axis direction.
     * @param averageTimeToShoot The average time between shots.
     * @return a ShootingEnemy object with the given parameters.
     */
    @Override
    public ShootingEnemy shootingEnemyCreator(Point location, int health, double size, Point bounds, double averageTimeToShoot) {
        return null;
    }

    @Override
    public Player playerCreator(Point location, int health, double size) {
        return new DebugPlayer(location, health, size,this, gameState, inputController);
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

    /**
     * Called by the game when a level is cleared {@link Game#levelCleared()}.
     * Use this as a hook to detect cleared levels.
     */
    @Override
    public void levelCleared() {

    }

    /**
     * Called by the game when the game is over {@link Game#gameOver()}.
     * Use this as a hook te detect game Over state.
     */
    @Override
    public void gameOver() {

    }

}
