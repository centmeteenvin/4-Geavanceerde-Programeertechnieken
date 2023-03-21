package game;

import game.entity.Enemy;
import game.entity.Entity;
import game.entity.Player;
import game.utilities.GameState;
import game.utilities.Settings;

import java.awt.*;
import java.util.ArrayList;


/**
 * Abstract class that defines the Factory that makes the objects needed for the game.
 * <p>
 * This class should be extended for a concrete implementation and passed to Game constructor.
 */
public abstract class AbstractFactory {

    /**
     * The current GameState of the game.
     * <p>
     * Is created or passed in {@link #AbstractFactory() }  constructor.<br>
     * State should be set in {@link #initialize()}.<br>
     * Game also holds a reference of this object.<br>
     * Should als be passed to the {@link Player} object because they need to interact with it.<br>
     */
    protected GameState gameState;

    /**
     * The settings for the game.
     * <p>
     * Is created or passed in {@link #AbstractFactory()} contructor.<br>
     * Settings should be set in {@link #initialize()}.<br>
     * Game also holds a reference of this object.<br>
     */
    protected Settings settings;

    /**
     * Default constructor.
     * <p>
     * {@link #gameState} and {@link #settings} are initialized empty.<br>
     */
    public AbstractFactory() {
        this.gameState = new GameState();
        this.settings = new Settings();
    }

    /**
     * Overloaded constructor with parameters.
     * <p>
     * {@link #gameState} and {@link #settings} are passed by reference from the parameters.<br>
     * @param gameState reference to a GameState object.
     * @param settings reference to a Settings object.
     */
    public AbstractFactory(GameState gameState, Settings settings) {
        this.gameState = gameState;
        this.settings = settings;
    }

    /**
     * Initializes the fields and settings of the factory.
     * <p>
     * Called once when Game.start() is called.<br>
     * Use this to initialize {@link #gameState} and {@link #settings}.<br>
     */
    public abstract void initialize();

    /**
     * Push to completed frame to the user.
     * <p>
     * Is called at the end of every gameLoop.<br>
     * Use this when implementing a buffer draw scheme.
     * Draw to the buffer with the {@link Entity#visualize()} method.
     * Push the frame to the screen with this method.<br>
     */
    public abstract void render();

    /**
     * This factory is called when a level is loaded.<br>
     * This should be overwritten returning an Enemy object.
     * @param location Point that defines the starting location.
     * @param health double that defines the starting health.
     * @param size double that defines the size, needed for collision detection
     * @param bounds point that defines the maximum movement in the x-axis direction.
     * @return an Enemy object instantiated with the given parameters.
     */
    public abstract Enemy enemyCreator(Point location, double health, double size, Point bounds);

    /**
     * This factory is called when a level is loaded.<br>
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
     * <p>
     * It's mainly used to parse the parameter list into the needed attributes to create a Player object.<br>
     * The generated parameters are passed to the playerCreator.<br>
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
     * <p>
     * It's mainly used to parse the parameter list into the needed attributes to create an Enemy.<br>
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
