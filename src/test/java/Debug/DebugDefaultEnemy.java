package Debug;

import SpaceInvaders.AbstractFactory;
import SpaceInvaders.entities.enemies.DefaultEnemy;

import java.awt.*;

public class DebugDefaultEnemy extends DefaultEnemy {
    public DebugDefaultEnemy(Point location, int health, double size, AbstractFactory abstractFactory, Point bounds) {
        super(location, health, size, abstractFactory, bounds);
    }

    @Override
    public void visualize() {
        System.out.println(coordinate);
    }
}
