
public class FibonacciWithMemoization
{
    private long []  temporaryComputations;

    public FibonacciWithMemoization()
    {
        temporaryComputations = new long [1000];
    }

    private long fibonacci_r(int n)
    {
        if (n == 0)
            return 0;
        else if (n == 1)
            return 1;
        else if (n < temporaryComputations.length && temporaryComputations[n] > 0)
            return temporaryComputations[n];
        else {
            temporaryComputations[n] = fibonacci_r(n - 1) + fibonacci_r(n - 2);
            return temporaryComputations[n];
        }
    }

    public long fibonacci(int n)
    {
        if (n > temporaryComputations.length) {
            temporaryComputations = new long [n + 1];
        }

        return fibonacci_r(n);
    }



    public static void main(String [] args)
    {
        FibonacciWithMemoization fwm = new FibonacciWithMemoization();

        int n = 50;

        if (args.length > 0) n = Integer.parseInt(args[0]);

        long ms = System.currentTimeMillis();
        long f = fwm.fibonacci(n);
        ms = System.currentTimeMillis() - ms;
        System.out.printf("Recursive with memoization version computed f(%d) = %d and needed %.6f seconds\n", n, f, ms / 1.0e+3);
    }
}