import java.util.*;
import java.io.*;


public class WorkWithFiles1
{
    public static void main(String [] args)
        throws FileNotFoundException
    {
         sumInt_v2("integers.in", "integers.out");
    }


    public static void sumInt(String fileIn, String fileOut)
        throws FileNotFoundException
    {
        Scanner input = new Scanner(new File(fileIn));
        PrintWriter output = new PrintWriter(
                               new BufferedOutputStream(
                                 new FileOutputStream(
                                   new File(fileOut))));
        int sum = 0;
        while (input.hasNext()) {
            try {
                int value = input.nextInt();
                output.println(value);
                sum += value;
            }
            catch(InputMismatchException e) {
                output.printf("(Error: %s)\n", input.next());
            }
        }
        output.println("Sum: " + sum);

        input.close();
        output.close();
    }

    public static void sumInt_v2(String fileIn, String fileOut)
    {
        Scanner input = null;
        PrintWriter output = null;
        try {
            input = new Scanner(new File(fileIn));
            output = new PrintWriter(fileOut);
            int sum = 0;
            while (input.hasNext()) {
                try {
                    int value = input.nextInt();
                    output.println(value);
                    sum += value;
                }
                catch(InputMismatchException e) {
                    output.printf("(Error: %s)\n", input.next());
                }
            }
            output.println("Sum: " + sum);
        }
        catch (FileNotFoundException e)
        {
            System.err.println("Impossible to open a file. Maybe it does not exist!");
            e.printStackTrace(System.err);
        }
        finally {
            if (input != null) input.close();
            if (output != null) output.close();
        }
    }
}
