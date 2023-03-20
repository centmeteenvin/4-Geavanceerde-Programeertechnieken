package TxT;

import game.AbstractFactory;
import game.Player;
import game.Visualizer;

public class TxTFactory extends AbstractFactory {

    @Override
    public Player createPlayer() {
        return new TxTPlayer();
    }

    @Override
    public Visualizer createVisualizer() {
        return null;
    }
}
