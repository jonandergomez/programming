import java.util.*;

public class Plane
{
    private static Scanner input = new Scanner(System.in).useLocale(Locale.US);

    public static void main(String [] args)
    {
        double x, y;

        System.out.print("\n x: "); x = input.nextDouble();
        System.out.print("\n y: "); y = input.nextDouble();

        if (y > 0.0) {
            if (x > 0.0) {
                System.out.println("First quadrant.");
            } else if (x < 0.0) {
                System.out.println("Second quadrant.");
            } else { // x == 0.0
                System.out.println("Y axis.");
            }
        } else if (y < 0.0) {
            if (x > 0.0) {
                System.out.println("Fourth quadrant.");
            } else if (x < 0.0) {
                System.out.println("Third quadrant.");
            } else { // x == 0.0
                System.out.println("Y axis.");
            }
        } else { // y == 0.0
            if (x == 0.0) {
                System.out.println("Origin of coordinates.");
            } else {
                System.out.println("X axis.");
            }
        }
    }
}