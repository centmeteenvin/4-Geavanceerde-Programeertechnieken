package J2DSpaceInvaders;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Properties;

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
            public void paintComponents(Graphics g) {
                super.paintComponents(g);
                doPainting(g);
            }
        };
        frame.add(panel);
    }

    /**
     * Render {@link #bufferedImage} to {@link #panel}.
     */
    public void render() {
        panel.repaint();
    }

    /**
     * Initialize the frame and size for and all related settings using a Properties object.
     * @param properties properties object.
     */
    public void initialize(Properties properties) {
        size = new Size(Integer.parseInt(properties.getProperty("width")), Integer.parseInt(properties.getProperty("width")));
        bufferedImage = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
        graphics2D = bufferedImage.createGraphics(); //link graphics2D to the bufferedImage.
        frame.setSize(size.width, size.height);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setFocusable(true);
        frame.setVisible(true);
        frame.setTitle(properties.getProperty("title"));
    }



    /**
     * Extending the logic of {@link #panel#paint(Graphics)} to enable double buffering.<br>
     * @param g the graphics context.
     */
    private void doPainting(Graphics g) {
        Graphics2D graph2d = (Graphics2D) g; //convert the graphics context to a 2d graphics context.
        Toolkit.getDefaultToolkit().sync(); //Black magic.
        graph2d.drawImage(bufferedImage, 0, 0, null); //Draw the bufferedImage to the current screen.
        graph2d.dispose(); //Release resources concerned with buffering.
        //Cleanup the normal buffered graphics.
        if (graphics2D != null)
            graphics2D.clearRect(0, 0, frame.getWidth(), frame.getHeight());
    }
}