package Debug;

import SpaceInvaders.AbstractFactory;
import SpaceInvaders.entities.enemies.DefaultEnemy;

import java.awt.*;

public class DebugDefaultEnemy extends DefaultEnemy {
    public DebugDefaultEnemy(Point location, int health, double size, AbstractFactory abstractFactory) {
        super(location, health, size, abstractFactory);
    }

    @Override
    public void visualize() {
        System.out.println(coordinate);
    }
}
