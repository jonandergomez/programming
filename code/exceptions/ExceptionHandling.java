import java.util.*;
import java.io.*;

public class ExceptionHandling
{
    static Scanner input = new Scanner(System.in);

    public static void main(String [] args)
    {
        try {
            int minutes = getMinutes();
        }
        catch(InputMismatchException ime) { /* Here the code. */ }
        catch(IOException ioe) { /* Here the code. */ }
        catch(Exception e) { /* Here the code. */ }
        finally { /* Code that will be executed always. */ }
    }
    public static int getMinutes()
        throws InputMismatchException, IOException
    {
        System.out.print("Minutes? ");
        int minutes = input.nextInt();
        if (minutes < 0 || minutes >= 60)
            throw new InputMismatchException("Minutes out of range!");

        return minutes;
    }
}
