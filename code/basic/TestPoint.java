import java.util.*;

public class TestPoint
{
    public static void main(String [] args)
    {
        Point p0 = new Point();
        Point p1 = new Point(7, 8);
        Point p2 = new Point(1, 2);

        System.out.println("p0: " + p0);
        System.out.println("p1: " + p1);
        System.out.println("p2: " + p2);

        p0.setX(65);
        p0.setY(-7);
        System.out.println("p0: " + p0);
    }
}
