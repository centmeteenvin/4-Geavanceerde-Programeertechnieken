package game;

/**
 * Defines the Objects the factory needs to be able to make
 */
public abstract class AbstractFactory {

    public AbstractFactory() {

    }

    /**
     * Return a player object
     * @return Player object
     */
    public abstract Player createPlayer();

    /**
     * return the game Visualizer
     * @return game Visualizer object
     */
    public abstract Visualizer createVisualizer();

}
