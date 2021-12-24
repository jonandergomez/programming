import java.util.Random;

public class Eratosthenes
{
    private boolean [] preComputed;

    public Eratosthenes(int size)
    {
        preComputed = new boolean [size];
        preCompute();
    }
    public Eratosthenes()
    {
        this(1000000);
    }

    public int size()
    {
        return preComputed.length;
    }

    public boolean isPrime(int n)
    {
        if (n < preComputed.length) {
            return preComputed[n];
        } else {
            return checkPrimality(n);
        }
    }

    public boolean checkPrimality(int n)
    {
        if (n <= 1) return false; // 0 and 1 are not prime numbers
        if (n == 2) return true; // 2 is the unique even natural number that is a prime number
        if (n % 2 == 0) return false; // even numbers are not prime numbers

        // any possible divisor of 'n' lower than sqrt(n) needs to be multiplied
        // by another divisor greater than sqrt(n), so checking till sqrt(n) is enough;
        // additionally, sqrt(n) must be also checked to ensure perfect squares are not
        // considered as prime numbers
        long sqrt_n = Math.round(Math.sqrt(n));
        //long sqrt_n = (long)Math.floor(Math.sqrt(n));
        for (int i = 3; i <= sqrt_n; i += 2) // only check odd numbers as possible divisors
            if (n % i == 0) return false; // a divisor found, no further checkings are needed

        return true; // no possible divisor found
    }

    private void preCompute()
    {
        // by default consider all natural numbers from 2 to .lentgh as prime numbers
        for (int i = 2; i < preComputed.length; ++i) preComputed[i] = true;

        /*
            thanks to Mario Fabado Gómez-Lobo contributions:
                1) the most outer loop only needs to iterate till
                   sqrt(preComputed.length) instead till preComputed.length
                2) the most innter loop can start from i*i instead from i+i,
                   because all the multiples of 'i' between 2*i and i*i have
                   already been discarded as prime numbers by the algorithm
                   when applied to prime numbers lower than 'i'
        */
        long sqrt_l = Math.round(Math.sqrt(preComputed.length));
        for (int i = 2; i <= sqrt_l; ++i) {
            if (preComputed[i]) {
                for (int j = i * i; j < preComputed.length; j += i) {
                    preComputed[j] = false;
                }
            }
        }
    }

    public static void main(String [] args)
    {
        Random r = new Random();
        Eratosthenes primes = new Eratosthenes(1000000 + r.nextInt(3000001));
        boolean stressTest = true;

        if (stressTest) {
            int i;
            for (i = 1; i <= primes.size(); ++i) {
                boolean rc1 = primes.isPrime(i);
                boolean rc2 = primes.checkPrimality(i);

                if (rc1 != rc2) {
                    throw new Error("the solution fails at number " + i);
                }

                // show a period symbol every 1000 processed numbers
                if (i % 1000 == 0) System.out.print(".");
                // show a reporting every 100K processed numbers
                if (i % 100000 == 0)
                    System.out.printf(" %5d K\n", i/1000);
            }
            // if the message reporting amount was not show in last iteration, then
            if (i % 100000 != 0) {
                // fill the pending white spaces
                System.out.printf(String.format("%%%ds", 100 - ((i % 100000) / 1000)), " ");
                // print the report
                System.out.printf(" %5d K\n", i/1000);
            }
            // final report
            System.out.printf("\nTotally processed around %.2f M numbers\n\n", i / 1.0e+6);

        } else {

            for (int i = 0; i < 20; ++i) {
                int n = r.nextInt(1 << 30); // 2 raised 30 = 0x040000000 = 1073741824
                System.out.printf("%12d %s a prime number\n",
                                    n, primes.isPrime(n) ? "  is  " : "is not");
            }
        }
    }

    /*
        as home work you can prepare a stress test with isPrime() against checkPrimality()

        *** the home work is already included in this version ***
    */
}
