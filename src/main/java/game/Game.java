package game;

public class Game {
    private AbstractFactory abstractFactory;

    public Game(AbstractFactory abstractFactory) {
        this.abstractFactory = abstractFactory;
    }
}
