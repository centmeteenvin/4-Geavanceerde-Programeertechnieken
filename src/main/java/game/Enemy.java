package game;

import java.awt.*;
import java.util.ArrayList;

public abstract class Enemy extends HittableEntity{
    private final Point bounds;
    private Input currentDirection;
    public Enemy(double health, Point size, Point bounds) {
        super(health, size);
        this.bounds = bounds;
    }

    @Override
    public void update(ArrayList<Entity> entities) {
        if (health <= 0) {
            entities.remove(this);
        }
        else {
            if (coordinate.x < bounds.x) {
                currentDirection = Input.RIGHT;
            }
            else if (coordinate.x > bounds.y) {
                currentDirection = Input.LEFT;
            }
            switch (currentDirection) {
                case RIGHT -> coordinate.x++;
                case LEFT -> coordinate.x--;
            }
        }
    }

    @Override
    public void visualize() {

    }
}
