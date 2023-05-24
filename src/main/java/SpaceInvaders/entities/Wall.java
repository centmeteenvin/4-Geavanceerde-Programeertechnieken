package SpaceInvaders.entities;

import SpaceInvaders.AbstractFactory;

import java.awt.*;

/**
 * The abstract class of a wall object.
 * They are friendly and cannot be shot by the player.
 */
public abstract class Wall extends HittableEntity {

    /**
     * Default Wall Constructor
     *
     * @param location        {@link Entity#coordinate}
     * @param health          {@link #health}
     * @param size            {@link #size}
     * @param abstractFactory {@link #abstractFactory}
     */
    public Wall(Point location, int health, double size, AbstractFactory abstractFactory) {
        super(location, health, size, abstractFactory);
        isFriendly = true;
    }

    /**
     * Use this to implement the doHittableEntityUpdate methods in HittableEntity Objects.
     */
    @Override
    public final void doHittableEntityUpdate() {
        //Empty
    }
}
