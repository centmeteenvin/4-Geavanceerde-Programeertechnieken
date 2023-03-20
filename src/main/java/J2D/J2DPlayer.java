package J2D;

import game.Player;

import java.awt.*;

public class J2DPlayer extends Player {
    private GraphicsContext graphicsContext;

    public J2DPlayer(GraphicsContext graphicsContext) {
        super();
        this.graphicsContext = graphicsContext;
    }

    @Override
    public void visualize() {
        Graphics2D graphics2D = graphicsContext.getGraphics2D();
        int height = graphicsContext.getHeight();
        int width = graphicsContext.getWidth();
        graphics2D.setColor(new Color(255, 0, 0));
        graphics2D.drawRect(coordinate.x, coordinate.y, width/3, height/3);
    }

    @Override
    public void update() {
        coordinate.x += 1;
        coordinate.y += 1;
    }
}
