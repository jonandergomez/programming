import java.util.Locale;
import java.util.Scanner;

/*
  More info in the following link:

    https://en.wikipedia.org/wiki/Fibonacci_number
*/

public class Fibonacci
{
    private static Scanner input = new Scanner(System.in).useLocale(Locale.US);

    public static void main(String [] args)
    {
        for (int n = 0; n < 20; n++) {
            System.out.printf("%4d %20d %20d\n", n, fib(n), fib2(n));
        }
    }

    public static long fib(int n)
    {
        long fib = 1, fib_ant = -1;

        for (int i = 0; i <= n; i++) {
            fib = fib + fib_ant;
            fib_ant = fib - fib_ant;
        }

        return fib;
    }
    public static long fib2(int n)
    {
        long fib = 1, fib_ant = -1;

        do {
            fib = fib + fib_ant;
            fib_ant = fib - fib_ant;
        } while (--n >= 0);

        return fib;
    }
}