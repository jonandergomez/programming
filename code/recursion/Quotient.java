
public class Quotient
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

        int q = quotient(a, b);
        System.out.printf("integer quotient of %d / %d is %d\n", a, b, q);

        int p = product(a, b);
        System.out.printf("product of %d * %d is %d\n", a, b, p);
    }

    /* precondition: a >= 0 and b > 0

        if a < b : return 0
        if a >= b : return quotient(a - b, b) + 1
    */
    private static int quotient(int a, int b)
    {
        if (a < b)
            return 0;
        else
            return 1 + quotient(a - b, b);
    }

    /*
        a*b = 0 if b == 0
        a*b = a * (b-1) + a if b > 0
    */
    private static int product(int a, int b)
    {
        if (b == 0)
            return 0;
        else
            return a + product(a, b - 1);
    }
}