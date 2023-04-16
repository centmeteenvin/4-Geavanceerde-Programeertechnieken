package SpaceInvaders.entities;

import SpaceInvaders.AbstractFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * An Extension of the {@link Entity} class for Object that can be hit.
 * The Hittable Entity Responsible for collision detection.
 */
public abstract class HittableEntity extends Entity {
    /**
     * The health of an entities.
     * Every time a HittableEntity gets hit, the health goes down by one.
     */
    protected int health;

    /**
     * The maximum value of {@link #health}.
     * Used to calculate percentage health if needed.
     */
    protected final int maxHealth;

    /**
     * The size of An Entity
     * If the distance to the position of {@link Bullet}  is smaller than the size.<br>
     * The HittableEntity gets hit.
     */
    protected double size;

    /**
     * Reference to game's {@link SpaceInvaders.Game#entities entities}.
     * HittableEntities need this list because they can interact with other entities.
     */
    protected AbstractFactory abstractFactory;

    /**
     * Determine if an entity is friendly or not.
     * Used to determine if a bullet should hit or not.
     */
    protected boolean isFriendly;

    /**
     * Default HittableEntity Constructor
     * @param location {@link Entity#coordinate}
     * @param health {@link #health}
     * @param size {@link #size}
     * @param abstractFactory {@link #abstractFactory}
     */
    public HittableEntity(Point location, int health, double size, AbstractFactory abstractFactory) {
        super(location);
        this.maxHealth = health;
        this.health = health;
        this.size = size;
        this.abstractFactory = abstractFactory;
    }

    /**
     * The method that is activated when a hit happens.<br>
     * Should be called from {@link HittableEntity#update()}.
     */
    private void getHit() {
        health--;
    }

    /**
     * Called after the updates are gathered.
     * <p>
     * This code implements collisionDetection.
     * </p>
     */
    @Override
    public final void update() {

        //Get a list of all the current bullets in the game
        ArrayList<Bullet> bullets = abstractFactory.getEntities().stream() //Get the entity list from the factory.
                .filter(entity -> entity instanceof Bullet) //Filter it for bullets.
                .map(entity -> (Bullet) entity).collect(Collectors.toCollection(ArrayList::new)); //Cast those Entities to Bullets.

        //Get a list of all the bullets in the game that could hurt the current entity and are close enough
        ArrayList<Bullet> damagingBullets = bullets.stream()
                .filter(bullet -> bullet.owner.isFriendly != this.isFriendly) //Filter the bullets so that the owner class of the bullet is different from ours.
                .filter(bullet -> bullet.coordinate.distance(this.coordinate) <= size) // Filter the bullets that are inside our size.
                .collect(Collectors.toCollection(ArrayList::new)); // Collect them in an array

        damagingBullets.forEach(bullet -> getHit()); //For every bullet in this array, call the getHit() method.

        //Remove the bullets that are hitting the targets, using for loop to prevent concurrency
        for (Bullet damagingBullet : damagingBullets) {
            abstractFactory.getEntities().remove(damagingBullet);
        }

        //Health check otherwise call the subclasses' update function.
        if (health <= 0) {
            abstractFactory.getEntities().remove(this);
        }
        else {
            doHittableEntityUpdate();
        }
    }

    /**
     * Use this to implement the doHittableEntityUpdate methods in HittableEntity Objects.
     */
    public abstract void doHittableEntityUpdate();
}
