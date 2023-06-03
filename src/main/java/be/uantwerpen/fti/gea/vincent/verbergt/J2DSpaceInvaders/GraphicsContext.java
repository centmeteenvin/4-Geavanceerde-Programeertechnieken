package be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders;

import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.panels.GamePanel;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.panels.InformationPanel;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.utilities.Props;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.AbstractFactory;
import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.panels.UIPanel;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

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
    private J2DFactory factory;

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
    private JFrame frame;

    /**
     * The panel added to the {@link #frame} where the game is drawn on.
     * is displayed below {@link #informationPanel} and {@link #uiPanel}
     */
    private GamePanel gamePanel;


    /**
     * The screen size.
     */
    private Size size;

    /**
     * The UIPanel for rendering buttons and titles.
     * is displayed on top of {@link #informationPanel} and {@link #gamePanel}
     */
    private UIPanel uiPanel;

    /**
     * The information screen containing score, lives etc.
     * is displayed on top of {@link #gamePanel} and below {@link #uiPanel}
     */
    private InformationPanel informationPanel;

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
        frame = new JFrame();
        JLayeredPane mainPanel = frame.getLayeredPane();
        gamePanel = new GamePanel(null, true, this);
        uiPanel = new UIPanel(null, true, this);
        informationPanel = new InformationPanel(null, false, this);

        mainPanel.add(gamePanel, Integer.valueOf(1));
        mainPanel.add(informationPanel, Integer.valueOf(2));
        mainPanel.add(uiPanel, Integer.valueOf(3));

    }

    /**
     * Render {@link #bufferedImage} to {@link #gamePanel}.
     */
    public void render() {
        gamePanel.repaint();
        informationPanel.update();
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
        informationPanel.initialize();
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
}
