
import java.util.*;
import java.io.*;

public class ReadNumbers2
{
    private static Scanner input;

    public static void main(String [] args)
    {
        input = new Scanner(System.in);
        boolean correct;
        int n = 0;
        double x = 0.0;

        do {
            correct = true;
            try {
                System.out.print("Enter an integer number from 1 up to 100, both included: ");
                n = input.nextInt();
                if (n < 0 || n > 100) throw new InputMismatchException("Out of range!");
            }
            catch (InputMismatchException ime) {
                correct = false;
                System.err.println("ERROR: " + ime.getMessage());
            }
            finally
            {
                input.nextLine();
            }
        } while (! correct);

        do {
            correct = true;
            try {
                System.out.print("Enter a real number in the range 0..1: ");
                x = input.nextDouble();
                if (x < 0.0 || x > 1.0) throw new InputMismatchException("Out of range!");
            }
            catch (InputMismatchException ime) {
                correct = false;
                System.err.println("ERROR: " + ime.getMessage());
            }
            finally
            {
                input.nextLine();
            }
        } while (! correct);


        System.out.println();
        System.out.println();
        System.out.printf(" n = %d   x = %f\n", n, x);
        System.out.println();
        System.out.println();
    }
}
