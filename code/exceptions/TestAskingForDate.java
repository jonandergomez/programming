import java.util.*;

/**
 * Class for testing the use of objects of the class <b>AskingForDate</b>.
 *
 * @author Jon Ander Gómez (jon@dsic.upv.es)
 * @version 1.0 (March 2012)
 */
public class TestAskingForDate
{
    /** <em>friendly</em> class variable for reading data from standard input */
    static Scanner input = new Scanner( System.in );

    /**
     * <code>main()</code> method for testing the use of objects of other classes.
     *
     * @param args Array of objects of class <b>String</b> with the list of
     *             command line arguments.
     */
    public static void main(String [] args)
    {
        AskingForDate afd = new AskingForDate(input);

        Date d = afd.readDate("Birth date");

        System.out.println("You were born " + d.toString());
    }
}
