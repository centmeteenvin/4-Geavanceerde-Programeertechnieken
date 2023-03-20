package game;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class Game {
    private AbstractFactory abstractFactory;
    private ArrayList<Entity> entities;
    private GameState gameState;

    public Game(AbstractFactory abstractFactory) {
        this.abstractFactory = abstractFactory;
    }

    /**
     * Call this method to start the game
     */
    public void start() {
        initialize();
//        TODO loadLevel();
        gameLoop();
    }

    /**
     * Initialize all the settings of the game.
     * Makes a call to the AbstractFactory InitializeMethod.
     * Gets the gameState object from the factory.
     */
    public void initialize() {
        abstractFactory.initialize();
        this.gameState = abstractFactory.getGameState();
    }

    /**
     * execute the game loop.
     * 1 Get all the inputs and events.
     * 2 Call the update method on all Entities.
     * 3 Call the visualize method on all Entities.
     * 4 Call AbstractFactory render method add the end of the loop.
     */
    public void gameLoop() {
        while(gameState.getPlaying()) {
            //TODO fetchInputs, nog is even nadenken hoe ik inputs ga passeren
            entities.forEach(Entity::update);
            entities.forEach(Entity::visualize);
            abstractFactory.render();
        }
    }
}
