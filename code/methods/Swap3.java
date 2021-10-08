public class Swap3
{
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
        Point p1 = new Point();
        Point p2 = new Point(5, 2);
        swap(p1, p2);
        System.out.println("p1 = " + p1 + "  p2 = " + p2);
    }
}
