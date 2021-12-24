
import java.util.*;
import java.io.*;

public class ReadNumbers
{
    private static Scanner input;

    public static void main(String [] args)
    {
        input = new Scanner(System.in); // .useLocale(Locale.US);
        // Uncomment useLocale() for using the dot as the decimal separator
        boolean correct;
        int n = 0;
        double x = 0.0;

        do {
            try {
                correct = true;
                System.out.print("Enter an integer number from 1 up to 100, both included: ");
                n = input.nextInt();
                if (n < 1 || n > 100) throw new Exception("Out of range!");
            }
            catch (InputMismatchException ime) {
                correct = false;
                String message = ime.getMessage();
                if (message != null)
                    System.err.println("Catch 1 " + message);
                else
                    System.err.println("Catch 1 :  unknown reason");
            }
            catch (Exception e) {
                correct = false;
                System.err.println("Catch 2 " + e.getMessage());
            }
            finally {
                input.nextLine(); // For cleaning the standard input buffer
            }

        } while (! correct);

        do {
            try {
                correct = true;
                System.out.print("Enter a real number in the range 0..1: ");
                x = input.nextDouble();
                if (x < 0.0 || x > 1.0) throw new Exception("Out of range!");
            }
            catch (InputMismatchException ime) {
                correct = false;
                String message = ime.getMessage();
                if (message != null)
                    System.err.println(message);
                else
                    System.err.println(ime.toString());
            }
            catch (Exception e) {
                correct = false;
                System.err.println(e.getMessage());
            }
            finally {
                input.nextLine(); // For cleaning the standard input buffer
            }
        } while (! correct);


        System.out.println();
        System.out.println();
        System.out.printf(" n = %d   x = %f\n", n, x);
        System.out.println();
        System.out.println();
    }
}
