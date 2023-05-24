package J2DSpaceInvaders;

import J2DSpaceInvaders.panels.GamePanel;
import J2DSpaceInvaders.panels.UIPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * The GraphicsContext of the J2D visualisation.
 * <p>
 * Contains the logic for rendering along with accessors for its resources.
 * </p>
 */
public class GraphicsContext {
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
     */
    private GamePanel gamePanel;


    /**
     * The screen size.
     */
    private Size size;


    private JPanel UIPanel;

    /**
     * Default constructor for GraphicsContext.
     * <p>
     * Creates the necessary objects and structure.<br>
     * {@link #initialize(Properties)} needs to be called to finalize this object.
     * </p>
     */
    public GraphicsContext() {
        frame = new JFrame();
        JLayeredPane mainPanel = frame.getLayeredPane();
        gamePanel = new GamePanel(null, true, this);
        UIPanel = new UIPanel(new BorderLayout(), true);
        mainPanel.add(gamePanel, Integer.valueOf(1));
        mainPanel.add(UIPanel, Integer.valueOf(2));

    }

    /**
     * Render {@link #bufferedImage} to {@link #gamePanel}.
     */
    public void render() {
        gamePanel.repaint();
    }

    /**
     * Initialize the frame and size for and all related settings using a Properties object.
     *
     * @param properties properties object.
     */
    public void initialize(Properties properties) {
        size = new Size(Integer.parseInt(properties.getProperty("width")), Integer.parseInt(properties.getProperty("height")));
        bufferedImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
        graphics2D = (Graphics2D) bufferedImage.getGraphics();
        frame.setSize(size.width, size.height);

        gamePanel.initialize();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.setVisible(true);
        frame.setTitle(properties.getProperty("title"));
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

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

}
