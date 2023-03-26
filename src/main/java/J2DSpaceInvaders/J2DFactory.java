package J2DSpaceInvaders;

import J2DSpaceInvaders.entities.J2DBullet;
import J2DSpaceInvaders.entities.J2DEnemy;
import J2DSpaceInvaders.entities.J2DPlayer;
import SpaceInvaders.AbstractFactory;
import SpaceInvaders.entities.*;
import SpaceInvaders.utilities.GameState;
import SpaceInvaders.utilities.Settings;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class J2DFactory extends AbstractFactory {

    /**
     * The GraphicsContext of the J2DGame.
     * <p>
     * Used to show a visualisation of the game using J2D.
     * It also uses a double buffered scheme.
     * </p>
     */
    private GraphicsContext graphicsContext;

    private final File J2DSettingsFile = new File("src/main/resources/J2Dsetting.properties");
    /**
     * Default constructor.
     * <p>
     * {@link #gameState}, {@link #settings}, {@link #entities} and {@link #graphicsContext} are initialized empty.<br>
     */
    public J2DFactory() {
        super();
        this.graphicsContext = new GraphicsContext();
        this.inputController = new J2DInputController(graphicsContext);
    }

    /**
     * Overloaded constructor with parameters.
     * <p>
     * {@link #gameState} and {@link #settings} are passed by reference from the parameters.<br>
     * @param gameState reference to a GameState object.
     * @param settings  reference to a Settings object.
     */
    public J2DFactory(GameState gameState, Settings settings) {
        super(gameState, settings);
        this.graphicsContext = new GraphicsContext();
    }

    /**
     * Initializes the fields and settings of the factory.
     * <p>
     * Called once when Game.start() is called.<br>
     * Use this to initialize {@link #gameState} and {@link #settings}.<br>
     * Calls {@link #graphicsContext#initialize()};
     */
    @Override
    public void initialize() {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(J2DSettingsFile));
        } catch (IOException e) {
            System.out.println("Properties file not found for J2D");
            throw new RuntimeException(e);
        }
        graphicsContext.initialize(properties);
        gameState.setCurrentLevel(1);
        gameState.setScore(0);
        gameState.setPlaying(true);

        settings.setFps(60);
        //TODO settings init
    }

    /**
     * Push to completed frame to the user.
     * <p>
     * Is called at the end of every gameLoop.<br>
     * Use this when implementing a buffer draw scheme.
     * Draw to the buffer with the {@link Entity#visualize()} method.
     * Push the frame to the screen with this method.<br>
     * Calls {@link #graphicsContext#render()}.<br>
     */
    @Override
    public void render() {
        graphicsContext.render();
    }

    /**
     * This factory is called when a level is loaded.<br>
     * This should be overwritten returning an Enemy object.
     *
     * @param location Point that defines the starting location.
     * @param health   double that defines the starting health.
     * @param size     double that defines the size, needed for collision detection
     * @param bounds   point that defines the maximum movement in the x-axis direction.
     * @return an Enemy object instantiated with the given parameters.
     */
    @Override
    public Enemy enemyCreator(Point location, int health, double size, Point bounds) {
        return new J2DEnemy(location, health, size, this, bounds);
    }

    /**
     * This factory is called when a level is loaded.<br>
     * This should be overwritten returning a Player object.
     *
     * @param location Point that defines the starting location.
     * @param health   double that defines the starting health.
     * @param size     double that defines the size, need for collision detection.
     * @return a Player object instantiated with the given parameters.
     */
    @Override
    public Player playerCreator(Point location, int health, double size) {
        return new J2DPlayer(location, health, size, this);
    }

    /**
     * This factory is called when a {@link HittableEntity} Shoots.
     *
     * @param location {@link Bullet#coordinate}.
     * @param entity   {@link Bullet#owner}.
     * @return a reference to the Bullet object.
     */
    @Override
    public Bullet bulletCreator(Point location, HittableEntity owner) {
        return new J2DBullet(location, owner, graphicsContext);
    }

    /**
     * Getter for graphicsContext/
     * @return reference to {@link #graphicsContext}
     */
    public GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }

}
