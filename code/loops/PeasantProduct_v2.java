import java.util.*;

public class PeasantProduct_v2
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

	public static int peasantProduct_1(int a, int b)
	{
        int p = 0;
        int sign = 1;
        if (a < 0) { sign *= -1; a = -a; }
        if (b < 0) { sign *= -1; b = -b; }

        while (b > 0) {
            if (b % 2 != 0) p += a;
            a = a * 2;
            b = b / 2;
        }

        return sign * p;
	}

	public static int peasantProduct_2(int a, int b)
	{
        int p = 0;
        int sign = 1;
        if (a < 0) { sign *= -1; a = -a; }
        if (b < 0) { sign *= -1; b = -b; }

        do {
            if (b % 2 != 0) p += a;
            a = a * 2;
            b = b / 2;
        } while (b > 0);

        return sign * p;
	}

}