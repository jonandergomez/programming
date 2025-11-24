import java.util.*;

public class PeasantProduct
{
    public static void main(String [] args)
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter an integer value for A: ");
        int a = input.nextInt();
        System.out.print("Enter an integer value for B: ");
        int b = input.nextInt();

        System.out.println();
        System.out.printf("%d * %d = %d\n", a, b, peasantProduct_1(a, b));
        System.out.printf("%d * %d = %d\n", a, b, peasantProduct_2(a, b));
    }
    /*
        Example of how the algorithm evolves for a = 892 and b = 757

               a           b            b % 2          p
          ---------------------------------------------------
             892         757              1          892
            1784         378              0          892
            3568         189              1         4460
            7136          94              0         4460
           14272          47              1        18732
           28544          23              1        47276
           57088          11              1       104364
          114176           5              1       218540
          228352           2              0       218540
          456704           1              1       675244
                           0
    */

	public static int peasantProduct_1(int a, int b)
	{
        // Loop initialization begins
        int p = 0;
        int sign = 1;
        if (a < 0) { sign *= -1; a = -a; }
        if (b < 0) { sign *= -1; b = -b; }
        // Loop initialization ends

        while (b > 0) { // Loop guard
            // Loop body begins
            if (b % 2 != 0) p += a;
            a = a * 2;
            b = b / 2;
            // Loop body ends
        }

        return sign * p;
	}

	public static int peasantProduct_2(int a, int b)
	{
        // Loop initialization begins
        int p = 0;
        int sign = 1;
        if (a < 0) { sign *= -1; a = -a; }
        if (b < 0) { sign *= -1; b = -b; }
        // Loop initialization ends

        do {
            // Loop body begins
            if (b % 2 != 0) p += a;
            a = a * 2;
            b = b / 2;
            // Loop body ends
        } while (b > 0); // Loop guard

        return sign * p;
	}
}
