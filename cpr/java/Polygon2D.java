
package es.upv.etsinf.geometry;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;

public class Polygon2D
{
    private Point2D [] points;
    private int        numberOfPoints;

    public Polygon2D()
    {
        numberOfPoints = 0;
        points = new Point2D [ 100 ];
    }
    public Polygon2D( Point2D p1, Point2D p2, Point2D p3 )
    {
        this();

        add( p1 );
        add( p2 );
        add( p3 );
    }
    public Polygon2D( Point2D [] points, int n )
    {
        this();

        for( int i=0; i < n; i++ ) this.add( points[i] );
    }
    public Polygon2D( Point2D [] points )
    {
        this( points, points.length );
    }

    public int length() { return numberOfPoints; }

    public void add( Point2D p )
    {
        if ( numberOfPoints == points.length ) {
            Point2D temp [] = new Point2D [ points.length + 100 ];
            for( int i=0; i < numberOfPoints; i++ ) temp[i] = points[i];
            points = temp;
        }

        points[ numberOfPoints++ ] = p;
    }

    public double area()
    {
        double area=0.0;

        for( int i=0; i < numberOfPoints; i++ ) {
            int j = (i+1)%numberOfPoints;

            area += points[i].getX()*points[j].getY()
                  - points[i].getY()*points[j].getX();
        }

        return area / 2.0;
    }

    // Methods required by the Convex Hull algorithm
    private void removeLast()
    {
        if ( numberOfPoints > 0 ) points[ --numberOfPoints ] = null;
    }
    private Point2D getPoint( int i )
    {
        if ( i >= 0 )
            return points[ i ];
        else
            return points[ numberOfPoints-i ];
    }

    public Polygon2D convexHull()
    {
        return Polygon2D.convexHull( points, numberOfPoints );
    }
    public static Polygon2D convexHull( Point2D [] points, int n )
    {
        if ( n <= 3 ) return new Polygon2D( points, n );

        points = sortAndRemoveDuplicates( points, n );
        n = points.length;

        sortBySmallerAngle( points, n );

        Polygon2D ch = new Polygon2D();
        ch.add( points[0] );
        ch.add( points[1] );

        int i = 2;
        while( i < n ) {

            if ( false == Triangle.counterClockWise( ch.getPoint(-2),
                                                     ch.getPoint(-1),
                                                     points[i] ) ) {
                ch.removeLast();
            } else {
                ch.add( points[i++] );
            }
        }

        while( false == Triangle.counterClockWise( ch.getPoint(-2),
                                                   ch.getPoint(-1),
                                                   points[0] ) ) ch.removeLast();
        // ch.add( points[0] );

        return ch;
    }

    private static void sortBySmallerAngle( Point2D [] points, int n )
    {
        LinkedList<Point2D>  list  = new LinkedList<Point2D>();

        for( int i=1; i < n; i++ ) list.add( points[i] );

        sortBySmallerAngle( points[0], list );

        for( int i=1; i < n; i++ ) points[i] = list.poll();
    }
    private static void sortBySmallerAngle( Point2D reference, LinkedList<Point2D> list )
    {
        if ( list.size() >= 2 ) {

            LinkedList<Point2D>  left  = new LinkedList<Point2D>();
            LinkedList<Point2D>  right = new LinkedList<Point2D>();

            while( list.size() > 1 ) {
                left.add( list.poll() );
                right.add( list.poll() );
            }
            if ( list.size() > 0 ) left.add( list.poll() );

            sortBySmallerAngle( reference, left );
            sortBySmallerAngle( reference, right );

            mergeLists( reference, left, right, list );
        }
    }
    private static void mergeLists( Point2D reference, LinkedList<Point2D> la, LinkedList<Point2D> lb, LinkedList<Point2D> lc )
    {
        lc.clear();

        while( la.size() > 0 && lb.size() > 0 ) {
            if ( smallerAngle( reference, la.peek(), lb.peek() ) < 0 ) {
                lc.add( la.poll() );
            } else {
                lc.add( lb.poll() );
            }
        }
        while( la.size() > 0 ) lc.add( la.poll() );
        while( lb.size() > 0 ) lc.add( lb.poll() );
    }
    private static int smallerAngle( Point2D reference, Point2D p1, Point2D p2 )
    {
        if ( Triangle.collinear( reference, p1, p2 ) ) {

            if ( reference.distanceFrom( p1 ) <= reference.distanceFrom( p2 ) )
                return -1;
            else
                return  1;

        } else if ( Triangle.counterClockWise( reference, p1, p2 ) ) {
            return -1;
        } else {
            return  1;
        }
    }
    private static Point2D [] sortAndRemoveDuplicates( Point2D [] points, int n )
    {
        Arrays.sort( points, 0, n-1 );

        ArrayList<Point2D> l = new ArrayList<Point2D>();
        
        l.add( points[0] );
        int lastInserted=0;

        for( int i=0; i < n; i++ ) {
            if ( ! points[lastInserted].equals( points[i] ) ) {
                l.add( points[i] );
                lastInserted=i;
            }
        }

        return l.toArray( new Point2D[l.size()] );
    }
    // Methods required by the Convex Hull algorithm

    /* Triangularisation
       The Van Gogh's algorithm needs the points sorted counter-clock-wise.
       Any polygon with four or more points can be trigularised.
       Let v be the current vertex being explored,
           l the left neighbour,
           r the right neighbour and
           (l-v-r) the triangle formed by the three points/vertices.

       The algorithm checks if a new segment can be added from l to r to obtain
       a new triangle (l-v-r) without any other point of the polygon inside
       (l-v-r), i.e. the new segment doesn't cut any other segment of the
       polygon, even the segments added so far to create triangles.

       If the triangle (l-v-r) is possible, then the vertex v is removed from
       the list of vertices of the polygon.
       The algorithm is applied till only three points remain, i.e. the
       polygon has been reduced to a triangle.
     */

    private boolean possibleEar( Point2D l, Point2D v, Point2D r )
    {
        if ( Triangle.clockWise( l, v, r ) ) return false;

        for( int i=0; i < numberOfPoints; i++ ) {
            if ( points[i] != l && points[i] != v && points[i] != r ) {
                if ( Triangle.pointInside( l, v, r, points[i] ) ) return false;
            }
        }
        return true;
    }
    public LinkedList<Triangle> triangulate()
    {
        int  left[] = new int [ numberOfPoints ];
        int right[] = new int [ numberOfPoints ];

        for( int i=0; i < numberOfPoints; i++ ) {
             left[i] = (i-1+numberOfPoints) % numberOfPoints;
            right[i] = (i+1+numberOfPoints) % numberOfPoints;
        }
        LinkedList<Triangle> triangles = new LinkedList<Triangle>();
        int i = numberOfPoints-1;
        while( triangles.size() < (numberOfPoints-2) ) {
            i = right[i];

            if ( possibleEar( points[ left[i] ], points[i], points[ right[i] ] ) ) {
                triangles.add( new Triangle( points[ left[i] ], points[i], points[ right[i] ] ) );
                 left[ right[i] ] =  left[i];
                right[  left[i] ] = right[i];
            }
        }
        return triangles;
    }
    // Triangularisation
}
