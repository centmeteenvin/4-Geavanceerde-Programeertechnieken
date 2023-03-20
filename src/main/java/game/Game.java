package game;

import java.util.concurrent.TimeUnit;

/**
 * Game class, uses the Abstract factory to make method calls
 */
public class Game {
    private AbstractFactory factory;
    private Player player;
    private Visualizer visualizer;
    private final double frameRate;

    /**
     * Constructor for the game
     * @param factory Abstract Factory with implemented methods
     * @param frameRate The maximum framerate of the game
     */
    public Game(AbstractFactory factory, double frameRate) {
        this.factory = factory;
        this.frameRate = frameRate;

    }

    /**
     * Initializes the game entities and calls the gameLoop
     */
    public void start() {
        this.player = factory.createPlayer();
        this.visualizer = factory.createVisualizer();
        visualizer.initialize();
        gameLoop();
    }

    /**
     * The gameLoop loops through all the code needed to update the game and visualize the frame.
     * The call sequence is update then visualize and at the end render.
     * There is a frame limiter implemented.
     * If the time to finish the loop is smaller than the time it has to draw we wait for the time that equals the difference.
     */
    public void gameLoop() {
        long milliSecondsPerFrame =(long) (1000/frameRate);
        while (true) {
            long startTime = System.currentTimeMillis();
            player.update();
            player.visualize();
            visualizer.render();
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime < milliSecondsPerFrame) {
                try {
                    TimeUnit.MILLISECONDS.sleep( milliSecondsPerFrame -elapsedTime);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
