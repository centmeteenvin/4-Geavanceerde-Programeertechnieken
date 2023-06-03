package be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders;

import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.enemies.Enemy;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.Entity;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.Player;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.*;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


/**
 * Implementation of the SpaceInvaders.
 *
 * <p>Instantiate Game object with an AbstractFactory. <br>
 * start the SpaceInvaders with {@link #start() Game.start()} method. </p>
 */
public class Game {

    /**
     * AbstractFactory is an abstract class that defines the factory.
     *
     * <p>Is passed via constructor.
     * <br>Should be a concrete implementation of an {@link AbstractFactory} class.
     */
    private AbstractFactory abstractFactory;

    /**
     * An ArrayList of all the entities currently in the SpaceInvaders.
     *
     * <p>In the gameLoop the doHittableEntityUpdate and visualize method is called on all elements of this list.
     * <p>Is initialized in the constructor and assigned in the {@link #loadLevel() loadLevel} method.
     */
    private ArrayList<Entity> entities;

    /**
     * The Current state of the SpaceInvaders.
     *
     * <p>Contains information such as current level and pausing.
     * <p>Is created in the {@link AbstractFactory#AbstractFactory() AbstractFactory} constructor.
     * <br>Is passed in the {@link #initialize() initialize} method.
     */
    private GameState gameState;

    /**
     * The Settings of the SpaceInvaders.
     *
     * <p>Contains information such as fps.
     * <p>Is created in the {@link AbstractFactory#AbstractFactory() AbstractFactory} constructor.
     * <br>Is passed in the {@link #initialize() initialize} method.
     */
    private Settings settings;


    /**
     * The Game constructor.
     *
     * @param abstractFactory the implementation of the {@link AbstractFactory}.
     */
    public Game(AbstractFactory abstractFactory) {
        this.abstractFactory = abstractFactory;
    }

    /**
     * Call this method to start the SpaceInvaders.
     * <p>
     * 1 call {@link #initialize()}.<br>
     * 2 load the level via {@link #loadLevel()}.<br>
     * 3 start the {@link #gameLoop()}.<br>
     */
    public void start() {
        initialize();
        loadLevel();
        gameLoop();
    }

    /**
     * Initialize all the settings of the SpaceInvaders.
     * <p>
     * Makes a call to {@link AbstractFactory#initialize()}.<br>
     * Gets {@link #gameState} from the {@link #abstractFactory}.<br>
     * Gets {@link #settings} from the {@link #abstractFactory}.<br>
     */
    private void initialize() {
        abstractFactory.initialize();
        this.entities = abstractFactory.getEntities();

        this.gameState = abstractFactory.getGameState();
        this.gameState.initialize();

        this.settings = abstractFactory.getSettings();
        this.settings.loadFromProperties();
    }

    /**
     * Loads the level from the level data file.<br>
     * The level that is opened depends on the SpaceInvaders-state's currentLevel Field.
     */
    private void loadLevel() {
        LevelLoader levelLoader = new LevelLoader(abstractFactory);
        File currentLevelFile = new File("src/main/resources/levels/level_" + gameState.getCurrentLevel());
        if (currentLevelFile.exists()) {
            try {
                // first cleared the list and then added the new one to retain the same reference as abstractfactory
                entities.clear();
                entities.addAll(levelLoader.LoadLevel((currentLevelFile)));
                gameState.setCurrentLoadedLevel(gameState.getCurrentLevel());
                return;
            } catch (FileNotFoundException e) {
                throw new RuntimeException("File not found: " + currentLevelFile.getName());
            }
        }

    }

    /**
     * execute the SpaceInvaders loop.
     * <p>
     * 1 Get all the inputs and events.<br>
     * 2 Call the {@link Entity#update()} method on all {@link #entities Entities}.<br>
     * 3 Call the {@link Entity#visualize()} method on all {@link #entities Entities}.<br>
     * 4 Call {@link AbstractFactory#render()} method add the end of the loop.<br>
     */
    private void gameLoop() {
        long time;
        double elapsedTime;
        double msPerFrame = 1000 / settings.getFps();
        while (true) {
//            System.out.println("checking gameState");
            //using infinite loop because I need to be able to constantly check the getPlaying status
            time = System.currentTimeMillis();
            if (gameState.getCurrentLevel() != gameState.getCurrentLoadedLevel()) {
                loadLevel();
            }
            else if (gameState.getPlaying()) {
                ArrayList<Entity> immutableEntityList = (ArrayList<Entity>) entities.clone();
                for (Entity entity : immutableEntityList) {
                    entity.preUpdateCheck();
                }
                for (Entity entity : immutableEntityList) { //for loop instead of for each to prevent concurrency.
                    entity.update();
                }

                //Check for gameOver condition first because levelCleared will clear the entities list.
                if (entities.stream().noneMatch(entity -> entity instanceof Player)) gameOver();
                else if (entities.stream().noneMatch(entity -> entity instanceof Enemy)) levelCleared();

                entities.forEach(Entity::visualize);
                abstractFactory.processEvents();
                abstractFactory.render();
            }
            elapsedTime = (double) (System.currentTimeMillis() - time);
            if (elapsedTime < msPerFrame) {
                try {
                    TimeUnit.MILLISECONDS.sleep((long) (msPerFrame - elapsedTime));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * Called when a level is cleared.
     * <p>
     * Creates an {@link Event.Type#LEVEL_CLEARED} event or a {@link Event.Type#VICTORY} if we just cleared the last level.
     * Clears {@link #entities}.<br>
     * </p>
     */
    private void levelCleared() {
        int currentLevel = gameState.getCurrentLevel();

        // If the current level is the last level and we just cleared we achieved victory.
        if (currentLevel == Constants.NUMBER_OF_LEVELS) {
            victory();
            return;
        }
        abstractFactory.addEvent(new Event(Event.Type.LEVEL_CLEARED, this));
        entities.clear();
        gameState.setCurrentLoadedLevel(0);
        gameState.setPlaying(false);


    }

    /**
     * Stops the game.
     * <p>
     * Pauses Game and then creates an {@link Event.Type#GAME_OVER} event.
     * </p>
     */
    private void gameOver() {
        gameState.setPlaying(false);
        entities.clear();
        gameState.setCurrentLoadedLevel(0);
        abstractFactory.addEvent(new Event(Event.Type.GAME_OVER, this));
    }

    /**
     * Called when victory is achieved.
     * Sets {@link GameState#isPlaying} to false.
     * Creates an {@link Event.Type#VICTORY}.
     */
    private void victory() {
        gameState.setPlaying(false);
        abstractFactory.addEvent(new Event(Event.Type.VICTORY, this));
        entities.clear();
        gameState.setCurrentLoadedLevel(-1);
    }
}
