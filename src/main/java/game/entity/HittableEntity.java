package game.entity;

import java.awt.*;

public abstract class HittableEntity extends Entity {
    protected double health;
    protected double size;

    public HittableEntity(Point location, double health, double size) {
        super(location);
        this.health = health;
        this.size = size;
    }

    public void getHit() {
        health--;
    }
}
