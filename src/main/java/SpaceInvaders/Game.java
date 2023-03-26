package SpaceInvaders;

import SpaceInvaders.entities.Enemy;
import SpaceInvaders.entities.Entity;
import SpaceInvaders.utilities.GameState;
import SpaceInvaders.utilities.Settings;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
        this.settings = abstractFactory.getSettings();
        this.settings.loadFromProperties();
    }

    /**
     * Loads the level from the level data file.<br>
     * The level that is opened depends on the SpaceInvaders-state's currentLevel Field.
     */
    private void loadLevel() {
        File currentLevelFile = new File("src/main/resources/levels/level_" + gameState.getCurrentLevel());
        try {
            BufferedReader levelReader = new BufferedReader(new FileReader(currentLevelFile));
            String line;
            ArrayList<String> list;
            levelReader.readLine(); // read passed the first line, it only contains information about the way the data is formatted.
            line = levelReader.readLine();
            do {
                list = new ArrayList<>(List.of(line.split(";")));
                switch (list.get(0)) {
                    case "Enemy" -> entities.add(abstractFactory.createEnemy(list));
                    case "Player" -> entities.add(abstractFactory.createPlayer(list));
                }
                line = levelReader.readLine();
            }   while (line != null);
        } catch (FileNotFoundException e) {
            System.out.println("Level does not exist");
        } catch (IOException e) {
            throw new RuntimeException(e);
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
        double msPerFrame = 1000/settings.getFps();
        while(gameState.getPlaying()) {
            time = System.currentTimeMillis();
            for (int i = 0; i < entities.size(); i++) { //for loop instead of for each to prevent concurrency.
                entities.get(i).update();
            }
            if (entities.stream().noneMatch(entity -> entity instanceof Enemy)) gameState.setPlaying(false);
            entities.forEach(Entity::visualize);
            abstractFactory.render();
            elapsedTime = (double) (System.currentTimeMillis() - time);
            if (elapsedTime < msPerFrame) {
                try {
                    TimeUnit.MILLISECONDS.sleep((long) (msPerFrame-elapsedTime));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
