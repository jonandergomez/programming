
import java.io.*;
import java.util.Scanner;

class TextWritingReadingIntegers
{
    public static void main(String [] args)
    {
        String fileName = "file1.txt";
        try {
            //PrintWriter pw = new PrintWriter(new File(fileName));
            PrintWriter pw = new PrintWriter(
                                new BufferedOutputStream(
                                    new FileOutputStream(
                                        new File(fileName))));

            for (int i = 0; i < 10 ; i++) pw.println("     " + i);

            pw.close();

            //Scanner scanner = new Scanner(new File(fileName));
            Scanner scanner = new Scanner(
                                new BufferedInputStream(
                                    new FileInputStream(
                                        new File(fileName))));

            while (scanner.hasNext()) {
                System.out.println("Read value: "+scanner.nextInt());
            }
            scanner.close();

        }
        catch (FileNotFoundException e) {
            System.err.println(" ERROR when opening file: " + fileName);
        }
    }
}
