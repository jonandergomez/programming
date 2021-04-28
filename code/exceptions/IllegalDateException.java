/**
 * User-defined exception.
 *
 * @author Jon Ander Gómez (jon@dsic.upv.es)
 * @version 1.0 (March 2012)
 */
public class IllegalDateException
    extends Exception
{
    /**
     * Constructor for preparing objects of this class.
     *
     * @param msg Error message describing the conditions that cause this exception.
     */
    public IllegalDateException(String msg)
    {
        // Call to one constructor of the superclass.
        super("Illegal date: " + msg);
    }
}
