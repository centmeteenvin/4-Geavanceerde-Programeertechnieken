package SpaceInvaders;

import SpaceInvaders.entities.*;
import SpaceInvaders.entities.enemies.DefaultEnemy;
import SpaceInvaders.entities.enemies.ShootingEnemy;
import SpaceInvaders.utilities.GameState;
import SpaceInvaders.utilities.InputController;
import SpaceInvaders.utilities.Settings;

import java.awt.*;
import java.util.ArrayList;


/**
 * Abstract class that defines the Factory that makes the objects needed for the SpaceInvaders.
 * <p>
 * This class should be extended for a concrete implementation and passed to {@link Game}constructor.
 */
public abstract class AbstractFactory {

    /**
     * The current GameState of the SpaceInvaders.
     * <p>
     * Is created or passed in {@link #AbstractFactory() }  constructor.<br>
     * State should be set in {@link #initialize()}.<br>
     * {@link Game} also holds a reference of this object.<br>
     * Should als be passed to the {@link Player} object because they need to interact with it.<br>
     */
    protected GameState gameState;

    /**
     * The settings for the SpaceInvaders.
     * <p>
     * Is created or passed in {@link #AbstractFactory()} contructor.<br>
     * Settings should be set in {@link #initialize()}.<br>
     * {@link Game} also holds a reference of this object.<br>
     */
    protected Settings settings;

    /**
     * The entitylist for the game.
     * <p>
     * AbstractFactory is responsible for the initialization of this list.<br>
     * References are passed throughout the game and it objects.<br>
     * During {@link Game#loadLevel() level loading} and {@link Entity#update() updates} the list can be modified.<br>
     * </p>
     */
    protected ArrayList<Entity> entities;

    /**
     * Handles all user input.
     * <p>
     *     Should be passed to the {@link #playerCreator(Point, int, double) Player} object.<br>
     * </p>
     */
    protected InputController inputController;

    /**
     * Default constructor.
     * <p>
     * {@link #gameState}, {@link #settings} and {@link #entities} are initialized empty.<br>
     */
    public AbstractFactory() {
        this.gameState = new GameState();
        this.settings = new Settings();
        this.entities = new ArrayList<>();
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
     * Use this as a hook for concrete implementations.
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
     *
     * @param location Point that defines the starting location.
     * @param health   double that defines the starting health.
     * @param size     double that defines the size, needed for collision detection.
     * @return an Enemy object instantiated with the given parameters.
     */
    public abstract DefaultEnemy defaultEnemyCreator(Point location, int health, double size);

    /**
     * This factory is called when a level is loaded.<br>
     * This should be overwritten returning an Enemy object.
     *
     * @param location Point that defines the starting location.
     * @param health double that defines the starting health.
     * @param size double thate defines the sie, needed for collision detection.
     * @param averageTimeToShoot The average time between shots.
     * @return a ShootingEnemy object with the given parameters.
     */
    public abstract ShootingEnemy shootingEnemyCreator(Point location, int health, double size, double averageTimeToShoot);

    /**
     * This factory is called when a level is loaded.<br>
     * This should be overwritten returning a Player object.
     *
     * @param location Point that defines the starting location.
     * @param health   double that defines the starting health.
     * @param size     double that defines the size, need for collision detection.
     * @return a Player object instantiated with the given parameters.
     */
    public abstract Player playerCreator(Point location, int health, double size);
    /**
     * This factory is called when a {@link HittableEntity} Shoots.
     * @param location {@link Bullet#coordinate}.
     * @param entity {@link Bullet#owner}.
     * @return a reference to the Bullet object.
     */
    public abstract Bullet bulletCreator(Point location, HittableEntity entity);


    /**
     * This factory is called when a level is loaded.<br>
     * This should be overwritten returning a Wall object.
     *
     * @param location Point that defines the starting location.
     * @param health   double that defines the starting health.
     * @param size     double that defines the size, need for collision detection.
     * @return a Wall object instantiated with the given parameters.
     */
    public abstract Wall wallCreator(Point location, int health, double size);

    /**
     * Getter for GameState.
     * @return reference to {@link #gameState}
     */
    public GameState getGameState() {
        return gameState;
    }

    /**
     * Getter for Setting.
     * @return reference to {@link #settings}
     */
    public Settings getSettings() {
        return settings;
    }

    /**
     * Getter for the entities list.
     * @return reference to {@link #entities}
     */
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    /**
     * Getter for the inputController.
     * @return reference to {@link #inputController}
     */
    public InputController getInputController() {
        return inputController;
    }

    /**
     * Called by the game when a level is cleared {@link Game#levelCleared()}.
     * Use this as a hook to detect cleared levels.
     */
    public abstract void levelCleared();

    /**
     * Called by the game when the game is over {@link Game#gameOver()}.
     * Use this as a hook te detect game Over state.
     */
    public abstract void gameOver();
}
