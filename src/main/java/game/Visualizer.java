package game;

/**
 * Abstract visualizer class, comes in handy when all the gameObjects draw to the same canvas.
 */
public abstract class Visualizer {
    /**
     * Initialize the visualizer.
     * set all the setting etc.
     * This method is called at the start of the game.
     */
    public abstract void initialize();

    /**
     * Render the frame and push it to the user.
     * This method is called at the end of every game loop.
     */
    public abstract void render();
}
