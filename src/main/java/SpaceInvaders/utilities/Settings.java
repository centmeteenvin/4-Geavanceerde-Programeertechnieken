package SpaceInvaders.utilities;

import SpaceInvaders.AbstractFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Holds all the settings for the game.
 * <p>
 * Its fields are private but accessible via getter and setters.<br>
 * It should be passed or created in the {@link SpaceInvaders.AbstractFactory}.<br>
 * The {@link SpaceInvaders.AbstractFactory} passes it to the {@link SpaceInvaders.Game} during initializing.
 */
public class Settings {

    /**
     * The property file/object of the gamesettings.
     * Is read from /resources/gamesetting.properties.
     */
    private Properties properties;

    /**
     * The Amount of Frames per seconds.
     * Frames can be seen as a single GameTick.<br>
     * This does affect the game-speed and not only to amount of frames drawn.<br>
     * If you want to manipulate the draw-speed independently of the game-speed,
     * then this should be done in the {@link AbstractFactory#render()} method.
     */
    private double fps;

    /**
     * The Delay for shooting Player.
     * Expressed in Milliseconds.
     */
    private long playerShootingDelay;

    /**
     * The Speed of bullets.
     * {@link SpaceInvaders.entities.Bullet#speed}
     */
    private int bulletSpeed;

    /**
     * The speed of the {@link SpaceInvaders.entities.Player}.
     */
    private int playerSpeed;

    /**
     * The speed of the {@link SpaceInvaders.entities.Enemy}.
     */
    private int enemySpeed;

    /**
     * Load the gamesetting from the properties file at src/main/resources/gamesetting.properties.
     */
    public void loadFromProperties() {
        properties = new Properties();
        File propertiesFile = new File("src/main/resources/gamesetting.properties");
        try {
            properties.load(new FileReader(propertiesFile));
            fps = Integer.parseInt(properties.getProperty("fps"));
            playerShootingDelay = Long.parseLong(properties.getProperty("playerShootingDelay"));
            bulletSpeed = Integer.parseInt(properties.getProperty("bulletSpeed"));
            playerSpeed = Integer.parseInt(properties.getProperty("playerSpeed"));
            enemySpeed = Integer.parseInt(properties.getProperty("enemySpeed"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Getter for fps.
     * @return {@link #fps}
     */
    public double getFps() {
        return fps;
    }

    /**
     * Setter for fps
     * @param fps {@link #fps}
     */
    public void setFps(double fps) {
        this.fps = fps;
    }

    /**
     * Getter for shootingDelay.
     * @return {@link #playerShootingDelay}
     */
    public long getPlayerShootingDelay() {
        return playerShootingDelay;
    }

    /**
     * Setter for shootingDelay.
     * @param playerShootingDelay {@link #playerShootingDelay}
     */
    public void setPlayerShootingDelay(long playerShootingDelay) {
        this.playerShootingDelay = playerShootingDelay;
    }

    /**
     * Getter for bulletSpeed.
     * @return {@link #bulletSpeed}
     */
    public int getBulletSpeed() {
        return bulletSpeed;
    }

    /**
     * Setter for bulletSpeed.
     * @param bulletSpeed {@link #bulletSpeed}
     */
    public void setBulletSpeed(int bulletSpeed) {
        this.bulletSpeed = bulletSpeed;
    }

    /**
     * Getter for playerSpeed.
     * @return {@link #playerSpeed}.
     */
    public int getPlayerSpeed() {
        return playerSpeed;
    }

    /**
     * Setter for playerSpeed.
     * @param playerSpeed {@link #playerSpeed}
     */
    public void setPlayerSpeed(int playerSpeed) {
        this.playerSpeed = playerSpeed;
    }

    /**
     * Getter for enemySpeed.
     * @return {@link #enemySpeed}.
     */
    public int getEnemySpeed() {
        return enemySpeed;
    }

    /**
     * Setter for enemySpeed
     * @param enemySpeed {@link #enemySpeed}.
     */
    public void setEnemySpeed(int enemySpeed) {
        this.enemySpeed = enemySpeed;
    }
}
