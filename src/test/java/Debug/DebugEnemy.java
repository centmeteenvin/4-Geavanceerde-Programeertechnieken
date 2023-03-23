package Debug;

import SpaceInvaders.AbstractFactory;
import SpaceInvaders.entities.Enemy;

import java.awt.*;

public class DebugEnemy extends Enemy {
    public DebugEnemy(Point location, double health, double size, AbstractFactory abstractFactory,Point bounds) {
        super(location, health, size, abstractFactory, bounds);
    }

    @Override
    public void visualize() {
        System.out.println(coordinate);
    }
}
