package be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities;

import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.AbstractFactory;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.Game;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.projectiles.Bullet;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.Event;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.GameState;

import java.awt.*;

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
    public double size;

    /**
     * Reference to game's {@link Game#entities entities}.
     * HittableEntities need this list because they can interact with other entities.
     */
    protected AbstractFactory abstractFactory;

    /**
     * Determine if an entity is friendly or not.
     * Used to determine if a bullet should hit or not.
     */
    protected boolean isFriendly;

    /**
     * The state of the game.
     * Used to alter the score.
     */
    protected GameState gameState;

    /**
     * Default HittableEntity Constructor
     *
     * @param location        {@link Entity#coordinate}
     * @param health          {@link #health}
     * @param size            {@link #size}
     * @param abstractFactory {@link #abstractFactory}
     */
    public HittableEntity(Point location, int health, double size, AbstractFactory abstractFactory) {
        super(location);
        this.maxHealth = health;
        this.health = health;
        this.size = size;
        this.abstractFactory = abstractFactory;
        this.gameState = abstractFactory.getGameState();
    }

    /**
     * The method that is activated when a hit happens.<br>
     * Should be called from {@link HittableEntity#update()}.
     * If the entity dies, an {@link Event.Type#DEATH} is created.
     * @param damage The amount of damage that is dealt.
     */
    public final void getHit(int damage) {
        health -= damage;
        abstractFactory.addEvent(new Event(Event.Type.GOT_HIT, this));
        if (health <= 0) {
            abstractFactory.getEntities().remove(this);
            abstractFactory.addEvent(new Event(Event.Type.DEATH, this));
            death();
        }
    }

    /**
     * Called after the updates are gathered.
     * <p>
     * This code implements collisionDetection.
     * </p>
     */
    @Override
    public final void update() {
        //Health check otherwise call the subclasses' update function.
        if (health > 0) {
            doHittableEntityUpdate();
        }
    }

    /**
     * Use this to implement the doHittableEntityUpdate methods in HittableEntity Objects.
     */
    public abstract void doHittableEntityUpdate();

    /**
     * Hook that is called when the hittable entity dies.
     * This is used to differentiate death behaviour in subclasses.
     */
    protected abstract void death();

    /**
     * Check if owner is friendly.
     * @return {@link #isFriendly}
     */
    public final boolean isFriendly() {
        return isFriendly;
    }
}
