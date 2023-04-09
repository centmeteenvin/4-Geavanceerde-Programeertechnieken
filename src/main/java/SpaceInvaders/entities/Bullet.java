package SpaceInvaders.entities;

import SpaceInvaders.entities.enemies.Enemy;
import SpaceInvaders.utilities.Settings;

import java.awt.*;


/**
 * Abstract Class Template for bullets.
 * These objects should be created in {@link SpaceInvaders.AbstractFactory#bulletCreator(Point, HittableEntity)}.
 */
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
    private final int speed;

    /**
     * Default Constructor for Entities.
     *
     * @param location {@link Entity#coordinate}
     * @param owner    {@link #owner}
     * @param settings {@link SpaceInvaders.Game#settings}. Used for the bulletspeed
     */
    public Bullet(Point location, HittableEntity owner, Settings settings) {
        super(location);
        this.owner = owner;
        this.speed = settings.getBulletSpeed();
    }

    /**
     * Final Implementation of {@link Entity#update()}.
     * Depending on ownership moves the bullet up or down.
     */
    @Override
    public final void update() {
        if (this.coordinate.y > 1000 || this.coordinate.y < 0) {
           owner.abstractFactory.getEntities().remove(this);
        }
        else if (owner instanceof Enemy) {
            this.coordinate.y = coordinate.y - speed;
        }
        else {
            this.coordinate.y = coordinate.y + speed;
        }
    }
}
