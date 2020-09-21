import java.util.*;
import java.io.*;


public class ExampleOfStandardInputOutput
{
    private static Scanner input = new Scanner( System.in ).useLocale( Locale.US );


    public static void main( String [] args )
    {
        while( input.hasNext() ) {
            int value = input.nextInt();

            if ( (value % 2) == 0 ) {
                System.out.println( value );
            } else {
                System.err.println( value );
            }
        }
    }
}
