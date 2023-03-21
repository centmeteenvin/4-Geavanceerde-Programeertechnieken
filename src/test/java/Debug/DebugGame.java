package Debug;

import SpaceInvaders.*;

public class DebugGame {
    public static void main(String args[]) {
        AbstractFactory abstractFactory = new DebugFactory();
        Game gme = new Game(abstractFactory);
        gme.start();
    }
}
