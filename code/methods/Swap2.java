public class Swap2
{
    public static void swap( Point p1, Point p2 )
    {
        Point temp = p1;
        p1 = p2;
        p2 = temp;
    }
    public static void main( String [] args )
    {
        Point p1 = new Point();
        Point p2 = new Point( 5, 2 );
        swap( p1, p2 );
        System.out.println( "p1 = " + p1 + "  p2 = " + p2 );
    }
}
