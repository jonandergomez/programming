
public class TestingEquals
{
  public static void main(String [] args)
  {
    Point p1 = new Point(3.0, 4.0);
    Point p2 = new Point(3.0, 4.0);
    Point p3 = p1;

    System.out.printf("%s and %s %s the same object\n", p1, p2, p1 == p2 ? "are" : "are not");
    System.out.printf("%s and %s %s the same object\n", p1, p3, p1 == p3 ? "are" : "are not");
    System.out.printf("%s and %s %s the same object\n", p2, p3, p2 == p3 ? "are" : "are not");

    System.out.printf("%s and %s %s equal\n", p1, p2, p1.equals(p2) ? "are" : "are not");
    System.out.printf("%s and %s %s equal\n", p1, p3, p1.equals(p3) ? "are" : "are not");
    System.out.printf("%s and %s %s equal\n", p2, p3, p2.equals(p3) ? "are" : "are not");
  }
}
