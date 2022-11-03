import java.util.Random;

public class SimpleProduct
{
    public static void main(String [] args)
    {
        Random r = new Random();

        int a = r.nextInt(100);
        int b = r.nextInt(100);

        System.out.printf("%d * %d = %d\n", a, b, product(a, b, true));

        //stressTest();
    }

    public static void stressTest()
    {
        Random r = new Random();

        while (true) {
            int a = r.nextInt(1000);
            int b = r.nextInt(1000);
            int p = product(a, b, false);

            System.out.print('.');

            if (p != a * b)
                throw new Error(String.format("%d * %d = %d != %d", a, b, p, a * b));
        }
    }

    public static int product(int a, int b, boolean verbose)
    {
        int sum = 0;

        if (verbose)
            System.out.printf("before the loop: a = %10d  b = %10d  sum = %10d\n", a, b, sum);

        while (--b >= 0) {
            sum += a;
            if (verbose)
                System.out.printf("inside the loop: a = %10d  b = %10d  sum = %10d\n", a, b, sum);
        }
        if (verbose)
            System.out.printf(" after the loop: a = %10d  b = %10d  sum = %10d\n", a, b, sum);
        return sum;
    }
}