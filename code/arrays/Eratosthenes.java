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
        if (n == 2) return true;
        if (n % 2 == 0) return false;
        long sqrt_n = Math.round(Math.sqrt(n));
        //long sqrt_n = (long)Math.floor(Math.sqrt(n));
        for (int i = 3; i <= sqrt_n; i += 2)
            if (n % i == 0) return false; // a divisor found

        return true; // no possible divisor found
    }

    private void preCompute()
    {
        for (int i = 2; i < preComputed.length; ++i)
            preComputed[i] = true;
/*
        for (int i = 2; i < preComputed.length; ++i) {
            if (preComputed[i]) {
                for (int j = i * i; j < preComputed.length && j >= 0; j += i) {
                    preComputed[j] = false;
                }
            }
        }
*/
        for (int i = 2; i*i < preComputed.length; ++i) {
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
        Eratosthenes primes = new Eratosthenes();

        for (int i = 0; i < 20; ++i) {
            int n = r.nextInt(1 << 30); // 2 raised 30 = 0x040000000 = 1073741824
            System.out.printf("%12d %s a prime number\n",
                                n, primes.isPrime(n) ? "  is  " : "is not" );
        }
    }

    /*
    As home work you can prepare a stress test with isPrime() against checkPrimality()
    */
}
