package be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders;

import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.dataOriented.SoundComponent;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.dataOriented.SoundEngine;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.entities.*;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.entities.bosses.J2DExterminator;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.entities.bosses.J2DNabula;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.utilities.Props;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.AbstractFactory;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.*;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.enemies.DefaultEnemy;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.enemies.Enemy;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.enemies.bosses.Exterminator;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.enemies.bosses.Nabula;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.projectiles.Bullet;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.projectiles.Torpedo;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.Event;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.GameState;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.Settings;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.Vector2D;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

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
    private final GraphicsContext graphicsContext;


    /**
     * The controller for all the {@link be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.dataOriented.SoundComponent Soundcomponents}.
     */
    private final SoundEngine soundEngine;

    /**
     * The list containing all {@link SoundComponent} that need to be played.
     */
    private final ArrayList<SoundComponent> soundComponents = new ArrayList<>();

    /**
     * A ArrayList containing all scores.
     */
    public ArrayList<Pair<String, Integer>> scores = new ArrayList<>();

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
        this.soundEngine = new SoundEngine(graphicsContext.preLoader);
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
        this.soundEngine = new SoundEngine(graphicsContext.preLoader);
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
        if (new File(properties.scoreFile).exists()) {
            scores = loadScores();
        }
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
        return new J2DBullet(location, owner , this);
    }

    @Override
    public Torpedo torpedoCreator(Point location, Vector2D initialDirection, HittableEntity owner, HittableEntity target) {
        return new J2DTorpedo(location, initialDirection, owner, this, target);
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
     * This factory is called when a powerUp drops.
     * @param location {@link PowerUp#coordinate}
     * @param type the powerUp Type.
     * @return {@link J2DPowerUp}
     */
    @Override
    public PowerUp powerUpCreator(Point location, PowerUp.Type type) {
        return new J2DPowerUp(location, this, type);
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

    @Override
    public Nabula nabulaCreator(Point location) {
        return new J2DNabula(location, this);
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
            case GOT_HIT -> {
               queueSound(new SoundComponent(properties.hitSound, -25));
               graphicsContext.spark(((Entity) event.getSource()).coordinate);
            }

            case DEATH -> {
                graphicsContext.explosion(((Entity) event.getSource()).coordinate);
            }
            case LEVEL_CLEARED -> {
                int currentLevel = gameState.getCurrentLevel();
                gameState.setCurrentLevel(currentLevel + 1);
                gameState.setPlaying(true);
                //TODO 2
            }
            case GAME_OVER -> {
                System.out.println("Game was over");
                HashMap<String, Integer> finalGameState = (HashMap<String, Integer>) event.getSource();
                JOptionPane.showMessageDialog(null, "Game Over");
                int choice = JOptionPane.showConfirmDialog(
                        null, "You died at level: " + finalGameState.get("level") + "\nYour score was: " + finalGameState.get("score"),
                        "Save Score?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                        );
                if (choice == JOptionPane.YES_OPTION) {
                    String name = JOptionPane.showInputDialog("Your name: ");
                    Pair<String, Integer> entry = new Pair<>(name, finalGameState.get("score"));
                    scores.add(entry);
                    saveScores();
                }
                graphicsContext.gameOver();
            }
            case VICTORY -> {
                //TODO 4
                HashMap<String, Integer> finalGameState = (HashMap<String, Integer>) event.getSource();
                JOptionPane.showMessageDialog(null, "Victory");
                int choice = JOptionPane.showConfirmDialog(
                        null, "Your score was: " + finalGameState.get("score"),
                        "Save Score?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );
                if (choice == JOptionPane.YES_OPTION) {
                    String name = JOptionPane.showInputDialog("Your name: ");
                    Pair<String, Integer> entry = new Pair<>(name, finalGameState.get("score"));
                    scores.add(entry);
                    saveScores();
                }
                graphicsContext.gameOver();
            }

        }
    }

    /**
     * Save {@link #scores} to a file.
     */
    private void saveScores() {
        try {
            FileOutputStream fos = new FileOutputStream(properties.scoreFile);
            ObjectOutputStream bos = new ObjectOutputStream(fos);
            bos.writeObject(scores);
            bos.close();
            fos.close();
        } catch (IOException e) {
            System.out.println("Error saving file.");
            throw new RuntimeException(e);
        }
    }

    /**
     * reads the scores from {@link Props#scoreFile} and loads it into an array.
     * @return a list with the scores.
     */
    private ArrayList<Pair<String, Integer>> loadScores() {
        try {
            FileInputStream fis = new FileInputStream(properties.scoreFile);
            ObjectInputStream bis = new ObjectInputStream(fis);
            ArrayList<Pair<String, Integer>> list = (ArrayList<Pair<String, Integer>>) bis.readObject();
            bis.close();
            fis.close();
            return list;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading scores.");
            throw new RuntimeException(e);
        }
    }

}
