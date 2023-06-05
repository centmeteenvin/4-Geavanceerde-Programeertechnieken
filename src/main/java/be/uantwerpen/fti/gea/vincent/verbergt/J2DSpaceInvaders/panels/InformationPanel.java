package be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.panels;

import be.uantwerpen.fti.gea.vincent.verbergt.J2DSpaceInvaders.GraphicsContext;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Panel containing all information, like game score, time, amount of lives etc.
 */
public class InformationPanel{

    /**
     * GameState containing the necessary information.
     */
    private final GameState gameState;

    /**
     * The Graphical context we are working in.
     */
    private final GraphicsContext graphicsContext;

    /**
     * A preloaded health icon
     */
    private final BufferedImage healthSprite;


    /**
     * The constructor for an InformationPanel.
     * @param graphicsContext The {@link GraphicsContext} we are working in.
     */
    public InformationPanel(GraphicsContext graphicsContext) {
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
    }

    /**
     * Draw the info on top of the game panel after the whole game is rendered.
     */
    public void visualize() {
//        scoreLabel.setText("SCORE: " + gameState.getScore());
        Graphics2D graphics2D = graphicsContext.getGraphics2D();
        graphics2D.setColor(Color.WHITE);
        graphics2D.setFont(new Font("Helvetica", Font.PLAIN, 35));
        graphics2D.drawString("LEVEL " + gameState.getCurrentLevel(), 30, 60);
        graphics2D.drawString("SCORE: " + gameState.getScore(), 30, 110);
        int margin = 20;
        Point anchor = new Point(graphicsContext.getFrame().getWidth() - margin, margin);
        for(int i = 0; i < gameState.getHealth(); i++) {
            int hearthIconSize = 50;
            anchor.x = anchor.x - hearthIconSize;
            graphics2D.drawImage(healthSprite, anchor.x, anchor.y, hearthIconSize, hearthIconSize, null);
            anchor.x = anchor.x - margin;
        }
    }
}
