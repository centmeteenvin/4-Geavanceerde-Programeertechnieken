package game;

import java.awt.*;

public abstract class HittableEntity extends Entity {
    private double health;
    private Point size;

    public HittableEntity(double health, Point size) {
        this.health = health;
        this.size = size;
    }

    public abstract void getHit();
}
