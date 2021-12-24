import java.util.*;
import java.io.*;

public class WorkWithFiles2
{
    public static void main(String [] args)
        throws FileNotFoundException
    {
         checkTable("atp.in", "errors_found.txt");
    }

    public static void checkTable(String fileIn, String fileOut)
        throws FileNotFoundException
    {
        Scanner input = new Scanner(new File(fileIn));
        PrintWriter output = new PrintWriter(fileOut);

        int counter = 0;
        while (input.hasNext()) {
            try {
                String line = input.nextLine();
                String [] tokens = line.split("([ \t])+");
                ++counter;

                if (tokens.length != 4) {
                    throw new Exception(String.format("Error line %d: Unexpected number of columns.", counter));
                } else {
                    String lastName = tokens[0];
                    int age = Integer.parseInt(tokens[1]);
                    if (age <= 0)
                        throw new Exception(String.format("Error line %d: Negative value.", counter));
                    int points = Integer.parseInt(tokens[2]);
                    int championships = Integer.parseInt(tokens[3]);
                }
            }
            catch (NumberFormatException e) {
                output.printf("Error line %d: Invalid format for an integer.\n", counter);
            }
            catch (Exception e) {
                output.println(e.getMessage());
            }
        }
        input.close();
        output.close();
    }
}
