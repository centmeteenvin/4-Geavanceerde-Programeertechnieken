package be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders;

import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.panels.GamePanel;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.panels.InformationPanel;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.panels.UIPanel;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.utilities.Explosion;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.utilities.PreLoader;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.utilities.Props;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.utilities.Spark;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.AbstractFactory;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.GameState;
import javafx.util.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * The GraphicsContext of the J2D visualisation.
 * <p>
 * Contains the logic for rendering along with accessors for its resources.
 * </p>
 */
public class GraphicsContext {

    /**
     * {@link AbstractFactory} to call its {@link AbstractFactory#start()} method and such.
     * This allows this is passed to {@link #uiPanel} so it can link it to the corresponding buttons.
     */
    private final J2DFactory factory;

    /**
     * The graphic that is shown in the
     */
    private Graphics2D graphics2D;

    /**
     * The Image that is drawn on before pushing it to the {@link #graphics2D};
     */
    private BufferedImage bufferedImage;

    /**
     * The Window/frame that is displayed to the user.
     */
    private final JFrame frame;

    /**
     * The panel added to the {@link #frame} where the game is drawn on.
     * is displayed below {@link #informationPanel} and {@link #uiPanel}
     */
    private final GamePanel gamePanel;


    /**
     * The screen size.
     */
    private Size size;

    /**
     * The UIPanel for rendering buttons and titles.
     * is displayed on top of {@link #informationPanel} and {@link #gamePanel}
     */
    private final UIPanel uiPanel;

    /**
     * The information screen containing score, lives etc.
     * Is drawn on the gamePanel.
     */
    private final InformationPanel informationPanel;

    /**
     * A list containing all explosions that need to be rendered.
     */
    public final ArrayList<Explosion> explosions = new ArrayList<>();

    /**
     * A list containing all sparks that need to be rendered.
     */
    public final ArrayList<Spark> sparks = new ArrayList<>();

    /**
     * A class that has all images stored in memeory.
     */
    public final PreLoader preLoader;

    /**
     * Default constructor for GraphicsContext.
     * <p>
     * Creates the necessary objects and structure.<br>
     * {@link #initialize()} needs to be called to finalize this object.
     * </p>
     * @param factory {@link GraphicsContext#factory}
     */
    public GraphicsContext(J2DFactory factory) {
        this.factory = factory;
        this.preLoader = new PreLoader(factory);

        frame = new JFrame();
        JLayeredPane mainPanel = frame.getLayeredPane();
        gamePanel = new GamePanel(null, true, this);
        uiPanel = new UIPanel(null, true, this);
        informationPanel = new InformationPanel( this);

        mainPanel.add(uiPanel, Integer.valueOf(2));
        mainPanel.add(gamePanel, Integer.valueOf(0));

    }

    /**
     * Render {@link #bufferedImage} to {@link #gamePanel}.
     */
    public void render() {
        renderExplosions();
        renderSparks();
        renderInformation();
        gamePanel.repaint();

    }


    /**
     * Loops through {@link #explosions} and draws than on top of the game.
     */
    private void renderExplosions() {
        ArrayList<Explosion> clonedExplosions = (ArrayList<Explosion>) explosions.clone();
        for(Explosion explosion: clonedExplosions) {
            explosion.visualize();
            if (explosion.numberOfFrames - 1 < explosion.frameNumber) {
                explosions.remove(explosion);
            }
        }
    }

    /**
     * Loops through {@link #sparks} and draws than on top of the game.
     */
    private void renderSparks() {
        ArrayList<Spark> clonedSparks = (ArrayList<Spark>) sparks.clone();
        for (Spark spark : clonedSparks) {
            spark.visualize();
            if (spark.numberOfFrames -1 < spark.frameNumber) {
                sparks.remove(spark);
            }
        }
    }

    /**
     * Display score and amount of hearts.
     */
    private void renderInformation() {
        informationPanel.visualize();
    }

    /**
     * Initialize the frame and size for and all related settings using a Properties object.
     */
    public void initialize() {
        Props properties = factory.properties;
        size = new Size(properties.width, properties.height);
        bufferedImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
        graphics2D = (Graphics2D) bufferedImage.getGraphics();
        frame.setSize(size.width, size.height);

        gamePanel.initialize();
        uiPanel.initialize();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.setVisible(true);
        frame.setTitle(properties.title);
    }

    /**
     * Translates the Games coordinates to screen coordinates.
     *
     * @param gamePoint game coordinates.
     * @return screen coordinates.
     */
    public Point coordinateTranslation(Point gamePoint) {
        Point screenPoint = new Point();
        screenPoint.x = (int) ((gamePoint.x + 500.0) / 1000 * size.width);
        screenPoint.y = (size.height - 100) - (int) (1.0 * gamePoint.y / 1000 * (size.height - 100));
        return screenPoint;
    }

    /**
     * Translates the game's sizes to screen sizes.
     * //TODO Could be improved.
     *
     * @param gameSize gameSize
     * @return screenSize
     */
    public int sizeTranslation(double gameSize) {
        return (int) (gameSize / 1000 * size.width);
    }

    /**
     * Getter for graphics2D.
     *
     * @return reference to {@link #graphics2D}
     */
    public Graphics2D getGraphics2D() {
        return graphics2D;
    }

    /**
     * Getter for frame.
     *
     * @return reference to {@link #frame}
     */
    public JFrame getFrame() {
        return frame;
    }

    /**
     * getter for the bufferedImage we are drawing the game on.
     * @return reference to {@link #bufferedImage}
     */
    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    /**
     * Calls the {@link AbstractFactory#start()} method.
     */
    public void start() {
//        frame.remove(uiPanel);
        factory.start();
    }

    /**
     * Displays the Beginscreen again.
     */
    public void gameOver() {
        uiPanel.setVisible(true);
    }

    /**
     * End the game by calling {@link AbstractFactory#end()};
     */
    public  void end() {
        factory.end();
    }

    /**
     * Get the gameState singleton.
     * @return reference to {@link GameState}.
     */
    public GameState getGameState() {
        return factory.getGameState();
    }

    /**
     * The J2D properties.
     * @return {@link J2DFactory#properties}.
     */
    public Props getProps() {
        return factory.properties;
    }

    /**
     * Displays an explosion gif on {@link #gamePanel} at the give coordinate.
     * {@link Explosion}.
     * @param coordinate the coordinate where the animation should be drawn.
     */
    public void explosion(Point coordinate) {
        explosions.add(new Explosion((Point) coordinate.clone(), factory));
    }

    /**
     * Add a new {@link Spark} to {@link #sparks}.
     * @param coordinate The location of the spark.
     */
    public void spark(Point coordinate) {
        sparks.add(new Spark((Point) coordinate.clone(), factory));
    }

    /**
     * Gets the scores from the factory.
     * @return The {@link J2DFactory#scores}.
     */
    public ArrayList<Pair<String, Integer>> getScores() {
        return factory.scores;
    }
}
