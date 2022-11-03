import java.io.*;

public class TestPrintWriter
{
    public static void main(String [] args)
    {
        String fileName = "file2.txt";

        try {
            // Opens the file
            PrintWriter pw = new PrintWriter(new File(fileName));

            pw.print("El veloz murciélago hindú");
            pw.println(" comía feliz cardillo y kiwi");
            pw.println(4.815162342);

            // Closes the file
            pw.close();
        }
        catch (FileNotFoundException e) {
            System.err.println("Error opening or creating " + fileName);
        }
    }
}