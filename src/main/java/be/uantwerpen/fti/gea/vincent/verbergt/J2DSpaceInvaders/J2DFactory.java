package be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders;

import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.dataOriented.SoundComponent;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.dataOriented.SoundEngine;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.entities.*;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.entities.bosses.J2DExterminator;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.utilities.Props;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.AbstractFactory;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.Game;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.*;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.enemies.DefaultEnemy;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.enemies.Enemy;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.enemies.ShootingEnemy;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.enemies.bosses.Exterminator;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.Event;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.GameState;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.Settings;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Concrete implementation of {@link AbstractFactory}.
 * <p>
 * Implements the {@link GraphicsContext}.<br>
 * Handles Object creation for concrete j2d {@link Entity entities}.<br>
 * Also gives objects access to the {@link GameState} and {@link Settings}<br>
 * </p>
 */
public class J2DFactory extends AbstractFactory {

    /**
     * The GraphicsContext of the J2DGame.
     * <p>
     * Used to show a visualisation of the game using J2D.
     * It also uses a double buffered scheme.
     * </p>
     */
    private GraphicsContext graphicsContext;


    /**
     * The controller for all the {@link be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.dataOriented.SoundComponent Soundcomponents}.
     */
    private final SoundEngine soundEngine = new SoundEngine();

    /**
     * The list containing all {@link SoundComponent} that need to be played.
     */
    private final ArrayList<SoundComponent> soundComponents = new ArrayList<>();
    /**
     * A {@link Props} object to easily reach constants.
     */
    public final Props properties;

    /**
     * Default constructor.
     * <p>
     * {@link #gameState}, {@link #settings}, {@link #entities} and {@link #graphicsContext} are initialized empty.<br>
     */
    public J2DFactory() {
        super();
        this.properties = new Props();
        this.graphicsContext = new GraphicsContext(this);
        this.inputController = new J2DInputController(graphicsContext);
    }

    /**
     * Overloaded constructor with parameters.
     * <p>
     * {@link #gameState} and {@link #settings} are passed by reference from the parameters.<br>
     *
     * @param gameState reference to a GameState object.
     * @param settings  reference to a Settings object.
     */
    public J2DFactory(GameState gameState, Settings settings) {
        super(gameState, settings);
        this.properties = new Props();
        this.graphicsContext = new GraphicsContext(this);
    }

    /**
     * Initializes the fields and settings of the factory.
     * <p>
     * Called once when Game.start() is called.<br>
     * Use this to initialize {@link #gameState} and {@link #settings}.<br>
     * Calls {@link GraphicsContext#initialize()};
     */
    @Override
    public void initialize() {
        gameState.setPlaying(false);
        graphicsContext.initialize();
    }

    /**
     * Push to completed frame to the user.
     * <p>
     * Is called at the end of every gameLoop.<br>
     * Use this when implementing a buffer draw scheme.
     * Draw to the buffer with the {@link Entity#visualize()} method.
     * Push the frame to the screen with this method.<br>
     * Calls {@link GraphicsContext#render()}.<br>
     */
    @Override
    public void render() {
        graphicsContext.render();
        soundEngine.playSounds(soundComponents);
        soundComponents.clear();
//        System.out.println(entities.size());
    }

    /**
     * This factory is called when a level is loaded.<br>
     * This should be overwritten returning an Enemy object.
     *
     * @param location Point that defines the starting location.
     * @param health   double that defines the starting health.
     * @param size     double that defines the size, needed for collision detection
     * @return an Enemy object instantiated with the given parameters.
     */
    @Override
    public DefaultEnemy defaultEnemyCreator(Point location, int health, double size) {
        return new J2DDefaultEnemy(location, health, size, this);
    }

    /**
     * This factory is called when a level is loaded.<br>
     * This should be overwritten returning a Player object.
     *
     * @param location Point that defines the starting location.
     * @param health   double that defines the starting health.
     * @param size     double that defines the size, need for collision detection.
     * @return a Player object instantiated with the given parameters.
     */
    @Override
    public Player playerCreator(Point location, int health, double size) {
        return new J2DPlayer(location, health, size, this);
    }

    /**
     * This factory is called when a {@link HittableEntity} Shoots.
     *
     * @param location {@link Bullet#coordinate}.
     * @param owner    {@link Bullet#owner}.
     * @return a reference to the Bullet object.
     */
    @Override
    public Bullet bulletCreator(Point location, HittableEntity owner) {
        return new J2DBullet(location, owner, settings, this);
    }

    /**
     * This factory is called when a level is loaded.<br>
     * This should be overwritten returning a Wall object.
     *
     * @param location Point that defines the starting location.
     * @param health   double that defines the starting health.
     * @param size     double that defines the size, need for collision detection.
     * @return a Wall object instantiated with the given parameters.
     */
    @Override
    public Wall wallCreator(Point location, int health, double size) {
        return new J2DWall(location, health, size, this);
    }

    /**
     * Creates a {@link J2DExterminator}.
     *
     * @param location {@link Exterminator#coordinate}
     * @return {@link J2DExterminator}.
     */
    @Override
    public Exterminator exterminatorCreator(Point location) {
        return new J2DExterminator(location, this);
    }

    /**
     * Getter for graphicsContext/
     *
     * @return reference to {@link #graphicsContext}
     */
    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

    /**
     * Add a soundComponent to the {@link #soundComponents} list.
     *
     * @param soundComponent The soundComponent that you want to play.
     */
    public void queueSound(SoundComponent soundComponent) {
        soundComponents.add(soundComponent);
    }

    /**
     * Process a single event.
     *
     * @param event {@link Event}.
     */
    @Override
    protected void handleEvent(Event event) {
        switch (event.getType()) {
            case SHOOT -> {
                if (event.getSource() instanceof Enemy) {
                    queueSound(new SoundComponent(properties.enemyShootingSound, -25));
                } else {
                    queueSound(new SoundComponent(properties.playerShootingSound, -25));
                }
            }
            case DEATH -> {
                //TODO 1
            }
            case LEVEL_CLEARED -> {
                int currentLevel = gameState.getCurrentLevel();
                gameState.setCurrentLevel(currentLevel + 1);
                gameState.setPlaying(true);
                //TODO 2
            }
            case GAME_OVER -> {
                System.out.println("Game was over");
                graphicsContext.gameOver();
                //TODO 3
            }
            case VICTORY -> {
                //TODO 4
                System.out.println("Victory was achieved");
                graphicsContext.gameOver();
            }

        }
    }

}
