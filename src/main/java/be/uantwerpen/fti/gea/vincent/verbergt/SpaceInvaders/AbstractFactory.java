package be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders;

import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.*;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.enemies.DefaultEnemy;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.enemies.bosses.Exterminator;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.enemies.bosses.Nabula;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.projectiles.Bullet;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.projectiles.Torpedo;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.Event;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;


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
     * Should be passed to the {@link #playerCreator(Point, int, double) Player} object.<br>
     * </p>
     */
    protected InputController inputController;

    /**
     * The Queue that holds all {@link Event events}.
     */
    protected final LinkedList<Event> eventQueue = new LinkedList<>();

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
     *
     * @param gameState reference to a GameState object.
     * @param settings  reference to a Settings object.
     */
    public AbstractFactory(GameState gameState, Settings settings) {
        this.gameState = gameState;
        this.settings = settings;
    }


    /**
     * Add an event to the end of the {@link #eventQueue}.
     *
     * @param event {@link Event}.
     */
    public final void addEvent(Event event) {
        eventQueue.add(event);
    }

    /**
     * The method that processes the {@link Event events} in {@link #eventQueue}.
     * Is Called every gameTick via {@link Game#gameLoop()} before {@link #render()}.
     * Calls {@link #handleEvent(Event)} for every event and empties {@link #eventQueue}.
     */
    public final void processEvents() {
        while (!eventQueue.isEmpty()) {
            handleEvent(eventQueue.pop());
        }
    }


    /**
     * Getter for GameState.
     *
     * @return reference to {@link #gameState}
     */
    public final GameState getGameState() {
        return gameState;
    }

    /**
     * Getter for Setting.
     *
     * @return reference to {@link #settings}
     */
    public final Settings getSettings() {
        return settings;
    }

    /**
     * Getter for the entities list.
     *
     * @return reference to {@link #entities}
     */
    public final ArrayList<Entity> getEntities() {
        return entities;
    }

    /**
     * Getter for the inputController.
     *
     * @return reference to {@link #inputController}
     */
    public final InputController getInputController() {
        return inputController;
    }

    /**
     * Start the game from level 1.
     */
    public final void start() {
        gameState.setCurrentLevel(1);
        gameState.setPlaying(true);
    }

    /**
     * Ends the game/program.
     */
    public final void end() {
        System.exit(1);
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
     * The method handles all event that are in the {@link #eventQueue} when {@link #processEvents()} is called.
     *
     * @param event {@link Event}.
     */
    protected abstract void handleEvent(Event event);


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
     *
     * @param location {@link Bullet#coordinate}.
     * @param entity   {@link Bullet#owner}.
     * @return a reference to the Bullet object.
     */
    public abstract Bullet bulletCreator(Point location, HittableEntity entity);

    /**
     * This factory is called when a {@link Nabula} shoots its missile attack.
     * @param location the starting location.
     * @param initialDirection the initial direction.
     * @param owner the owner of the torpedo.
     * @param target the target of the torpedo.
     * @return a concrete implementation of {@link Torpedo}.
     */
    public abstract Torpedo torpedoCreator(Point location, Vector2D initialDirection, HittableEntity owner, HittableEntity target);

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
     * When a {@link be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.enemies.Enemy} dies.
     * This should be overwritten returning a concrete implementation of {@link PowerUp}.
     * @param location {@link PowerUp#coordinate}
     * @param type the powerUp Type.
     * @return concrete {@link PowerUp}.
     */
    public abstract PowerUp powerUpCreator(Point location, PowerUp.Type type);

    /**
     * This factory is called when a level is loaded.<br>
     * This should be overwritten returning a concrete implementation of {@link Exterminator}.
     * @param location {@link Exterminator#coordinate}
     * @return concrete {@link Exterminator}.
     */
    public abstract Exterminator exterminatorCreator(Point location);

    /**
     * This factory is called when a level is loaded.<br>
     * This should be overwritten returning a concrete implementation of {@link Nabula}.
     * @param location The starting location.
     * @return concrete {@link Nabula}.
     */
    public abstract Nabula nabulaCreator(Point location);

}
