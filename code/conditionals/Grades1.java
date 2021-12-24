
import java.util.*;
import java.io.*;


public class Grades1
{
    private static Scanner input = new Scanner(System.in).useLocale(Locale.US);

    public static void main(String [] args)
    {
        System.out.print("\n\n Enter your grade: ");
        double grade = input.nextDouble();

        if (0.0 <= grade && grade < 3.0) {
            System.out.println("\nGo home and never come back here!");
        } else if (3.0 <= grade && grade < 4.0) {
            System.out.println("\nSee you next year!");
        } else if (4.0 <= grade && grade < 5.0) {
            System.out.println("\nI will give you another opportunity!");
        } else if (5.0 <= grade && grade < 7.0) {
            System.out.println("\nPassed!");
        } else if (7.0 <= grade && grade < 9.0) {
            System.out.println("\nB");
        } else if (9.0 <= grade && grade < 10.0) {
            System.out.println("\nA");
        } else if (grade == 10.0) {
            System.out.println("\nExcellent!");
        } else {
            System.out.println("\nThe grade is not a valid value!");
        }
    }
}
