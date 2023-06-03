package be.uantwerpen.fti.gea.vincent.verbergt.SpaceInvaders.utilities;

/**
 * A mathematical vector class;
 * The bases are stored as doubles;
 */
public class Vector2D {
    /**
     * The first base of the vector.
     */
    public double x;

    /**
     * The second base of the vector.
     */
    public double y;

    /**
     * Constructor using floats
     * @param x {@link #x}
     * @param y {@link #y}
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor using int.
     * @param x {@link #x}
     * @param y {@link #y}
     */
    public Vector2D(int x, int y) {
        this(x, (double) y);
    }

    /**
     * Calculate the direction of the vector.
     * @return the angle counterclockwise from the x-base in radians. [0, 2*pi[
     */
    public double getDirection() {
        double angle = Math.atan2(y, x);
        if (x == 0) {
            if (y > 0) {
                angle = Math.PI/2;
            } else if (y < 0) {
                angle= -Math.PI/2;
            } else {
                angle = 0;
            }
        }
        else if (y == 0) {
           if (x >= 0) {
               angle = 0;
           } else {
               angle = Math.PI;
           }
        }
        if (angle < 0) {
            angle += 2*Math.PI;
        }
        return angle;
    }

    /**
     * Get the size of the vector.
     * @return sqrt(x*x, y*y)
     */
    public double getSize() {
       return Math.sqrt(x*x + y*y);
    }

    /**
     * Get size of the vector without using square root.
     * @return x*x + y*y
     */
    public double getSizeSquared() {
        return x*x + y*y;
    }

    /**
     * Calculate angle from that to this.
     * @param that The vector we are measuring from.
     * @return the angle in radians.
     */
    public double angleFrom(Vector2D that) {
        return  this.getDirection() - that.getDirection();
    }

    /**
     * Rotates this counterclockwise by the given angle.
     * @param angle The angle in radians.
     */
    public void rotate(double angle) {
        double newx = x*Math.cos(angle) - y*Math.sin(angle);
        double newy = x*Math.sin(angle) + y*Math.cos(angle);
        x = newx;
        y = newy;
    }

    /**
     * Get the rounded x base.
     * @return {@link #x}
     */
    public int getX() {
        return (int) Math.round(x);
    }

    /**
     * Calculates equality based on {@link #x} and {@link #x}.
     * @param o the comparing object.
     * @return true if this == o
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector2D vector2D = (Vector2D) o;

        if (Double.compare(vector2D.x, x) != 0) return false;
        return Double.compare(vector2D.y, y) == 0;
    }

    /**
     * Calculates hashCode based on {@link #x} and {@link #x}.
     * @return an unique Integer.
     */
    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    /**
     * Get the rounded y base.
     * @return {@link #y}
     */
    public int getY() {
        return (int) Math.round(y);
    }

}
