import Debug.DebugFactory;
import SpaceInvaders.AbstractFactory;
import SpaceInvaders.entities.Entity;
import SpaceInvaders.utilities.GameState;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameTests {

    @Test
    public void testLevelLoader() {
        ArrayList<Entity> entities = new ArrayList<>();
        AbstractFactory abstractFactory = new DebugFactory();
        GameState gameState = new GameState();
        gameState.setCurrentLevel(1);
        File currentLevelFile = new File("src/main/resources/levels/level_" + gameState.getCurrentLevel());
        try {
            BufferedReader levelReader = new BufferedReader(new FileReader(currentLevelFile));
            String line;
            ArrayList<String> list;
            int i = 0;
            levelReader.readLine(); // read passed the first line, it only contains information about the way the data is formatted.
            line = levelReader.readLine();
            do {
                i++;
                System.out.println("Processing line " + i);
                list = new ArrayList<>(List.of(line.split(";")));
                switch (list.get(0)) {
                    case "Enemy" -> entities.add(abstractFactory.createEnemy(list));
                    case "Player" -> entities.add(abstractFactory.createPlayer(list));
                }
                line = levelReader.readLine();
            }   while (line != null);
        } catch (FileNotFoundException e) {
            System.out.println("Level does not exist");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(entities);
    }
}
