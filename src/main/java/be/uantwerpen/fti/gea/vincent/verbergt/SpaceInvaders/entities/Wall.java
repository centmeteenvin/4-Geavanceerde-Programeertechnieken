package be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities;

import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.AbstractFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

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

    /**
     * Disabled because no checks need to happen for now.
     */
    @Override
    public final void preUpdateCheck() {
        //Empty
    }

    /**
     * When the wall dies, it explodes dealing 2 damage to all surrounding entities.
     */
    @Override
    protected final void death() {
        ArrayList<HittableEntity> entities = abstractFactory.getEntities().stream().filter(entity -> entity instanceof HittableEntity).map(entity -> (HittableEntity) entity).collect(Collectors.toCollection(ArrayList::new));
        entities.stream().filter(entity -> entity.coordinate.distance(this.coordinate) <= 2*size).forEach(entity -> entity.getHit(2));
    }
}
