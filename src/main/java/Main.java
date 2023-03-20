import J2D.J2DFactory;
import game.AbstractFactory;
import game.Game;

public class Main {
    public static void main(String args[]) {
        AbstractFactory factory = new J2DFactory(400, 400);
        Game game = new Game(factory, 48);
        game.start();
    }
}
