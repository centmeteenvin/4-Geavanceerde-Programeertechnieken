package be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities;

import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.AbstractFactory;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.Constants;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * The powerUp class
 */
public abstract class PowerUp extends HittableEntity {

    /**
     * The type of PowerUp it is.
     */
    public enum Type {
        /**
         * Gives an extra life to the player.
         */
        HEATH_PACKET,

        /**
         * Doubles the damage for 10 seconds of the player.
         */
        DOUBLE_DAMAGE,

        /**
         * Deals 2 damage to all nearby enemies excluding the player.
         */
        EXPLOSION,

        /**
         * Shrink the player to 75 the size for 10 seconds.
         */
        SHRINK,
    }

    /**
     * The type of powerUp the object is.
     */
    public final PowerUp.Type type;

    /**
     * The current player object of the game.
     */
    private Player player;

    /**
     * The speed of the bonus.
     */
    private final int speed = 10;

    /**
     * The constructor of a bonus.
     *
     * @param location starting location.
     * @param abstractFactory {@link #abstractFactory}.
     * @param type The powerUp type.
     */
    public PowerUp(Point location, AbstractFactory abstractFactory, PowerUp.Type type) {
        super(location, 1, 50 , abstractFactory);
        this.type = type;
        this.isFriendly = false;
    }


    /**
     * Move the powerUp down.
     */
    @Override
    public final void doHittableEntityUpdate() {
        if (player == null) {
            player = fetchPlayer();
            return;
        }
        if (inbounds()) {
            coordinate.y -= speed;
            return;
        }
        abstractFactory.getEntities().remove(this);
    }

    @Override
    protected final void death() {
        switch (type) {
            case HEATH_PACKET -> {
                player.addHealth(1);
            }
            case DOUBLE_DAMAGE -> {
                player.doubleDamage();
            }
            case EXPLOSION -> {
                int blastradius = (int) (3*size);
                ArrayList<Entity> entities = abstractFactory.getEntities();
                ArrayList<HittableEntity> hostileEntities = entities.stream()
                        .filter(entity -> entity instanceof HittableEntity)
                        .map(entity -> (HittableEntity) entity)
                        .filter(hittableEntity -> !hittableEntity.isFriendly())
                        .filter(hittableEntity -> hittableEntity.coordinate.distance(coordinate) <= (blastradius + hittableEntity.size/2))
                        .collect(Collectors.toCollection(ArrayList::new));
                for (HittableEntity hostileEntity: hostileEntities) {
                    hostileEntity.getHit(10);
                }
            }
            case SHRINK -> {
                player.shrink();
            }
            default -> {
                System.out.println("Not yet implemented");
            }
        }
        abstractFactory.getEntities().remove(this);
    }

    /**
     * Redundant for this class.
     */
    @Override
    public final void preUpdateCheck() {
        //empty
    }

    /**
     * Check if the projectile is withing the field bounds.
     *
     * @return true if inbounds.
     */
    private boolean inbounds() {
        if (this.coordinate.y > Constants.FIELD_Y_UPPER || this.coordinate.y < Constants.FIELD_Y_LOWER) return false;
        return this.coordinate.x <= Constants.FIELD_X_UPPER && this.coordinate.x >= Constants.FIELD_X_LOWER;
    }

    /**
     * Get the player from the entity list.
     *
     * @return null if the players wasn't found.
     */
    private Player fetchPlayer() {
        ArrayList<Entity> entities = abstractFactory.getEntities();
        return entities.stream()
                .filter(entity -> entity instanceof Player)
                .map(entity -> (Player) entity)
                .findFirst()
                .orElse(null);
    }
}
