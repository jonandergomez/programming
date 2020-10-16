
import java.util.*;
import java.io.*;


public class Dice2
{
    private static Scanner input = new Scanner(System.in).useLocale(Locale.US);


    public static void main(String [] args)
    {
        Random r = new Random();

        System.out.println("dice thrown with value: " + (r.nextInt(6)+1));
    }
}
