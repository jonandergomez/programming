
import java.util.*;
import java.io.*;

public class TestBinaryReading
{
    public static void main(String [] args)
    {
        try {

            File               file = new File("file-for-testing-1.dat");
            FileInputStream     fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream   ois = new ObjectInputStream(bis);

            String     str = ois.readUTF();
            int       year = ois.readInt();
            double deficit = ois.readDouble();

            ois.close();

            System.out.println(str);
            System.out.printf("The spanish deficit for %d is %f\n", year, deficit);
        }
        catch (IOException e)
        {
            System.err.println("Derived from IOException.");
            e.printStackTrace(System.err);
        }
        catch (Exception e)
        {
            System.err.println("Derived from Exception.");
            e.printStackTrace(System.err);
        }
    }
}
