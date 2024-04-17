import java.util.*;

/**
  Class to represent points in the 3-dimensional space extending
  the Point2D class.

  @author  jon@upv.es
  @version 2021-04
 */

public class Point3D extends Point2D
{
    /**
       Static constant (final variable) indicating the dimensionality
       of the objects of this class.
    */
    public final static int DIMENSION = 3;

    /* attributes of this class (instance variables) */
    /** Coordinate for Z-axis or abscissas. */
    private double z;

    /**
        Default constructor.
        Creates an object corresponding to origin of coordinates in the plane.
    */
    public Point3D()
    {
        super();
    }

    /**
        Generic constructor.
        Creates an object for representing the point in the plane specified
        by the parameters.

        @param x Value for the x axis.
        @param y Value for the y axis.
        @param z Value for the z axis.
    */
    public Point3D(double x, double y, double z)
    {
        super(x, y);
        this.z = z;
    }

    /** Gets the value of the attribute z.
        @return The value of z.
    */
    public double getZ() { return this.z; }

    /** Returns an String as representation of the object.
        @return The representation of this object.
    */
    @Override
    public String toString()
    {
        return String.format("(%.3f, %.3f, %.3f)",
                                this.getX(),
                                this.getY(),
                                z);
    }

    @Override
    public double r()
    {
        return Math.sqrt(super.getX() * super.getX()
                       + super.getY() * super.getY()
                       + z * z);
    }
    /*
    This method must be used from the parent class with caution.
    public double atan()
    {
        return Math.atan2(y, x);
    }
    */
}
