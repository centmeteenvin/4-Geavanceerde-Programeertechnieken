package SpaceInvaders.utilities;

import SpaceInvaders.AbstractFactory;

/**
 * Holds all the settings for the game.
 * <p>
 * Its fields are private but accessible via getter and setters.<br>
 * It should be passed or created in the {@link SpaceInvaders.AbstractFactory}.<br>
 * The {@link SpaceInvaders.AbstractFactory} passes it to the {@link SpaceInvaders.Game} during initializing.
 */
public class Settings {

    /**
     * The Amount of Frames per seconds.
     * Frames can be seen as a single GameTick.<br>
     * This does affect the game-speed and not only to amount of frames drawn.<br>
     * If you want to manipulate the draw-speed independently of the game-speed,
     * then this should be done in the {@link AbstractFactory#render()} method.
     */
    private double fps;

    public double getFps() {
        return fps;
    }

    public void setFps(double fps) {
        this.fps = fps;
    }
}
