package be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.enemies.bosses;

import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.AbstractFactory;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.Player;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.enemies.ShootingEnemy;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities.Vector2D;

import java.awt.*;

/**
 * The second boss.
 * Has double the health of {@link Exterminator}.
 * Shoot bullets twice as often.
 * Shoots 2 {@link be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.projectiles.Missile Misiles} objects.
 */
public abstract class Nabula extends ShootingEnemy {

    /**
     * The average time before a rocket attack is shot.
     */
    private final double averageMissileTime = 10;

    /**
     * The constructor for a Nabula boss.
     * @param location the starting location of the boss.
     * @param abstractFactory {@link #abstractFactory}.
     */
    public Nabula(Point location, AbstractFactory abstractFactory) {
        super(location, 20, 100, abstractFactory, 2.5);
    }

    /**
     * Add 20 points to the score when the boss dies.
     */
    @Override
    protected final void doEnemyDeath() {
        gameState.addScore(20);
    }

    @Override
    public final void doShootingEnemyUpdate() {
        if (shouldShoot(averageMissileTime)) {
            double speed = 12;
            Player player = (Player) abstractFactory.getEntities().stream().filter(entity -> entity instanceof Player).findFirst().orElse(null);

            Point torpedo1Location = new Point((int) (coordinate.x - size/2), (int) (coordinate.y - size/2));
            Vector2D torpedo1InitDirection = new Vector2D(-1, -1);
            torpedo1InitDirection.normalize();
            torpedo1InitDirection.scale(speed);

            Point torpedo2Location = new Point((int) (coordinate.x + size/2), (int) (coordinate.y - size/2));
            Vector2D torpedo2InitDirection = new Vector2D(+1, -1);
            torpedo2InitDirection.normalize();
            torpedo2InitDirection.scale(speed);

            abstractFactory.getEntities().add(
              abstractFactory.torpedoCreator(torpedo1Location, torpedo1InitDirection, this, player)
            );
            abstractFactory.getEntities().add(
                    abstractFactory.torpedoCreator(torpedo2Location, torpedo2InitDirection, this, player)
            );
        }
    }
}
