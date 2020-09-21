
import java.util.*;
import java.io.*;


public class StudentSelection 
{
    private static Scanner input = new Scanner( System.in ).useLocale( Locale.US );


    public static void main( String [] args )
    {
        int n = 25;
        
        if ( args.length > 0 ) n = Integer.parseInt( args[0] );

        Random r = new Random();

        System.out.println( "student number: " + (r.nextInt(n)+1) );
    }
}
