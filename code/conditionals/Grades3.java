
import java.util.*;
import java.io.*;


public class Grades3
{
    private static Scanner input = new Scanner(System.in).useLocale(Locale.US);

    public static void main(String [] args)
    {
        System.out.print("\n\n Enter your grade: ");
        double grade = input.nextDouble();

        switch ((int)grade) {
            case 0 :
            case 1 :
            case 2 :
                System.out.println("\nGo home and never come back here!");
                break;
            case 3 :
                System.out.println("\nSee you next year!");
                break;
            case 4 :
                System.out.println("\nI will give you another opportunity!");
                break;
            case 5 :
            case 6 :
                System.out.println("\nPassed!");
                break;
            case 7 :
            case 8 :
                System.out.println("\nB");
                break;
            case 9 :
                System.out.println("\nA");
                break;
            case 10 :
                System.out.println("\nExcellent!");
                break;
            default:
                System.out.println("\nThe grade is not a valid value!");
        }
    }
}
