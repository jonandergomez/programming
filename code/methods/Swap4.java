public class Swap4
{
    public static final String UNITS = "cm";

    public static void swap(Point p1, Point p2)
    {
        double temp;

        temp = p1.getX();
        p1.setX(p2.getX());
        p2.setX(temp);

        temp = p1.getY();
        p1.setY(p2.getY());
        p2.setY(temp);
    }
    public static void main(String [] args)
    {
        Point p1 = new Point(4, 3);
        Point p2 = new Point(30, 40);
        String s;
        double d;

        s = p1.toString();
        d = p1.distanceFromOrigin();
        System.out.println(s + " is at a distance of "
                             + d + " " + Swap4.UNITS
                             + " from the origin.");
        s = p2.toString();
        d = p2.distanceFromOrigin();
        System.out.println(s + " is at a distance of "
                             + d + " " + Swap4.UNITS
                             + " from the origin.");

        swap(p1, p2);

        d = p1.distance(p2);

        System.out.println(p1 + " is at a distance of "
                              + d + " " + Swap4.UNITS
                              + " from " + p2);
    }
}
