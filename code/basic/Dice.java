import java.util.*;
import java.io.*;

public class Dice
{
    private static Scanner input = new Scanner(System.in).useLocale(Locale.US);

    public static void main(String [] args)
    {
        int dice = (int)(6 * Math.random()) + 1;

        System.out.println("The roll of the dice is: " + dice);
    }
}
