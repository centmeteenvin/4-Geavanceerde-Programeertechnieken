package J2D;


import game.AbstractFactory;
import game.Player;
import game.Visualizer;

public class J2DFactory extends AbstractFactory {
    private GraphicsContext graphicsContext;

    public J2DFactory(int width, int height) {
        this.graphicsContext = new GraphicsContext(width, height);
    }

    @Override
    public Player createPlayer() {
        return new J2DPlayer(graphicsContext);
    }

    @Override
    public Visualizer createVisualizer() {
        return graphicsContext;
    }


}
