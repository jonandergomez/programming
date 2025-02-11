
package es.upv.etsinf.geometry;

public class Point3D extends Point2D
{
    private double  z;

    public Point3D( double x, double y, double z )
    {
        super( x, y );
        this.z = z;
    }

    public double getZ() { return z; }

    public double distanceFrom( Point3D other )
    {
        double dx = this.getX() - other.getX();
        double dy = this.getY() - other.getY();
        double dz = this.z - other.z;

        return Math.sqrt( dx*dx + dy*dy + dz*dz );
    }

    @Override
    public boolean equals( Object o )
    {
        if ( o instanceof Point3D ) {
            Point3D other = (Point3D)o;

            return ( super.equals( other )
                  && Math.abs( this.z - other.z ) <= EPSILON );

        } else {
            return false;
        }
    }

    @Override
    public Object clone()
    {
        Point3D p = new Point3D( this.getX(), this.getY(), this.z );

        return p;
    }

    @Override
    public String toString()
    {
        return String.format( java.util.Locale.US, "(%.3f,%.3f,%.3f)", getX(), getY(), z );
    }
}
