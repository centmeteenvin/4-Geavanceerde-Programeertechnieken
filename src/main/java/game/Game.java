package game;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    /**
     * execute the game loop.
     * 1 Get all the inputs and events.
     * 2 Call the update method on all Entities.
     * 3 Call the visualize method on all Entities.
     * 4 Call AbstractFactory render method add the end of the loop.
     */
    private void gameLoop() {
        long time;
        double elapsedTime;
        double timePerFrame = 1.0/settings.getFps();
        while(gameState.getPlaying()) {
            time = System.currentTimeMillis();
            //TODO fetchInputs, nog is even nadenken hoe ik inputs ga passeren
            entities.forEach(entity -> entity.update(entities));
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

    /**
     * Loads the level from the level data file.
     * The level that is opened depends on the game-state's currentLevel Field.
     */
    private void loadLevel() {
        File currentLevelFile = new File("src/main/resources/levels/level_" + gameState.getCurrentLevel());
        try {
            BufferedReader levelReader = new BufferedReader(new FileReader(currentLevelFile));
            String line;
            ArrayList<String> list;
            levelReader.readLine(); // read passed the first line, it only contains information about the way the data is formatted.
            do {
                line = levelReader.readLine();
                list = new ArrayList<>(List.of(line.split(";")));
                switch (list.get(0)) {
                    case "Enemy" -> entities.add(abstractFactory.createEnemy(list));
                    case "Player" -> entities.add(abstractFactory.createPlayer(list));
                }
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
    private void initialize() {
        abstractFactory.initialize();
        this.gameState = abstractFactory.getGameState();
        this.settings = abstractFactory.getSettings();
    }
}
