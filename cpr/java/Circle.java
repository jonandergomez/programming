
package es.upv.etsinf.geometry;

public class Circle
{
    private Point2D centre;
    private double  radius;

    public Circle( Point2D c, double r )
    {
        centre = c;
        radius = r;
    }

    public double getRadius() { return radius; }
    public double getDiameter() { return 2 * radius; }
    public double getCircumference() { return 2 * Math.PI * radius; }
    public double getArea() { return Math.PI * radius * radius; }


    public boolean separated( Circle other )
    {
        return this.centre.distanceFrom( other.centre ) > this.radius+other.radius;
    }
    public boolean intersect( Circle other )
    {
        return this.centre.distanceFrom( other.centre ) < this.radius+other.radius
          &&  !this.contained( other );
    }
    public boolean contained( Circle other )
    {
        return this.centre.distanceFrom( other.centre )+Math.min( this.radius, other.radius ) <= Math.max( this.radius, other.radius );
    }

    public Line2D [] getTangents( Point2D p )
    {
        // Distance from the centre of this circle to the given point.
        double d = centre.distanceFrom( p );

        // If the point is inside the circle no tangent lines can exists
        if ( d < radius-Point2D.EPSILON ) return null;

        // If the point is just in the circle, then there is one tangent line
        if ( Math.abs( radius - d ) <= Point2D.EPSILON ) {
            Line2D perpendicular = new Line2D( this.centre, p );
            Line2D tangent[] = new Line2D [ 1 ];
            tangent[0] = new Line2D( p, -1.0/perpendicular.getSlope() );

            return tangent;
        }

        // Radius of the imaginary circle centered at 'p'.
        double x = Math.sqrt( d*d - radius*radius );

        // We obtain the intersection points between this circle and the imaginary one.
        Point2D [] intersectionPoints = getIntersectionPoints( new Circle( p, x ) );

        // We obtain the tangent lines from the intersection points.
        Line2D [] tangentLines = new Line2D [2];
        tangentLines[0] = new Line2D( p, intersectionPoints[0] );
        tangentLines[1] = new Line2D( p, intersectionPoints[1] );

        return tangentLines;
    }

    public Point2D [] getIntersectionPoints( Circle other )
    {
        // Consultar: http://en.wikipedia.org/wiki/Intersection_(Euclidean_geometry)

        if ( this.intersect( other ) && !this.contained( other ) ) {
            
            double r1 = this.radius;
            double x1 = this.centre.getX();
            double y1 = this.centre.getY();
            double r2 = other.radius;
            double x2 = other.centre.getX();
            double y2 = other.centre.getY();
            
            Line2D line = new Line2D( 2*(x2-x1),
                                      2*(y2-y1),
                                      -(r1*r1 - x1*x1 - y1*y1 - r2*r2 + x2*x2 + y2*y2) );

            return this.getIntersectionPoints( line );
        }
        return null;
    }

    public Point2D [] getIntersectionPoints( Line2D line )
    {
        // Consultar: http://en.wikipedia.org/wiki/Intersection_(Euclidean_geometry)

        // The line is defined as Ax+By+C=0, but for this formula we need ax + by = c
        double a =  line.getA();
        double b =  line.getB();
        double c = -line.getC();

        double den  = a*a + b*b;
        double sqrt = Math.sqrt( this.radius*this.radius * den - c*c );

        Point2D [] points = new Point2D [2];

        points[0] = new Point2D( (a*c + sqrt) / den, (b*c - sqrt) / den );
        points[1] = new Point2D( (a*c - sqrt) / den, (b*c + sqrt) / den ); 

        return points;
    }

    // For the problem stated and solved in the book "Programming Challenges"
    public double incrementByChord( Segment2D segment )
    {
        Point2D closestPoint = segment.getLine().closestPoint( this.centre );
        double d = this.centre.distanceFrom( closestPoint );

        if ( d >= 0 && d < this.radius  &&  segment.isInsideRectangle( closestPoint )  ) {
            double angle = Math.acos( d / this.radius );

            double segmentLength = 2.0 * Math.sqrt( radius*radius - d*d );
            double chordLength = 2.0 * angle * this.radius;

            return chordLength - segmentLength;
        } else {
            return 0.0;
        }
    }
}
