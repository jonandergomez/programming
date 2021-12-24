
import java.io.*;
import java.util.*;

public class Pluviometer
    implements Serializable
{
    private int []      daysPerMonth;
    private double [][] rain;

    public Pluviometer()
    {
        daysPerMonth = new int [13];
        daysPerMonth[ 1] = 31;
        daysPerMonth[ 2] = 28;
        daysPerMonth[ 3] = 31;
        daysPerMonth[ 4] = 30;
        daysPerMonth[ 5] = 31;
        daysPerMonth[ 6] = 30;
        daysPerMonth[ 7] = 31;
        daysPerMonth[ 8] = 31;
        daysPerMonth[ 9] = 30;
        daysPerMonth[10] = 31;
        daysPerMonth[11] = 30;
        daysPerMonth[12] = 31;

        rain = new double [13][32];
    }

    public void save(String filename)
    {
        try {
            File file = new File(filename);
            FileOutputStream fos = new FileOutputStream(file);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);

            oos.writeObject(this);
            oos.close();
        }
        catch (FileNotFoundException fnfe)
        {
            fnfe.printStackTrace(System.err);
            System.exit(1);
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace(System.err);
            System.exit(1);
        }
    }

    public static Pluviometer load(String filename)
    {
        Pluviometer p = null;

        try {
            File file = new File(filename);
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);

            Object o = ois.readObject();

            if (o instanceof Pluviometer)
                p = (Pluviometer)o;
            else
                throw new ClassNotFoundException("Invalid class loaded from file!");
        }
        catch (ClassNotFoundException|FileNotFoundException e)
        {
            e.printStackTrace(System.err);
            System.exit(1);
        }
        catch (IOException e)
        {
            e.printStackTrace(System.err);
            System.exit(1);
        }

        return p;
    }
}
