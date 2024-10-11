import java.util.Scanner;
import java.util.Locale;

public class TestRectangle {
    public static void main(String [] args) {
        Rectangle r1 = new Rectangle(new Point(10, 10), new Point(2, 12));

        System.out.println(r1);

        Square s1 = new Square(3, 10, 70);

        System.out.println(s1);
    }
}
