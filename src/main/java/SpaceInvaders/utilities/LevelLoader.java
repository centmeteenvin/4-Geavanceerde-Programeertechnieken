package SpaceInvaders.utilities;

import SpaceInvaders.AbstractFactory;
import SpaceInvaders.entities.Wall;
import SpaceInvaders.entities.enemies.DefaultEnemy;
import SpaceInvaders.entities.Entity;
import SpaceInvaders.entities.Player;
import SpaceInvaders.entities.enemies.ShootingEnemy;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handle all the level loading logic.
 * Mainly reading a levelFile and retrieving the params.
 */
public class LevelLoader {
    /**
     * Reference to {@link AbstractFactory} to make the correct calls.
     */
    private AbstractFactory abstractFactory;

    /**
     * The constructor.
     * Mainly used to access the methods and get an abstractFactory reference.
     *
     * @param abstractFactory {@link #abstractFactory}
     */
    public LevelLoader(AbstractFactory abstractFactory) {
        this.abstractFactory = abstractFactory;
    }

    /**
     * Handles Parsing of levelFile to the correct AbstractFactory calls with the correct parameters.
     *
     * @param levelFile The file of the current level.
     * @return an ArrayList with all the level entities.
     * @throws FileNotFoundException If the file doesn't exist.
     */
    public ArrayList<Entity> LoadLevel(File levelFile) throws FileNotFoundException {
        ArrayList<Entity> entities = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(levelFile));
            reader.readLine(); //read passed the first line because it contains the formatting info

            String line;
            ArrayList<String> list;
            line = reader.readLine();
            do {
                list = new ArrayList<>(List.of(line.split(";")));
                switch (list.get(0)) {
                    case "DefaultEnemy" -> entities.add(createDefaultEnemy(list));
                    case "Player" -> entities.add(createPlayer(list));
                    case "ShootingEnemy" -> entities.add(createShootingEnemy(list));
                    case "Wall" -> entities.add(createWall(list));
                }
                line = reader.readLine();
            } while (line != null);

        } catch (IOException e) {
            if (e instanceof FileNotFoundException) { //filter out file not found errors indicating that the level does not exist, error should be handled by the game
                throw new FileNotFoundException();
            }
            throw new RuntimeException(e);
        }
        return entities;
    }

    /**
     * Is used to create the enemy.
     * <p>
     * It's mainly used to parse the parameter list into the needed attributes to create an Enemy.<br>
     * The generated parameters are passed to the defaultEnemyCreator;
     *
     * @param parameters Is the list of parameters that is read from the level file.
     * @return An instance of Enemy that has the given parameters.
     */
    private DefaultEnemy createDefaultEnemy(ArrayList<String> parameters) {
        //Type;Health;Size;Location;bounds
        int health = Integer.parseInt(parameters.get(1));
        double size = Double.parseDouble(parameters.get(2));
        String[] tempList = parameters.get(3).split(",");
        Point location = new Point(Integer.parseInt(tempList[0]), Integer.parseInt(tempList[1]));
        return abstractFactory.defaultEnemyCreator(location, health, size);
    }

    /**
     * Is used to create the player.
     * <p>
     * It's mainly used to parse the parameter list into the needed attributes to create a Player object.<br>
     * The generated parameters are passed to the playerCreator.<br>
     *
     * @param parameters Is the list of parameters that is read from the level file.
     * @return An instance of Player that has the given parameters.
     */
    private Player createPlayer(ArrayList<String> parameters) {
        //Type;Health;Size;Location;bounds
        int health = Integer.parseInt(parameters.get(1));
        double size = Double.parseDouble(parameters.get(2));
        String[] tempList = parameters.get(3).split(",");
        Point location = new Point(Integer.parseInt(tempList[0]), Integer.parseInt(tempList[1]));
        return abstractFactory.playerCreator(location, health, size);
    }

    /**
     * Is used to create a ShootingEnemy.
     * <p>
     * Main function is parsing the parameters list into its component attributes.<br>
     * Than it call {@link AbstractFactory#shootingEnemyCreator(Point, int, double, Point, double)} to make the object.<br>
     * </p>
     *
     * @param parameters Is the list of parameters received from the level file.
     * @return An instance of shootingEnemy
     */
    private ShootingEnemy createShootingEnemy(ArrayList<String> parameters) {
        //Type;Health;Size;Location;bounds;averageTimeToShoot
        int health = Integer.parseInt(parameters.get(1));
        double size = Double.parseDouble(parameters.get(2));
        String[] tempList = parameters.get(3).split(",");
        Point location = new Point(Integer.parseInt(tempList[0]), Integer.parseInt(tempList[1]));
        double averageTimeToShoot = Double.parseDouble(parameters.get(4));
        return abstractFactory.shootingEnemyCreator(location, health, size , averageTimeToShoot);
    }

    /**
     * Is used to create the Wall.
     * <p>
     * It's mainly used to parse the parameter list into the needed attributes to create a Wall object.<br>
     * The generated parameters are passed to the wallCreator.<br>
     *
     * @param parameters Is the list of parameters that is read from the level file.
     * @return An instance of Wall that has the given parameters.
     */
    private Wall createWall(ArrayList<String> parameters) {
        //Type;Health;Size;Location
        int health = Integer.parseInt(parameters.get(1));
        double size = Double.parseDouble(parameters.get(2));
        String[] templist = parameters.get(3).split(",");
        Point location = new Point(Integer.parseInt(templist[0]), Integer.parseInt(templist[1]));
        return abstractFactory.wallCreator(location, health, size);
    }


}
