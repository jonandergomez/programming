
import java.util.*;
import java.io.*;


public class GameManager 
{
    public static void main(String [] args)
    {
        TimeInstant t = new TimeInstant();

        SaveGame sg = new SaveGame("SCES", 50760, 1, t);

        System.out.println(sg.toHHMM());

        System.out.println(t.toString());

        // System.out.println(t.toString().compareTo(sg.toHHMM()));

        int rc = t.toString().compareTo(sg.toHHMM());

        if (rc == 0) {
            System.out.printf("Both strings are equal!  %s and %s\n", sg.toHHMM(), t.toString());
        } else if (rc < 0) {
            System.out.printf("The strings are NOT equal!  %s goes before %s\n", t.toString(), sg.toHHMM());
        } else {
            System.out.printf("The strings are NOT equal!  %s goes after %s\n", t.toString(), sg.toHHMM());
        }
    }
}
