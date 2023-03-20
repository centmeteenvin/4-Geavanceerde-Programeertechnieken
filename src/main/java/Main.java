import game.AbstractFactory;
import game.Game;
public class Main {
    public static void main(String args[]) {
        System.out.println("Hello World");
        AbstractFactory abstractFactory = null; //Give a concrete implementation of the AbstractFactory
        Game game = new Game(abstractFactory); //Pass it into a new gameObject
//        TODO game.start(); // Call start method and the game starts playing
    }
}
