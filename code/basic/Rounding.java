import java.util.*;

public class Rounding
{
    public static void main(String [] args)
    {
        Scanner input = new Scanner(System.in).useLocale(Locale.US);

        System.out.print("\n Please, enter a double value:");
        double x = input.nextDouble();

        double y = myRound(x, 2);

        System.out.println(x);
        System.out.println(y);
        System.out.printf("%.2f\n", x);
    }
    public static double myRound(double x, int digits)
    {
        return Math.round(x * Math.pow(10, digits)) / Math.pow(10, digits);
    }
}
