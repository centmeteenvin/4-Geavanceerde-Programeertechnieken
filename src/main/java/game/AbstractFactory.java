package game;

import game.entity.Enemy;
import game.entity.Player;
import game.utilities.GameState;
import game.utilities.Settings;

import java.awt.*;
import java.util.ArrayList;

/**
 * Abstract class that defines the Factory that makes the objects needed for the game;
 */
public abstract class AbstractFactory {
    protected GameState gameState;
    protected Settings settings;
    public  AbstractFactory() {

    }

    /**
     * Initializes the fields and settings of the factory.
     * Called once when Game.start() is called.
     */
    public abstract void initialize();

    /**
     * Push to completed frame to the user.
     * Is called at the end of every gameLoop.
     */
    public abstract void render();

    /**
     * This factory is called when a level is loaded.
     * This should be overwritten returning an Enemy object.
     * @param location Point that defines the starting location.
     * @param health double that defines the starting health.
     * @param size double that defines the size, needed for collision detection
     * @param bounds point that defines the maximum movement in the x-axis direction.
     * @return an Enemy object instantiated with the given parameters.
     */
    public abstract Enemy enemyCreator(Point location, double health, double size, Point bounds);

    /**
     * This factory is called when a level is loaded.
     * This should be overwritten returning a Player object.
     * @param location Point that defines the starting location.
     * @param health double that defines the starting health.
     * @param size double that defines the size, need for collision detection.
     * @param gameState the gameState object of the abstractFactory, is also passed to the game.
     * @return a Player object instantiated with the given parameters.
     */
    public abstract Player playerCreator(Point location, double health, double size, GameState gameState);

    /**
     * Is used to create the player.
     * It's mainly used to parse the parameter list into the needed attributes to create a Player object.
     * The generated parameters are passed to the playerCreator;
     * @param parameters Is the list of parameters that is read from the level file.
     * @return An instance of Enemy that has the given parameters.
     */
    public Player createPlayer(ArrayList<String> parameters) {
        //Type;Health;Size;Location;bounds
        double health = Double.parseDouble(parameters.get(1));
        double size = Double.parseDouble(parameters.get(2));
        String[] tempList = parameters.get(3).split(",");
        Point location = new Point(Integer.parseInt(tempList[0]), Integer.parseInt(tempList[1]));
        return playerCreator(location, health, size, gameState);
    }

    /**
     * Is used to create the enemy.
     * It's mainly used to parse the parameter list into the needed attributes to create an Enemy.
     * The generated parameters are passed to the enemyCreator;
     * @param parameters Is the list of parameters that is read from the level file.
     * @return An instance of Enemy that has the given parameters.
     */
    public  Enemy createEnemy(ArrayList<String> parameters) {
        //Type;Health;Size;Location;bounds
        double health = Double.parseDouble(parameters.get(1));
        double size = Double.parseDouble(parameters.get(2));
        String[] tempList = parameters.get(3).split(",");
        Point location = new Point(Integer.parseInt(tempList[0]), Integer.parseInt(tempList[1]));
        tempList = parameters.get(4).split(",");
        Point bounds = new Point(Integer.parseInt(tempList[0]), Integer.parseInt(tempList[1]));
        return enemyCreator(location, health, size, bounds);
    }


    public GameState getGameState() {
        return gameState;
    }

    public Settings getSettings() {
        return settings;
    }
}
