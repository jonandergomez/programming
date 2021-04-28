
import java.util.*;
import java.io.*;

public class ReadNumbersOriginal
{
    private static Scanner input;

    public static void main(String [] args)
    {
        input = new Scanner(System.in);

        System.out.print("Enter an integer number from 1 up to 100, both included: ");
        int n = input.nextInt();

        System.out.print("Enter a real number in the range 0..1: ");
        double x = input.nextDouble();


        System.out.println();
        System.out.println();
        System.out.printf(" n = %d   x = %f\n", n, x);
        System.out.println();
        System.out.println();
    }
}
