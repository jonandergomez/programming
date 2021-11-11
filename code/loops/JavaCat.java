import java.util.*;
import java.io.*;

public class JavaCat
{
    public static void main(String [] args)
        throws Exception
    {
        String filename = args[0];

        Scanner f = new Scanner(new File(filename));

        while (f.hasNextLine()) {
            String line = f.nextLine();
            System.out.println(line);
        }
        f.close();
    }
}
