
package es.upv.etsinf.geometry;

public class Triangle
{
    private Point2D A, B, C;
    private Segment2D a, b, c;
    private double  alpha, beta, gamma;

    public Triangle( Point2D p1, Point2D p2, Point2D p3 )
    {
        A = p1;
        B = p2;
        C = p3;

        // Nos aseguramos que A es el punto más a la izquierda y más bajo de los tres.
        if ( A.compareTo( B ) > 0 ) { Point2D temp = A; A = B; B = temp; }
        if ( B.compareTo( C ) > 0 ) { Point2D temp = B; B = C; C = temp; }
        if ( A.compareTo( B ) > 0 ) { Point2D temp = A; A = B; B = temp; }

        if ( ! counterClockWise( A, B, C ) ) { Point2D temp = B; B = C; C = temp; }

        a = new Segment2D( B, C );
        b = new Segment2D( C, A );
        c = new Segment2D( A, B );

        alpha = Math.abs( b.angle(c) );
         beta = Math.abs( c.angle(a) );
        gamma = Math.PI - alpha - beta;
    }

    public Segment2D getSegmentA() { return a; }
    public Segment2D getSegmentB() { return b; }
    public Segment2D getSegmentC() { return c; }
    public double getAngleA() { return alpha; }
    public double getAngleB() { return beta; }
    public double getAngleC() { return gamma; }
    public Point2D getA() { return A; }
    public Point2D getB() { return B; }
    public Point2D getC() { return C; }

    public double area()
    {
        return Math.abs( signedArea( A, B, C ) );
    }
    public boolean pointInside( Point2D p )
    {
        return ! ( clockWise( A, B, p ) || clockWise( B, C, p ) || clockWise( C, A, p ) );
    }

    public static boolean pointInside( Point2D A, Point2D B, Point2D C, Point2D p )
    {
        return ! ( clockWise( A, B, p ) || clockWise( B, C, p ) || clockWise( C, A, p ) );
    }

    private static double signedArea( Point2D p1, Point2D p2, Point2D p3 )
    {
    /*
                 | ax ay 1 |
        2*A(T) = | bx by 1 | = (ax*by - ay*bx) + (-ax*cy + ay*cx) + (bx*cy - cx*by)
                 | cx cy 1 |
    */
        return ( ( p1.getX()*p2.getY() - p1.getY()*p2.getX()
                 - p1.getX()*p3.getY() + p1.getY()*p3.getX()  // Esto está bien 
                 + p2.getX()*p3.getY() - p2.getY()*p3.getX() ) / 2.0 );
    }

    public static boolean counterClockWise( Point2D p1, Point2D p2, Point2D p3 )
    {
        return ( signedArea( p1, p2, p3 ) > Point2D.EPSILON );
    }
    public static boolean clockWise( Point2D p1, Point2D p2, Point2D p3 )
    {
        return ( signedArea( p1, p2, p3 ) < -Point2D.EPSILON );
    }
    public static boolean collinear( Point2D p1, Point2D p2, Point2D p3 )
    {
        return ( Math.abs( signedArea( p1, p2, p3 ) ) <= Point2D.EPSILON );
    }
}
