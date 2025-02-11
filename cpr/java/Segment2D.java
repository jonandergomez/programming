
package es.upv.etsinf.geometry;

public class Segment2D
{
    private Point2D p1, p2;
    private Line2D  line;

    public Segment2D( Point2D p1, Point2D p2 )
    {
        this.p1 = p1;
        this.p2 = p2;

        this.line = new Line2D( p1, p2 );
    }

    public Line2D getLine() { return line; }

    public double length() { return p1.distanceFrom( p2 ); }

    public boolean isVertical()
    {
        return Math.abs( p1.getX() - p2.getX() ) <= Point2D.EPSILON;
    }
    public boolean isHorizontal()
    {
        return Math.abs( p1.getY() - p2.getY() ) <= Point2D.EPSILON;
    }

    public boolean isParallelTo( Segment2D other )
    {
        return this.line.isParallelTo( other.line );
    }

    public boolean inSameLine( Segment2D other )
    {
        return this.line.isSameLineAs( other.line );
    }

    public boolean isInsideRectangle( Point2D p )
    {
        return Math.min( p1.getX(), p2.getX() ) <= p.getX() && p.getX() <= Math.max( p1.getX(), p2.getX() )
            && Math.min( p1.getY(), p2.getY() ) <= p.getY() && p.getY() <= Math.max( p1.getY(), p2.getY() );
    }

    public Point2D intersection( Segment2D other )
    {
        if ( this.line.isSameLineAs( other.line ) ) {

            if (  this.isInsideRectangle( other.p1 ) ) return other.p1;
            if (  this.isInsideRectangle( other.p2 ) ) return other.p2;
            if ( other.isInsideRectangle(  this.p1 ) ) return  this.p1;
            if ( other.isInsideRectangle(  this.p2 ) ) return  this.p2;

            return null;
        }

        if ( this.line.isParallelTo( other.line ) ) return null;

        Point2D p = this.line.intersection( other.line );

        return ( p != null && this.isInsideRectangle( p ) && other.isInsideRectangle( p ) ) ? p : null;
    }

    /**
     * @return Ángulo más pequeño entre dos rectas medido en radianes. Puede ser negativo.
     */
    public double angle( Segment2D other )
    {
        return this.line.angle( other.line );
    }

    public Point2D closestPoint( Point2D p )
    {
        Point2D q = this.line.closestPoint( p );

        if ( q != null && isInsideRectangle( q ) ) {
            return q;
        } else if ( p1.distanceFrom( p ) < p2.distanceFrom( p ) ) {
            return p1;
        } else {
            return p2;
        }
    }


    // For the problem stated and solved in the book "Programming Challenges"
    public double distanceOfSuperman( Circle circles[] )
    {
        double dist = this.length();

        for( Circle c : circles ) dist += c.incrementByChord( this );

        return dist;
    }
}
