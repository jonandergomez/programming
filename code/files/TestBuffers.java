import java.io.*;
import java.util.*;


public class TestBuffers
{
    public static void main(String [] args)
    {
        String filename = null;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--file")) {
                filename = args[i + 1];
            }
        }

        if (filename != null) {
            Scanner sf = null;
            try {
                sf = new Scanner(
                        new BufferedInputStream(
                            new FileInputStream(
                                new File(filename))));

                while (true) {
                    String line = sf.nextLine(); //.trim();
                    System.out.println(line);
                }

            }
            // catch (EOFException e)
            catch (NoSuchElementException e)
            {
                System.out.println("*** The end-of-file was reached as expected. All is OK!");
            }
            catch (FileNotFoundException e)
            {
                e.printStackTrace(System.err);
            }
            finally {
                if (sf != null)
                    sf.close();
            }
        }
    }
}
