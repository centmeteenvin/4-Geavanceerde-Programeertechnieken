package SpaceInvaders.utilities;

import SpaceInvaders.entities.Player;

/**
 * The abstract InputController.
 * <p>
 * The Input controller is passed to {@link Player}.<br>
 * The Player fetches it's input from the InputController implementation.<br>
 * </p>
 */
public abstract class InputController {
    /**
     * Indicates the current direction.
     */
    protected Input direction = Input.NONE;

    /**
     * Indicates if the player needs to shoot.
     * <p>
     * When the Player objects fetches this via {@link #getShooting()},
     * the state is always reset to {@link Input#NONE}.
     * </p>
     */
    protected Input shooting = Input.NONE;



    /**
     * Fetches the current shooting input.
     * <p>
     *  This method is called every frame by {@link Player#doHittableEntityUpdate()}.<br>
     *  Fetching the shooting command always reset its state to {@link Input#NONE}.<br>
     * </p>
     *
     * @return An Input object indicating if the player needs to Shoot.
     */
    public Input getShooting() {
        if (shooting == Input.SHOOT) {
            shooting = Input.NONE;
            return Input.SHOOT;
        }
        return Input.NONE;
    }

    /**
     * Getter for direction.
     * @return {@link #direction}
     */
    public Input getDirection() {
        return direction;
    }
}
