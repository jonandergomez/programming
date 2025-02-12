import java.util.Locale;
import java.util.Scanner;

public class Exponential
{
    private static Scanner input = new Scanner(System.in).useLocale(Locale.US);

    public static void main(String [] args)
    {
        double x = 0;
        int    n = 10;
        double epsilon = 1.0e-15;

        // this for uses arrays, please, do not worry about arrays
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-x"))
                x = Double.parseDouble(args[i + 1]);
            else if (args[i].equals("-n"))
                n = Integer.parseInt(args[i + 1]);
        }

        //System.out.printf("our computation %25.17e\n", myExp1(x, n));
        //System.out.printf("our computation %25.17e\n", myExp2(x, n));
        System.out.printf("our computation %25.17e\n", myExp3(x, epsilon));
        System.out.printf("CPU computation %25.17e\n", Math.exp(x));
    }

    private static double myExp1(double x, int n)
    {
        // Loop initialization begins
        double sum = 1.0,
               num = 1.0,
               den = 1.0;
        // Loop initialization ends

        System.out.printf("i = %4d %25.17e  %25.17e  %25.17e\n", 0, num, den, sum);
        for (int i = 1; i <= n; i++) { // Loop guard
            // Loop body begins
            num *= x;
            den *= i;
            sum += num / den;
            // Loop body ends
            System.out.printf("i = %4d %25.17e  %25.17e  %25.17e\n", i, num, den, sum);
        }

        return sum;
    }
    private static double myExp2(double x, int n)
    {
        if (x < 0.0) return 1.0 / myExp2(-x, n);

        // Loop initialization begins
        double sum = 1.0, term = 1.0;
        // Loop initialization ends

        for (int i = 1; i <= n; i++) { // Loop guard
            // Loop body begins
            System.out.printf("i = %4d %25.17e  %25.17e\n", i, term, sum);
            term *= x / i;
            sum += term;
            // Loop body ends
        }
        return sum;
    }
    private static double myExp3(double x, double epsilon)
    {
        if (x < 0.0) return 1.0 / myExp3(-x, epsilon);

        // Loop initialization begins
        double sum = 1.0, term = 1.0;
        int i = 0;
        // Loop initialization ends
        //while (term > epsilon) {
        while (sum + term != sum) { // Loop guard
            // Loop body begins
            System.out.printf("i=%6d    %25.17e  %25.17e\n", ++i, term, sum);
            term *= x / i;
            sum += term;
            // Loop body ends
        }
        return sum;
    }
}
