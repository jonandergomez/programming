import java.util.Random;

/*
  See more details in this link:

    https://en.wikipedia.org/wiki/Ancient_Egyptian_multiplication#Russian_peasant_multiplication
*/

public class PeasantProduct_v1
{
    public static int iterative(int a, int b)
    {
        // Loop initialization begins
        int p = 0;
        int sign = 1;

        if (b < 0) { a = -a; b = -b; }
        if (a < 0) { sign = -1; a = -a; }

        if (a < b) { int temp = a; a = b; b = temp; }
        // Loop initialization ends

        while (b > 0) { // Loop guard
            // Loop body begins
            if ((b % 2) == 1) p += a;

            a <<= 1;
            b >>= 1;
            // Loop body ends
        }

        return sign * p;
    }
    public static int iterative_debug(int a, int b)
    {
        // Loop initialization begins
        int p = 0;
        int sign = 1;

        if (b < 0) { a = -a; b = -b; }
        if (a < 0) { sign = -1; a = -a; }

        if (a < b) { int temp = a; a = b; b = temp; }
        // Loop initialization ends

        System.out.printf("before the loop: a = %15d  b = %15d  p = %15d\n", a, b, p);

        while (b > 0) { // Loop guard
            // Loop body begins
            if ((b % 2) == 1) p += a;

            System.out.printf("inside the loop: a = %15d  b = %15d  p = %15d\n", a, b, p);

            a <<= 1;
            b >>= 1;
            // Loop body ends
        }
        System.out.printf(" after the loop: a = %15d  b = %15d  p = %15d\n", a, b, p);

        return sign * p;
    }

    public static int wrapper_recursive(int a, int b)
    {
        int sign = 1;
        if (b < 0) { a = -a; b = -b; }
        if (a < 0) { sign = -1; a = -a; }

        if (a < b) {
            return sign * recursive(b, a);
        } else {
            return sign * recursive(a, b);
        }
    }
    public static int recursive(int a, int b)
    {
        if (b == 0) {
            return 0;
        } else if (b == 1) {
            return a;
        } else if (b > 1) {
            if ((b % 2) == 1) {
                return recursive(a << 1, b >> 1) + a;
            } else {
                return recursive(a << 1, b >> 1);
            }
        } else {
            throw new Error("Negative arguments are not accepted!");
        }
    }

    public static void main(String [] args)
    {
        boolean stressTest = false;
        Random r = new Random();

        if (stressTest) {
            while (true) {
                int a = r.nextInt(100000);
                int b = r.nextInt(100000);

                int p1 = a * b;
                int p2 = iterative(a, b);
                int p3 = recursive(a, b);

                System.out.printf(" %6d %6d %15d  %15d  %15d\n", a, b, p1, p2, p3);

                if (p1 != p2 || p1 != p3) throw new Error("Differences were found!");
            }
        } else {
            int a = r.nextInt(100000);
            int b = r.nextInt(100000);
            int p = iterative_debug(a, b);
            System.out.printf(" %6d %6d %15d %15d\n", a, b, p, a * b);
        }
    }
}