package be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.utilities;

import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.AbstractFactory;

import java.awt.*;
import java.io.IOException;
import java.util.Properties;
import java.io.*;
/**
 * Class that holds a lot of constant for J2D visualisation like filenames to certain sounds.
 * I'm also dropping all setters and getters to reduce verbosity.
 */
public class Props {
    /**
     * The File containing the J2D settings such as screensize.
     */
    private final File J2DSettingsFile = new File("src/main/resources/J2D/J2Dsetting.properties");


    /**
     * The default constructor.
     * Loads the props from {@link #J2DSettingsFile}.
     */
    public Props() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(J2DSettingsFile));
            loadFromPropertiesFile(properties);
        } catch (IOException e) {
            System.out.println("Properties file not found for J2D");
            throw new RuntimeException(e);
        }
    }

    /**
     * Assign our fields from the properties file.
     * @param properties The Properties file containing all the data.
     */
    private void loadFromPropertiesFile(Properties properties) {
        width = Integer.parseInt(properties.getProperty("width"));
        height = Integer.parseInt(properties.getProperty("height"));
        title = properties.getProperty("title");
        playerShootingSound = properties.getProperty("playerShootingSound");
        enemyShootingSound = properties.getProperty("enemyShootingSound");
        hitSound = properties.getProperty("hitSound");
        returnIcon = properties.getProperty("returnIcon");
        exterminatorSprite = properties.getProperty("exterminatorSprite");
        playerSprite = properties.getProperty("playerSprite");
        defaultEnemySprite = properties.getProperty("defaultEnemySprite");
        friendlyBulletSprite = properties.getProperty("friendlyBulletSprite");
        hostileBulletSprite = properties.getProperty("hostileBulletSprite");
        explosionSprite = properties.getProperty("explosionSprite");
        sparkSprite = properties.getProperty("sparkSprite");
    }

    /**
     * The screen width
     */
    public int width;

    /**
     * The screen height
     */
    public int height;

    /**
     * The title of the frame.
     */
    public String title;

    /**
     * File path to the shooting sound for a player.
     */
    public String playerShootingSound;

    /**
     * File path to the shooting sound for an enemy.
     */
    public String enemyShootingSound;

    /**
     * {@link be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.Event.Type#GOT_HIT}.
     */
    public String hitSound;

    /**
     * The return Icon;
     */
    public String returnIcon;

    /**
     * {@link be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.entities.bosses.J2DExterminator#sprite}.
     */
    public String exterminatorSprite;

    /**
     * {@link be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.entities.J2DPlayer#sprite}.
     */
    public String playerSprite;

    /**
     * {@link be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.entities.J2DDefaultEnemy#sprite}.
     */
    public String defaultEnemySprite;

    /**
     * {@link be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.entities.J2DBullet#sprite}
     */
    public String friendlyBulletSprite;

    /**
     * {@link be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.entities.J2DBullet#sprite}
     */
    public String hostileBulletSprite;

    /**
     * {@link Explosion}.
     */
    public String explosionSprite;

    /**
     * {@link Spark}.
     */
    public String sparkSprite;

}
