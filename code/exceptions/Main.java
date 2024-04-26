import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;

/**
 * Class for testing the use of objects of the class <b>ClassesForAnObject</b>.
 * To compile and run this class you need the class <b>ClassesForAnObject</b>
 * to be previously compiled. Use the scripts <code>compile-examples.sh</code>
 * and <code>run-examples.sh</code> to compile the class <b>ClassesForAnObject</b>
 * before compiling this class. Then, run it to see the result.
 *
 * @author Jon Ander Gómez (jon@dsic.upv.es)
 * @version 1.0 (March 2012)
 */

public class Main
{
    /**
     * <code>main()</code> method for testing the use of objects of other classes.
     *
     * @param args Array of objects of class <b>String</b> with the list of command line arguments.
     */
    public static void main(String [] args)
    {
        AskingForDate afd = new AskingForDate(null);
        Date d = Calendar.getInstance().getTime();

        IllegalDateException ide = new IllegalDateException("A test");
        InputMismatchException ime = new InputMismatchException("A test");

        System.out.println();
        ClassesForAnObject.listOfClasses(afd);
        ClassesForAnObject.listOfClasses(d);
        ClassesForAnObject.listOfClasses(Calendar.getInstance());
        ClassesForAnObject.listOfClasses(ide);
        ClassesForAnObject.listOfClasses(ime);
    }
}