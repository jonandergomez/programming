import java.util.*;


public class SimpleProduct
{
    public static void main(String [] args)
    {
        Random r = new Random();

        int a = r.nextInt(100);
        int b = r.nextInt(100);

        System.out.printf("%d * %d = %d\n", a, b, product(a, b));

        stressTest();
    }

    public static void stressTest()
    {
        Random r = new Random();

        while (true) {
            int a = r.nextInt(1000);
            int b = r.nextInt(1000);
            int p = product(a, b);

            System.out.print('.');

            if (p != a*b)
                throw new Error(String.format("%d * %d = %d != %d", a, b, p, a*b));
        }
    }

    public static int product(int a, int b)
    {
        int sum = 0;

        while (--b >= 0) {
            sum += a;
        }
        return sum;
    }
}
