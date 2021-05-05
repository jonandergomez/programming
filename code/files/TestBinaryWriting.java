
import java.util.*;
import java.io.*;

public class TestBinaryWriting
{
    public static void main(String [] args)
    {
        try {

            File              file = new File("file-for-testing-1.dat");
            FileOutputStream   fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeUTF("Hello crazy world!");
            oos.writeInt(2016);
            oos.writeDouble(21.4);

            oos.close();
        }
        catch(IOException e)
        {
            e.printStackTrace(System.err);
        }
        catch(Exception e)
        {
            e.printStackTrace(System.err);
        }
    }
}
