import java.io.*;
import java.util.Scanner;

public class TestScanner
{
    public static void main(String [] args)
    {
        System.out.println();
        System.out.println(" We are going to read three integers and a text line.");
        System.out.println();

        Scanner scanner = null;

        try{
            scanner = new Scanner(new File("things.txt"));
        }
        catch (FileNotFoundException ex) {
            System.err.println("File doesn’t exist. " + ex.getMessage());
            System.exit(0);
        }

        int n1 = scanner.nextInt();
        int n2 = scanner.nextInt();
        int n3 = scanner.nextInt();
        scanner.nextLine();

        String line = scanner.nextLine();

        System.out.println("Numbers are: " + n1 + ", " + n2 + ", " + n3);
        System.out.println("Line is: " + line);
        scanner.close();
    }
}