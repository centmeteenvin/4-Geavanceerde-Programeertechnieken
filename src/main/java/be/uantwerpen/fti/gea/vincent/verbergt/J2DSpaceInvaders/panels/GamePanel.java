package be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.panels;

import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.GraphicsContext;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * The panel where the game is drawn on.
 */
public class GamePanel extends JPanel {
    /**
     * An image containing the background of the game.
     */
    private final BufferedImage background;

    /**
     * The GraphicsContext we are working in.
     */
    private final GraphicsContext graphicsContext;



    /**
     * Default constructor
     *
     * @param layout           the layout manager
     * @param isDoubleBuffered is doubleBuffered?
     * @param graphicsContext  The graphicContext. Used for the size and frame.
     */
    public GamePanel(LayoutManager layout, boolean isDoubleBuffered, GraphicsContext graphicsContext) {
        super(layout, isDoubleBuffered);
        this.graphicsContext = graphicsContext;
        try {
            File imageFile = new File("src/main/resources/J2D/background.png");
            background = ImageIO.read(imageFile);
        } catch (IOException e) {
            System.out.println("Background Image Not Found");
            throw new RuntimeException(e);
        }
    }



    /**
     * Initialize {@link #getSize()} and its subcomponents' sizes after the frame size is set.
     */
    public void initialize() {
        setSize(graphicsContext.getFrame().getSize());
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage bufferedImage = graphicsContext.getBufferedImage();
        Graphics2D graphics2D = graphicsContext.getGraphics2D();
        Graphics2D graph2d = (Graphics2D) g; //convert the graphics context to a 2d graphics context.
        Toolkit.getDefaultToolkit().sync(); //Black magic.
        graph2d.drawImage(bufferedImage, 0, 0, null); //Draw the bufferedImage to the current screen.
        graph2d.dispose(); //Release resources concerned with buffering.
        //Cleanup the normal buffered graphics.
        if (graphics2D != null) {
            graphics2D.clearRect(0, 0, graphicsContext.getFrame().getWidth(), graphicsContext.getFrame().getHeight());
            graphics2D.drawImage(background, 0, 0, null);
        }
    }
}
