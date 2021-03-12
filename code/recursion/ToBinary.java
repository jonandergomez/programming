import java.util.*;


public class ToBinary
{
    public static void main(String [] args)
    {
        Random r = new Random();

        for (int i = 0; i < 10; i++) {
            long n = r.nextInt(1 << 10);
            System.out.printf(" %12d  %20d\n", n, toBinary(n));
        }
    }

    private static long toBinary(long n)
    {
        if (n < 2)
            return n;
        else
            return 10 * toBinary(n >> 1) + (n % 2);
    }
}
