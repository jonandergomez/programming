import java.util.*;

public class Sums
{
    public static void main(String [] args)
    {
        Scanner input = new Scanner(System.in);

        int n;
        do {
            System.out.print("\n Please, enter a positive value for N: ");
            n = input.nextInt();
            System.out.print("\n Please, enter a positive value for X: ");
            double x = input.nextDouble();
            if (n >= 0) {
                System.out.printf("Sum(i) till n=%d is %d\n", n, sum_i(n));
                System.out.printf("Sum(i*i) till n=%d is %d\n", n, sum_ii(n));
                System.out.printf("Sum(1/i) till n=%d is %.12f\n", n, sum_1_i(n));
                System.out.printf("Sum(1/(i*i)) till n=%d is %.12f\n", n, sum_1_ii(n));
                System.out.printf("%d! is %d\n", n, factorial(n));
                System.out.printf("Sum(2^i)) till n=%d is %d\n", n, sum_2ri(n));
                System.out.printf("Sum(1/2^i)) till n=%d is %.12f\n", n, sum_1_2ri(n));
                System.out.printf("x^%d is %.12f\n", n, x_raised_n(x, n));
            }
        } while (n >= 0);
    }

    public static long sum_i(int n)
    {
        /*
            s = 1 + 2 + 3 + 4 + 5 + 6 + 7 + ...
        */
        long sum = 0;

        for (int i=1; i <= n; i++) sum += i;

        return sum;
    }
    public static long sum_ii(int n)
    {
        /*
            s = 1 + 4 + 9 + 16 + 25 + 36 + 49 + ...
        */
        long sum = 0;

        for (int i=1; i <= n; i++) sum += i*i;

        return sum;
    }
    public static double sum_1_i(int n)
    {
        double sum = 0;

        for (int i=1; i <= n; i++) sum += 1.0/i;

        return sum;
    }
    public static double sum_1_ii(int n)
    {
        double sum = 0;

        for (double i=1; i <= n; i++) {
        /*
            System.out.printf("DEBUG: i=%f i*i=%f term=%e\n", i, i*i, 1.0/(i*i));
            if (i*i == 0) break;
        */
            sum += 1.0/(i*i);
        }

        return sum;
    }
    public static long factorial(int n)
    {
        long p = 1;

        for (int i=1; i <= n; i++) p *= i;

        return p;
    }
    public static long sum_2ri(int n)
    {
        long sum = 0;
        long p = 1;

        // for (int i=0; i <= n; i++) sum += Math.pow(2,i); // GO TO next academic year

        for (int i=0; i <= n; i++) {
            System.out.printf("BEFORE: %d %d ", sum, p);
            sum += p;
            p *= 2;
            System.out.printf("AFTER: %d %d \n", sum, p);
        }

        return sum;
    }
    public static double sum_1_2ri(int n)
    {
        double sum = 0;
        double p = 1;

        // for (int i=0; i <= n; i++) sum += 1.0/Math.pow(2,i); // GO TO next academic year

        for (int i=0; i <= n; i++) {
            System.out.printf("BEFORE: %e %e ", sum, p);
            // sum += 1/p;
            // p *= 2;
            sum += p;
            p /= 2;
            System.out.printf("AFTER: %e %e \n", sum, p);
        }

        return sum;
    }
    public static double x_raised_n(double x, int n)
    {
        double p = 1;

        // p = Math.pow(x, n);
        for (int i=1; i <= n; i++) p *= x;

        return p;
    }
}
