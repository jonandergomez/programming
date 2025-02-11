
package es.upv.etsinf.geometry;

/**
 * A line is defined by three parameters: <code>Ax+By+C=0</code>
 */
public class Line2D
{
    /** Coefficient for <em>x</em> */
    private double A;
    /** Coefficient for <em>y</em> */
    private double B;
    /** Constant term */
    private double C;

    public Line2D( double A, double B, double C )
    {
        this.A = A;
        this.B = B;
        this.C = C;
    }
    public Line2D( Point2D p1, Point2D p2 )
    {
        if ( p1.getX() == p2.getX() ) {
            // Vertical line
            A = 1.0;
            B = 0.0;
            C = -p1.getX();
        } else {
            A = -( p1.getY() - p2.getY() ) / ( p1.getX() - p2.getX() );
            B = 1.0;
            /*
                Alternative:
                A = - p1.getY() + p2.getY();
                B =   p1.getX() - p2.getX();
            */
            C = -( A * p1.getX() ) - ( B * p1.getY() );
        }
    }

    public Line2D( Point2D p, double m )
    {
        A = -m;
        B = 1.0;
        C = -( A * p.getX() ) - ( B * p.getY() );
    }

    public boolean isVertical()
    {
        return Math.abs(B) <= Point2D.EPSILON;
    }
    public boolean isHorizontal()
    {
        return Math.abs(A) <= Point2D.EPSILON;
    }

    public double getA() { return A; }
    public double getB() { return B; }
    public double getC() { return C; }

    public double getSlope() { return isVertical() ? Double.POSITIVE_INFINITY : -A/B; }

    public double f( double x )
    {
        return ( isVertical() ) ? 0.0 : ( -A*x - C ) / B;
    }

    public boolean isParallelTo( Line2D other )
    {
        return ( Math.abs( this.A - other.A ) <= Point2D.EPSILON
              && Math.abs( this.B - other.B ) <= Point2D.EPSILON );
    }

    public boolean isSameLineAs( Line2D other )
    {
        return isParallelTo( other ) && Math.abs( this.C - other.C ) <= Point2D.EPSILON;
    }

    public Point2D intersection( Line2D other )
    {
        if ( this.isParallelTo( other ) ) return null;

        double x = (other.B * this.C - this.B * other.C) / (other.A * this.B - this.A * other.B);
        double y = this.isVertical() ? other.f(x) : this.f(x);

        return new Point2D( x, y );
    }

    /**
     * @return Ángulo más pequeño entre dos rectas medido en radianes. Puede ser negativo.
     */
    public double angle( Line2D other )
    {
        return Math.atan2( this.A*other.B - other.A*this.B,
                           this.A*other.A + this.B*other.B );
    }

    public Point2D closestPoint( Point2D p )
    {
        if ( this.isVertical() ) {
            return new Point2D( -C, p.getY() );
        } else if ( this.isHorizontal() ) {
            return new Point2D( p.getX(), -C );
        } else {
            return this.intersection( new Line2D( p, this.B/this.A ) );
        }
    }
}
