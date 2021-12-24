
import java.util.*;
import java.io.*;

public class SphereExample1
{
    private static Scanner input = new Scanner(System.in).useLocale(Locale.US);

    public static void main(String [] args)
    {
        System.out.print("\nPlease, enter a positive real value for the radius: ");
        double radius = input.nextDouble();

        double area, volume1, volume2;

        area = 4 * Math.PI * radius * radius;
        volume1 = 4.0 / 3 * Math.PI * radius * radius * radius;
        volume2 = area * radius / 3;


        System.out.printf("   area = %30.20f\n", area);
        System.out.printf("volume1 = %30.20f\n", volume1);
        System.out.printf("volume2 = %30.20f\n", volume2);
    }
}
