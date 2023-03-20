import game.AbstractFactory;
import game.Game;
public class Main {
    public static void main(String args[]) {
        System.out.println("Hello World");
        AbstractFactory abstractFactory = null;
        Game game = new Game(abstractFactory);
//        TODO game.start();
    }
}
