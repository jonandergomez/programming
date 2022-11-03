import java.util.Random;

public class EuclidesGCD
{
    public static void main(String [] args)
    {
        Random r = new Random();
        boolean verbose = false;

        int a = r.nextInt(100000000);
        int b = r.nextInt(100000000);

        System.out.printf("gcd(%d, %d)= ", a, b);

        if (verbose) System.out.printf("\n   B -- a = %15d  b = %15d\n", a, b);

        //while (a != b && a != 0 && b != 0)
        while (a != 0 && b != 0) {
            if (a > b) {
                a = a % b;
            } else {
                b = b % a;
            }
            if (verbose) System.out.printf("   I -- a = %15d  b = %15d\n", a, b);
        }
        if (verbose) System.out.printf("   A -- a = %15d  b = %15d\n", a, b);

        if (a == 0 && b == 0)
            System.out.println(1);
        else
            System.out.println((a == 0) ? b : a);
    }
}