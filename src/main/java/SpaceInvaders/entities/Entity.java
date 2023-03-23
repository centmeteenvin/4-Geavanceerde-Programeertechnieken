package SpaceInvaders.entities;

import java.awt.Point;

/**
 * Base abstract class for all the things that can move and/or be interacted with.
 */
public abstract class Entity {
    /**
     * All Entities have a coordinate field to track its position.
     */
    protected Point coordinate;

    /**
     * Default Constructor for Entities.
     * @param location {@link #coordinate}
     */
    public Entity(Point location) {
        this.coordinate = location;
    }

    /**
     * Called after the updates are gathered
     */
    public abstract void update();

    /**
     * Called after updates.
     */
    public abstract void visualize();
}
