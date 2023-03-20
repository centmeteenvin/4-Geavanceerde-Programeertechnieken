package J2D;

import game.Visualizer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GraphicsContext extends Visualizer {
    private JFrame frame;
    private JPanel panel;
    private Graphics2D graphics2D; //stuur naar hier
    private BufferedImage bufferedImage; //teken hier
    private final int height, width;

    GraphicsContext(int width, int height) {
        this.height = height;
        this.width = width;
        frame = new JFrame("J2D visualization");
        panel = new JPanel(true) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                doDraw(g);
            }
        };
        frame.add(panel);
        frame.setFocusable(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(width, height);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void doDraw(Graphics g) {
        Graphics2D graph2d = (Graphics2D) g;
        Toolkit.getDefaultToolkit().sync();
        graph2d.drawImage(bufferedImage, 0, 0, null);   // copy buffered image
        graph2d.dispose();
        if (graphics2D != null) {
            graphics2D.clearRect(0, 0, frame.getWidth(), frame.getHeight());
        }
    }

    public Graphics2D getGraphics2D() {
        return graphics2D;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    @Override
    public void initialize() {
        frame.setLocation(50, 50);
        bufferedImage = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_RGB);
        graphics2D = bufferedImage.createGraphics();
        graphics2D.setBackground(new Color(255, 255, 255));
        graphics2D.clearRect(0,0, frame.getWidth(), frame.getHeight());
    }
    @Override
    public void render() {
        panel.repaint();
    }
}
