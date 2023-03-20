package game;

public class Game {
    private AbstractFactory abstractFactory;

    public Game(AbstractFactory abstractFactory) {
        this.abstractFactory = abstractFactory;
    }

    public void start() {
        initialize();
//        TODO loadLevel();
        gameLoop();
    }

    public void initialize() {

    }

    public void gameLoop() {
        while(true) {

        }
    }
}
