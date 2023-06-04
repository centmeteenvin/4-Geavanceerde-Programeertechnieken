package be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.panels;

import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.GraphicsContext;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.GameState;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Panel containing all information, like game score, time, amount of lives etc.
 */
public class InformationPanel extends JPanel {

    /**
     * GameState containing the necessary information.
     */
    private final GameState gameState;

    /**
     * The Graphical context we are working in.
     */
    private final GraphicsContext graphicsContext;

    /**
     * Label containing the score.
     * Is displayed on the top right of the game.
     */
    private final JLabel scoreLabel = new JLabel("SCORE: ", SwingConstants.CENTER);

    /**
     * The size of a hearth/life Icon.
     */
    private final int hearthIconSize = 50;

    /**
     * A preloaded health icon
     */
    private final BufferedImage healthSprite;


    /**
     * The constructor for an InformationPanel.
     * @param layout The LayoutManager, recommend: null.
     * @param isDoubleBuffered, double-buffer the screen.
     * @param graphicsContext The {@link GraphicsContext} we are working in.
     */
    public InformationPanel(LayoutManager layout, boolean isDoubleBuffered, GraphicsContext graphicsContext) {
        super(layout, isDoubleBuffered);
        this.graphicsContext = graphicsContext;
        this.gameState = graphicsContext.getGameState();
        try
        {
            healthSprite = ImageIO.read(new File("src/main/resources/J2D/health.png"));
        } catch(
                IOException e)

        {
            System.out.println("Sprite for ShootingEnemy Not found");
            throw new RuntimeException(e);
        }
        setOpaque(false);

        add(scoreLabel);
    }

    /**
     * Initialize the panel with correct size after the frame size is set.
     */
    public void initialize() {
        setSize(graphicsContext.getFrame().getSize());

        scoreLabel.setSize(250, 100);
//        scoreLabel.setBorder(new LineBorder(Color.white));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Helevetica", Font.PLAIN, 40));
//        scoreLabel.setVisible(false);
    }

    /**
     * Paint the  amount of hearts on the screen.
     * @param g the graphic we are drawing on.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        scoreLabel.setText("SCORE: " + gameState.getScore());
        int margin = 20;
        Point anchor = new Point(getWidth() - margin, margin);
        for(int i = 0; i < gameState.getHealth(); i++) {
            anchor.x = anchor.x - hearthIconSize;
            g.drawImage(healthSprite, anchor.x, anchor.y, hearthIconSize, hearthIconSize, this);
            anchor.x = anchor.x - margin;
        }
    }


}
