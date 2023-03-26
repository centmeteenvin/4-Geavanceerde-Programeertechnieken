package SpaceInvaders.entities;

import java.awt.*;

public abstract class Bullet extends Entity{

    /**
     * The Creator of the bullet.<br>
     * Class Type can be checked to prevent friendly fire.<br>
     */
    protected HittableEntity owner;

    /**
     * The speed of the object.
     * This is the amount of displacement added every frame.
     */
    private final int speed = 10;

    /**
     * Default Constructor for Entities.
     *
     * @param location {@link Entity#coordinate}
     * @param owner {@link #owner}
     */
    public Bullet(Point location, HittableEntity owner) {
        super(location);
        this.owner = owner;
    }

    /**
     * Final Implementation of {@link Entity#update()}.
     * Depending on ownership moves the bullet up or down.
     */
    @Override
    public final void update() {
        if (owner instanceof Enemy) {
            this.coordinate.y = coordinate.y - speed;
        }
        else {
            this.coordinate.y = coordinate.y + speed;
        }
    }
}
