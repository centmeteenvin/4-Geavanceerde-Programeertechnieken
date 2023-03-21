package game;

import java.awt.*;

public abstract class HittableEntity extends Entity {
    protected double health;
    protected Point size;

    public HittableEntity(Point location, double health, Point size) {
        super(location);
        this.health = health;
        this.size = size;
    }

    public void getHit() {
        health--;
    }
}
