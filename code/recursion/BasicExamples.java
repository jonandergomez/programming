
public class BasicExamples
{
    private static boolean debug = true;

    public static void main(String [] args)
    {
        System.out.println(natural(100));

        if (debug)
            System.out.println(factorial_debug(9, 1));
        else
            System.out.println(factorial(9));


        System.out.println(sumOfFigures_i(415327));
        if (debug)
            System.out.println(sumOfFigures_r_debug(415327, 1));
        else
            System.out.println(sumOfFigures_r(415327));

        int n = 55;

        if (args.length > 0) n = Integer.parseInt(args[0]);

        long ms = System.currentTimeMillis();
        long f = fibonacci_i(n);
        ms = System.currentTimeMillis() - ms;
        System.out.printf("Iterative version computed f(%d) = %d and needed %.6f seconds\n", n, f, ms / 1.0e+3);
        ms = System.currentTimeMillis();
        f = fibonacci_r(n);
        ms = System.currentTimeMillis() - ms;
        System.out.printf("Recursive version computed f(%d) = %d and needed %.6f seconds\n", n, f, ms / 1.0e+3);
    }

    private static int natural(int n)
    {
        if (n == 0)
            return 0;
        else
            return 1 + natural(n - 1);
    }

    private static int factorial(int n)
    {
        if (n == 0)
            return 1;
        else
            return n * factorial(n - 1);
    }
    private static int factorial_debug(int n, int deep)
    {
        System.out.printf("%s--> factorial(%d)\n", white_spaces(deep), n);
        int f;
        if (n == 0)
            f = 1;
        else {
            f = n * factorial_debug(n - 1, deep + 1);
            System.out.printf("%s<-- factorial(%d)\n", white_spaces(deep), n);
        }
        return f;
    }
    private static String white_spaces(int n)
    {
        StringBuffer fb = new StringBuffer("");

        while (--n >= 0) fb.append("  ");

        return fb.toString();
    }



    private static int countOfFigures_i(int n)
    {
        int count = 1;

        while (n >= 10) {
            ++count;
            n /= 10;
        }
        return count;
    }
    private static int sumOfFigures_i(int n)
    {
        int sum = 0;

        while (n > 0) {
            sum += n % 10;
            n = n / 10;
        }
        return sum;
    }
    private static int sumOfFigures_r(int n)
    {
        if (n == 0) // trivial case
            return 0;
        else // general case
            return n % 10 + sumOfFigures_r(n / 10);
    }
    private static int sumOfFigures_r_debug(int n, int deep)
    {
        System.out.printf("%s--> sumOfFigures(%d)\n", white_spaces(deep), n);
        if (n == 0) // trivial case
            return 0;
        else { // general case
            int result = n % 10 + sumOfFigures_r_debug(n / 10, deep + 1);
            System.out.printf("%s<-- sumOfFigures(%d)\n", white_spaces(deep), n);
            return result;
        }
    }

    private static long fibonacci_i(int n)
    {
        long f = 0, f_ant = 1;

        while (--n >= 0) {
            f = f + f_ant;
            f_ant = f - f_ant;
        }
        return f;
    }

    private static long fibonacci_r(int n)
    {
        if (n < 0) throw new RuntimeException("Are you a programmer?");

        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        else
            return fibonacci_r(n - 1) + fibonacci_r(n - 2);
    }
}
