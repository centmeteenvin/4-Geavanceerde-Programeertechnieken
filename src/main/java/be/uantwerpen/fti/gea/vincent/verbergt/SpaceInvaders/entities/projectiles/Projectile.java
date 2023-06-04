package be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.projectiles;

import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.AbstractFactory;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.Game;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.Entity;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.HittableEntity;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.Constants;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.Event;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * The abstract base class for all projectiles in the game.
 * These are the objects that can hit the on {@link HittableEntity}.
 * When a collision is detected the //TODO
 */
public abstract class Projectile extends Entity {

    /**
     * The direction of the projectile.
     * The magnitude of the vector encoded the speeds for the entity and should always remain equal.
     */
    protected Vector2D direction;


    /**
     * The owner of the projectile.
     * Used to determine if a bullet is friendly or not.
     */
    protected final HittableEntity owner;

    /**
     * A list of all the entities.
     * Is a reference to {@link AbstractFactory#entities}.
     * Is used for collision detection.
     */
    protected final ArrayList<Entity> entities;

    /**
     * The {@link AbstractFactory} of the game.
     * Used to get the {@link AbstractFactory#entities} list.
     * Also used to add events to the {@link AbstractFactory#eventQueue}.
     */
    protected final AbstractFactory factory;

    /**
     * The default constructor for a projectile.
     * @param location The starting {@link #coordinate} of the projectile.
     * @param direction The initial direction of the projectile. {@link #direction}.
     * @param owner  The owner of the projectile.
     * @param factory The factory containing a reference to {@link Game#entities} and the {@link AbstractFactory#addEvent} method.
     */
    public Projectile(Point location, Vector2D direction, HittableEntity owner, AbstractFactory factory) {
        super(location);
        this.direction = direction;
        this.direction.normalize();
        this.owner = owner;
        this.factory = factory;
        this.entities = factory.getEntities();
    }

    /**
     * Called after the entities are gathered.
     * <p>
     *     1. Update the position.<br>
     *     2. Checks for collisions.<br>
     *      2.1. If collision occurs calls {@link #doHit(HittableEntity)}.<br>
     *     3. Calls {@link #doProjectileUpdate()}.
     * </p>
     */
    @Override
    public final void update() {
        if (inbounds()) {
            updatePosition();
            HittableEntity hitEntity = collisionCheck();
            if (hitEntity != null) {
                doHit(hitEntity);
                entities.remove(this);
            }
            doProjectileUpdate();
            return;
        }
        entities.remove(this);
    }

    /**
     * Checks if a collision occurred.
     * @return the hit {@link HittableEntity entity}.
     */
    private HittableEntity collisionCheck() {
        ArrayList<HittableEntity> hittableEntities = entities.stream()
                .filter(entity -> entity instanceof HittableEntity)
                .map(entity -> (HittableEntity) entity)
                .collect(Collectors.toCollection(ArrayList::new));

        ArrayList<HittableEntity> collidingEntities = hittableEntities.stream()
                .filter(hittableEntity -> hittableEntity.isFriendly() != owner.isFriendly())
                .filter(hittableEntity -> hittableEntity.coordinate.distance(coordinate) <= hittableEntity.size)
                .collect(Collectors.toCollection(ArrayList::new));

        if (collidingEntities.size() == 0) {
            return null;
        }
        else {
            return collidingEntities.get(0);
        }
    }

    /**
     * Updates the position according to {@link #direction}.
     */
    private void updatePosition() {
        coordinate.x += direction.getX();
        coordinate.y += direction.getY();
    }

    /**
     * Check if the projectile is withing the field bounds.
     * @return true if inbounds.
     */
    private boolean inbounds() {
        if (this.coordinate.y > Constants.FIELD_Y_UPPER || this.coordinate.y < Constants.FIELD_Y_LOWER) return false;
        if (this.coordinate.x > Constants.FIELD_X_UPPER || this.coordinate.x < Constants.FIELD_X_LOWER) return false;
        return true;
    }

    /**
     * Called when a collision occurs.
     * This method should call the hitEntities {@link HittableEntity#getHit(int)} method.
     * Is implemented by the subclasses.
     * @param hitEntity the entity we are colliding with.
     */
    protected abstract void doHit(HittableEntity hitEntity);

    /**
     * A hook that is called every update cycle after {@link #update()} for if a subclass needs to implement more functionality.
     */
    protected abstract void doProjectileUpdate();
}
