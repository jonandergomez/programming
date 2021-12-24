import java.util.*;

/**
  This class allow us to work with with points in the 2-dimensional space.

  @author IIP teachers
  @version 2020-10
 */

/*
   Command line to generate the documentation:

   javadoc -d docs -private -author -version basic/Point2D.java
*/
public class Point2D
{
    /**
       Static constant (final variable) indicating the dimensionality of the
       objects of this class.
    */
    public final static int DIMENSION = 2;

    /**
       Static variable for counting how many objects of this class
       have been created during the execution of this program.
    */
    private static int objectCounter = 0;

    /* attributes of this class (instance variables)
    */
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
        ++objectCounter;
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
        ++objectCounter;
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

    /*
        The operator new performs the following three steps:

        1) Allocates the necessary amount of memory according to the
           data structure of the class given as parameter to the operator new.

        2) All the bytes of the allocated memory are set to zero,
           i.e. are reset.

        3) Invokes the constructor.
    */

    public static void main(String [] args)
    {
        Point2D p1 = new Point2D();    // invokes the default constructor
        Point2D p2 = new Point2D(4, 5); // invokes the generic constructor
        Point2D p3 = new Point2D(8, 1); // invokes the generic constructor
        Point2D p4 = p1;

        System.out.println();
        System.out.println("In this example program we are going to play with "
                        + "points the " + Point2D.DIMENSION + "-dimensional space.");
        System.out.println();

        System.out.println(p1.toString() + " " + p1.r() + " " + p1.atan());
        System.out.println(p2.toString() + " " + p2.r() + " " + p2.atan());
        System.out.println(p3.toString() + " " + p3.r() + " " + p3.atan());
        System.out.println(p4.toString() + " " + p4.r() + " " + p4.atan());
        /* prints the same of first println() because p4
           is a reference variable whose contents is the
           memory address where the object pointed by p1
           is stored, in other words, p1 and p4 contain
           the same memory address so both reference to the
           same object. */
        System.out.println();
        System.out.println("A total of " + Point2D.objectCounter
                        + " objects of the class Point2D were created. Bye!");
    }
}
