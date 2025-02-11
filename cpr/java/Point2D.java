
package es.upv.etsinf.geometry;

public class Point2D
    implements Comparable
{
    final public static double  EPSILON = 1.0e-5;

    final private double  x, y;

    public Point2D( double x, double y )
    {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    public double distanceFrom( Point2D other )
    {
        double dx = this.x - other.x;
        double dy = this.y - other.y;

        return Math.sqrt( dx*dx + dy*dy );
    }

    public boolean isInferior( Point2D other )
    {
        return this.y < other.y 
            || ( this.y == other.y  &&  this.x < other.x );
            // || ( Math.abs( this.y - other.y ) <= EPSILON  && this.x < other.x );
    }

    public int compareTo( Object o )
    {
        if ( o instanceof Point2D ) {

            Point2D other = (Point2D)o;
            if ( this.x < other.x ) return -1;
            if ( this.x > other.x ) return  1;
            if ( this.y < other.y ) return -1;
            if ( this.y > other.y ) return  1;
            return 0;
        }

        return -1;
    }

    @Override
    public boolean equals( Object o )
    {
        if ( o instanceof Point2D ) {
            Point2D other = (Point2D)o;

            return ( Math.abs( this.x - other.x ) <= EPSILON
                  && Math.abs( this.y - other.y ) <= EPSILON );

        } else {
            return false;
        }
    }

    @Override
    public Object clone()
    {
        Point2D p = new Point2D( this.x, this.y );

        return p;
    }

    @Override
    public String toString()
    {
        return String.format( java.util.Locale.US, "(%.3f,%.3f)", x, y );
    }
}
