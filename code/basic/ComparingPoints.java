
import java.util.*;
import java.io.*;


public class ComparingPoints
{
    private static Scanner input = new Scanner( System.in ).useLocale( Locale.US );

    public static void main(String [] args)
    {
        Point p1 = new Point(100, 200);
        Point p2 = new Point(100, 200);
        Point p3 = p1;

        System.out.printf("p1 and p2 %s the same object\n", ((p1==p2) ? "are" : "are not"));
        System.out.printf("p1 and p3 %s the same object\n", ((p1==p3) ? "are" : "are not"));
        System.out.printf("p2 and p3 %s the same object\n", ((p2==p3) ? "are" : "are not"));

        System.out.printf("p1 and p2 %s equal\n", ( p1.equals(p2) ? "are" : "are not"));
        System.out.printf("p1 and p3 %s equal\n", ( p1.equals(p3) ? "are" : "are not"));
        System.out.printf("p2 and p3 %s equal\n", ( p2.equals(p3) ? "are" : "are not"));
    }
}
