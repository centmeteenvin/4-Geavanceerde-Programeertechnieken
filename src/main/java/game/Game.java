package game;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class Game {
    private AbstractFactory abstractFactory;
    private ArrayList<Entity> entities;
    private GameState gameState;
    private Settings settings;
    public Game(AbstractFactory abstractFactory) {
        this.abstractFactory = abstractFactory;
    }

    /**
     * Call this method to start the game.
     * 1 call Initialize.
     * 2 load the level.
     * 3 start the gameLoop.
     */
    public void start() {
        initialize();
        loadLevel();
        gameLoop();
    }

    private void loadLevel() {
        File currentLevelFile = new File("src/main/resources/levels/level_" + gameState.getCurrentLevel());
        try {
            BufferedReader levelReader = new BufferedReader(new FileReader(currentLevelFile));
            String line;
            do {
                line = levelReader.readLine();
                //TODO entity lijst updaten maar eerst Player en Enemy maken
            }   while (line != null);
        } catch (FileNotFoundException e) {
            System.out.println("Level does not exist");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Initialize all the settings of the game.
     * Makes a call to the AbstractFactory InitializeMethod.
     * Gets the gameState object from the factory.
     */
    public void initialize() {
        abstractFactory.initialize();
        this.gameState = abstractFactory.getGameState();
        this.settings = abstractFactory.getSettings();
    }

    /**
     * execute the game loop.
     * 1 Get all the inputs and events.
     * 2 Call the update method on all Entities.
     * 3 Call the visualize method on all Entities.
     * 4 Call AbstractFactory render method add the end of the loop.
     */
    public void gameLoop() {
        long time;
        double elapsedTime;
        double timePerFrame = 1.0/settings.getFps();
        while(gameState.getPlaying()) {
            time = System.currentTimeMillis();
            //TODO fetchInputs, nog is even nadenken hoe ik inputs ga passeren
            entities.forEach(Entity::update);
            entities.forEach(Entity::visualize);
            abstractFactory.render();
            elapsedTime = (double) (System.currentTimeMillis() - time);
            if (elapsedTime < timePerFrame) {
                try {
                    TimeUnit.MILLISECONDS.sleep((long) (timePerFrame-elapsedTime));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
