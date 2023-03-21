package game;

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

    public abstract Enemy enemyCreator(Point location, double health, double size, Point bounds);
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
