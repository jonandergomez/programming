
public class GreatestCommonDivisor
{
    public static void main(String [] args)
    {
        int a = 71, b = 9;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-a"))
                a = Integer.parseInt(args[i+1]);
            else if (args[i].equals("-b"))
                b = Integer.parseInt(args[i+1]);
        }

        int g = gcd(a, b);
        System.out.printf("gcd(%d, %d) = %d\n", a, b, g);
    }

    /*
        gcd(a,b) = a if b == 0
        gcd(a,b) = b if a == 0
        gcd(a,b) = a if a == b
        gdc(a,b) = gcd(a % b, b) if a > b
        gdc(a,b) = gcd(a, b % a) if a < b

        the original Euclide's algorithm also returns x and y
        such that a * x + b * y = gcd(a, b)
    */
    private static int gcd(int a, int b)
    {
        System.out.printf("gcd(): a = %d  b = %d\n", a, b);

        if (b == 0)      return a;
        else if (a == 0) return b;
        else if (a == b) return a;
        else if (a > b)  return gcd(a % b, b);
        else             return gcd(a, b %a);
    }
}