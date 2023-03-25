package J2DSpaceInvaders;

/**
 * Basic class to handle Frame Sizes
 */
public class Size {

    /**
     * Width parameter of Size.
     */
    public int width;

    /**
     * Height parameter of Size.
     */
    public int height;

    /**
     * Preferred Constructor for Size.
     * @param width {@link #width}
     * @param height {@link #height}
     */
    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Default Constructor for Size.
     * Initializes {@link #width} and {@link #height} with 0.
     */
    public Size() {
        this(0,0);
    }
}
