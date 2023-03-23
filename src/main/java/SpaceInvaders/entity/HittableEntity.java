package SpaceInvaders.entity;

import java.awt.*;
import java.util.ArrayList;

/**
 * An Extension of the {@link Entity} class for Object that can be hit.
 * The Hittable Entity Responsible for collision detection.
 */
public abstract class HittableEntity extends Entity {
    /**
     * The health of an entity.
     * Every time a HittableEntity gets hit, the health goes down by one.
     */
    protected double health;

    /**
     * The size of An Entity
     * If the distance to the position of //TODO link to bullet is is smaller than the size.<br>
     * The HittableEntity gets hit.
     */
    protected double size;

    /**
     * Reference to game's {@link SpaceInvaders.Game#entities entities}.
     * HittableEntities need this list because they can interact with other entities.
     */
    protected ArrayList<Entity> entities;

    /**
     * Default HittableEntity Constructor
     * @param location {@link Entity#coordinate}
     * @param health {@link #health}
     * @param size {@link #size}
     * @param entities {@link #entities}
     */
    public HittableEntity(Point location, double health, double size, ArrayList entities) {
        super(location);
        this.health = health;
        this.size = size;
        this.entities = entities;
    }

    //TODO JavaDoc
    private void getHit() {
        health--;
    }

    //TODO Hitbox detection when bullet class is made
}
