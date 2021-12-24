public class Program02
{
    public static void main(String [] args)
    {
        Point p1 = new Point( 2.5,  3.0);
        Point p2 = new Point( 2.5, -1.2);
        Point p3 = new Point(-1.5,  1.4);

        System.out.println("Triangle with vertex at points:");
        //System.out.printf("\t (%.2f, %.2f) \n", p1.getX(), p1.getY());
        //System.out.printf("\t (%.2f, %.2f) \n", p2.getX(), p2.getY());
        //System.out.printf("\t (%.2f, %.2f) \n", p3.getX(), p3.getY());
        System.out.printf("\t %s \n", p1);
        System.out.printf("\t %s \n", p2);
        System.out.printf("\t %s \n", p3);

        //double sideLength12 = distance(p1.getX(), p1.getY(), p2.getX(), p2.getY());
        //double sideLength23 = distance(p2.getX(), p2.getY(), p3.getX(), p3.getY());
        //double sideLength31 = distance(p3.getX(), p3.getY(), p1.getX(), p1.getY());
        /*
        double sideLength12 = distance(p1, p2);
        double sideLength23 = distance(p2, p3);
        double sideLength31 = distance(p3, p1);
        double perimeter = sideLength12 + sideLength23 + sideLength31;
        */
        double perimeter = p1.distance(p2) + p2.distance(p3) + p3.distance(p1);
        System.out.println("Perimeter = " + perimeter);
    }

    public static double distance(double x1, double y1, double x2, double y2)
    {
        double dx = x1 - x2, dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }
    public static double distance(Point p, Point q)
    {
        double dx = p.getX() - q.getX(), dy = p.getY() - q.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
