import java.util.*;
import java.io.*;

public class Dice
{
    private static Scanner input = new Scanner(System.in).useLocale(Locale.US);

    public static void main(String [] args)
    {
        int dice = (int)(6 * Math.random()) + 1;

        System.out.println("The roll of the dice is: " + dice);

        testing();
    }

    private static void testing()
    {
        while (true) {
            double d = Math.random();
            int d1 = (int)(6 * d) + 1;
            int d2 = (int)(6 * d + 1);

            System.out.printf("%d vs %d\n", d1, d2);

            if (d1 != d2)
                throw new RuntimeException("the impossible happened!");
        }
    }
}
