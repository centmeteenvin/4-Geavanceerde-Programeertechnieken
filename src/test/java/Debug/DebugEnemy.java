package Debug;

import game.entity.Enemy;
import game.entity.Entity;

import java.awt.*;
import java.util.ArrayList;

public class DebugEnemy extends Enemy {
    public DebugEnemy(Point location, double health, double size, Point bounds) {
        super(location, health, size, bounds);
    }

    @Override
    public void update(ArrayList<Entity> entities) {
        super.update(entities);
        System.out.println(coordinate);
    }
}
