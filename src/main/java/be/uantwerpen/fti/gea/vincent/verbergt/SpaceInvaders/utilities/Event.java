package be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities;

import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.AbstractFactory;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.Game;
import be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.entities.Entity;

/**
 * The Event that are passed to {@link AbstractFactory} from {@link Entity}.
 * The Game doesn't react to these events.
 * They are meant as a way for visual libraries to react visually to an event happening.
 */
public class Event {

    /**
     * The possible types of events.
     */
    public enum Type {
        /**
         * Created when an entity dies.
         */
        DEATH,

        /**
         * Created when an entity shoots.
         */
        SHOOT,

        /**
         * Created when a level is cleared.
         */
        LEVEL_CLEARED,

        /**
         * Created when a game is won.
         */
        VICTORY,

        /**
         * Created when the player dies.
         */
        GAME_OVER,
    }

    /**
     * The {@link Type event type}.
     */
    private final Type type;

    /**
     * The object that created the event.
     * Will be an instance of either {@link Game} or {@link Entity}.
     */
    private final Object source;

    /**
     * The constructor of {@link Event}.
     * @param type The {@link Type} of the event.
     * @param source The object that created the event. Should always be "this".
     */
    public Event(Type type, Object source) {
        this.type = type;
        this.source = source;
    }


    /**
     * Get the eventType.
     * @return {@link #type}.
     */
    public Type getType() {
        return type;
    }

    /**
     * Get the source of the event.
     * @return {@link #source}
     */
    public Object getSource() {
        return source;
    }
}
