package game;

/**
 * Abstract class that defines the Factory that makes the objects needed for the game;
 */
public abstract class AbstractFactory {
    private GameState gameState;
    public  AbstractFactory() {

    }

    /**
     * Initializes the fields and settings of the factory.
     * Called once when Game.start() is called.
     */
    public abstract void initialize();

    /**
     * Push to completed frame to the user.
     * Is called at the end of every gameLoop.
     */
    public abstract void render();

    public GameState getGameState() {
        return gameState;
    }
}
