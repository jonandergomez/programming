import java.util.Random;

public class SimpleProduct
{
    public static void main(String [] args)
    {
        Random r = new Random();

        int a = 3; // r.nextInt(100);
        int b = 4; // r.nextInt(100);

        System.out.printf("%d * %d = %d\n", a, b, product_v1(a, b, true));

        //stressTest();
    }

    public static void stressTest()
    {
        Random r = new Random();

        while (true) {
            int a = r.nextInt(1000);
            int b = r.nextInt(1000);
            int p = product_v2(a, b, false);

            System.out.print('.');

            if (p != a * b)
                throw new Error(String.format("%d * %d = %d != %d", a, b, p, a * b));
        }
    }

    public static int product_v2(int a, int b, boolean verbose)
    {
        // Loop initialization
        int sum = 0;

        if (verbose)
            System.out.printf("before the loop: a = %10d  b = %10d  sum = %10d\n", a, b, sum);

        while (--b >= 0) { // Loop guard
            // Loop body begins
            sum += a;
            if (verbose)
                System.out.printf("inside the loop: a = %10d  b = %10d  sum = %10d\n", a, b, sum);
            // Loop body ends
        }
        if (verbose)
            System.out.printf(" after the loop: a = %10d  b = %10d  sum = %10d\n", a, b, sum);
        return sum;
    }

    public static int product_v1(int a, int b, boolean verbose)
    {
        // Loop initialization
        int sum = 0;
        int i = 0;

        if (verbose)
            System.out.printf("before the loop: a = %10d  b = %10d  i = %10d  sum = %10d\n", a, b, i, sum);

        while (i < b) { // Loop guard
            // Loop body begins
            sum += a;
            i++;
            if (verbose)
                System.out.printf("inside the loop: a = %10d  b = %10d  i = %10d  sum = %10d\n", a, b, i, sum);
            // Loop body ends
        }
        if (verbose)
            System.out.printf(" after the loop: a = %10d  b = %10d  i = %10d  sum = %10d\n", a, b, i, sum);
        return sum;
    }
}