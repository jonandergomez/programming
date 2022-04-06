import java.util.*;

/**
  Class to represent points in the 2-dimensional space.

  @author  jon@upv.es
  @version 2021-04
 */

public class Point2D
{
    /**
       Static constant (final variable) indicating the dimensionality
       of the objects of this class.
    */
    public final static int DIMENSION = 2;

    /* attributes of this class (instance variables) */
    /** Coordinate for X-axis or abscissas. */
    private double x;
    /** Coordinate for Y-axis or ordinates. */
    private double y;

    /**
        Default constructor.
        Creates an object corresponding to origin of coordinates in the plane.
    */
    public Point2D()
    {
    }

    /**
        Generic constructor.
        Creates an object for representing the point in the plane specified
        by the parameters.

        @param x Value for the abscissas.
        @param y Value for the ordinates.
    */
    public Point2D(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    /** Gets the value of the attribute x.
        @return The value of x.
    */
    public double getX() { return x; }

    /** Gets the value of the attribute y.
        @return The value of y.
    */
    public double getY() { return y; }

    /** Returns an String as representation of the object.
        @return The representation of this object.
    */
    public String toString()
    {
        return String.format("(%.3f, %.3f)", x, y);
    }

    public double r()
    {
        return Math.sqrt(x * x + y * y);
    }
    public double atan()
    {
        return Math.atan2(y, x);
    }
}
