package J2DSpaceInvaders;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
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
     * The Window/frame that is displayed to the user.
     */
    private JFrame frame;

    /**
     * The panel added to the {@link #frame} where the game is drawn on.
     */
    private JPanel panel;

    /**
     * The graphic that is shown in the {@link #panel}.
     */
    private Graphics2D graphics2D;

    /**
     * The Image that is drawn on before pushing it to the {@link #graphics2D};
     */
    private BufferedImage bufferedImage;

    /**
     * The screen size.
     */
    private Size size;

    private BufferedImage background;


    /**
     * Default constructor for GraphicsContext.
     * <p>
     * Creates the necessary objects and structure.<br>
     * {@link #initialize(Properties)} needs to be called to finalize this object.
     * </p>
     */
    public GraphicsContext() {
        frame = new JFrame();
        panel = new JPanel(true) {
            /**
             * Paints each of the components in this container.
             * @param g the graphics context.
             * @see Component#paint
             * @see Component#paintAll
             */
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                doPainting(g);
            }
        };
        frame.add(panel);

        try {
            File imageFile = new File("src/main/resources/J2D/background.png");
            background = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.out.println("Background Image Not Found");
            throw new RuntimeException(e);
        }

    }

    /**
     * Render {@link #bufferedImage} to {@link #panel}.
     */
    public void render() {
        panel.repaint();
    }

    /**
     * Initialize the frame and size for and all related settings using a Properties object.
     *
     * @param properties properties object.
     */
    public void initialize(Properties properties) {
        size = new Size(Integer.parseInt(properties.getProperty("width")), Integer.parseInt(properties.getProperty("height")));
        bufferedImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        graphics2D = bufferedImage.createGraphics(); //link graphics2D to the bufferedImage.
        graphics2D.setBackground(new Color(255, 255, 255));
        graphics2D.clearRect(0, 0, frame.getWidth(), frame.getHeight());
        frame.setSize(size.width, size.height);
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

    /**
     * Extending the logic of {@link #panel} to enable double buffering.<br>
     *
     * @param g the graphics context.
     */
    private void doPainting(Graphics g) {
        Graphics2D graph2d = (Graphics2D) g; //convert the graphics context to a 2d graphics context.
        Toolkit.getDefaultToolkit().sync(); //Black magic.
        graph2d.drawImage(bufferedImage, 0, 0, null); //Draw the bufferedImage to the current screen.
        graph2d.dispose(); //Release resources concerned with buffering.
        //Cleanup the normal buffered graphics.
        if (graphics2D != null) {
            graphics2D.clearRect(0, 0, frame.getWidth(), frame.getHeight());
            graphics2D.drawImage(background, 0, 0, null);
        }
    }

}
