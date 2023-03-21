package game.entity;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Base abstract class for all the things that can move and/or be interacted with.
 */
public abstract class Entity {
    /**
     * All Entities have a coordinate field to track its position.
     */
    protected Point coordinate;

    public Entity(Point location) {
        this.coordinate = location;
    }
    /**
     * Called after the updates are gathered
     */
    public abstract void update(ArrayList<Entity> entities);

    /**
     * Called after updates.
     */
    public abstract void visualize();
}
