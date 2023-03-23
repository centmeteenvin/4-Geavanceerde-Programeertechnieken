package Debug;

import SpaceInvaders.entities.Enemy;

import java.awt.*;

public class DebugEnemy extends Enemy {
    public DebugEnemy(Point location, double health, double size, Point bounds) {
        super(location, health, size, bounds);
    }

    @Override
    public void visualize() {
        System.out.println(coordinate);
    }
}
