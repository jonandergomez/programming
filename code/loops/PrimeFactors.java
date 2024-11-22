import java.util.Scanner;
import java.util.Random;


public class PrimeFactors
{
    public static void main(String [] args) {

        computeAndShow(2);
        computeAndShow(3);
        computeAndShow(10);
        computeAndShow(100);
        computeAndShow(512);
        computeAndShow(5417);
        computeAndShow(5147);

        Random r = new Random();

        computeAndShow(Math.abs(r.nextInt()));
    }


    private static void computeAndShow(int n) {

        boolean debug = true;

        System.out.println("\n Prime factor decomposition of integer " + n);
        int e = 0;
        while (n > 0 && (n % 2) == 0) {
            e++;
            n /= 2;
            if (debug) System.out.printf("f = %d   n = %d   e = %d\n", 2, n, e);
        }
        if (e > 0) {
            System.out.printf("%12d^%d\n", 2, e);
        }

        int f = 3;
        do {
            e = 0;
            while (n > 0 && (n % f) == 0) {
                e++;
                n /= f;
                if (debug) System.out.printf("f = %d   n = %d   e = %d\n", f, n, e);
            }
            if (e > 0) {
                System.out.printf("%12d^%d\n", f, e);
            }
            f += 2;
        } while (n >= f);
    }
}