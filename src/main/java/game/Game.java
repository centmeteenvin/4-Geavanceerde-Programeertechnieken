package game;

import java.util.ArrayList;

public class Game {
    private AbstractFactory abstractFactory;
    private ArrayList<Entity> entities;

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
     * Makes a call to the AbstractFactory InitializeMethod
     */
    public void initialize() {
        abstractFactory.initialize();
    }

    /**
     * execute the game loop.
     * 1 Get all the inputs and events.
     * 2 Call the update method on all Entities.
     * 3 Call the visualize method on all Entities.
     * 4 Call AbstractFactory render method add the end of the loop.
     */
    public void gameLoop() {
        while(true) {

        }
    }
}
