package game;

import java.awt.*;

/**
 * Abstract player class, defines the methods that are needed to run the game
 */
public abstract class Player {
    protected Point coordinate;

    /**
     * Initializes player Coordinate at 0,0
     */
    public Player() {
        coordinate = new Point(0,0);
    }

    /**
     * Method that visualizes the player object.
     * Beware there is also a render call to the abstract visualizer at the end of each game loop.
     * This method is called every game loop after visualization.
     */
    public abstract void visualize();

    /**
     * Method that updates the player object.
     * this can contain position changes etc.
     * This method is called every frame before the visualization.
     */
    public abstract void update();

    @Override
    public String toString() {
        return "game.Player(" + coordinate.toString() + ")";
    }
}
