import J2DSpaceInvaders.J2DFactory;
import SpaceInvaders.*;

/**
 * Main function.
 */
public class Main {
    /**
     * Start game here.
     * @param args commandline arguments.
     */
    public static void main(String args[]) {
//        System.out.println("Hello World");
        AbstractFactory abstractFactory = new J2DFactory(); //Give a concrete implementation of the AbstractFactory
        Game game = new Game(abstractFactory); //Pass it into a new gameObject
        game.start(); // Call start method and the SpaceInvaders starts playing
    }
}
