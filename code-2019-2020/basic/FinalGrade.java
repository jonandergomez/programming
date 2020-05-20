
import java.util.*;
import java.io.*;


public class FinalGrade 
{
    private static Scanner input = new Scanner( System.in ).useLocale( Locale.US );


    public static void main( String [] args )
    {
        System.out.print( "\n PG1: " ); double pg1 = input.nextDouble();
        System.out.print( "\n PG2: " ); double pg2 = input.nextDouble();
        System.out.print( "\n LG1: " ); double lg1 = input.nextDouble();
        System.out.print( "\n LG2: " ); double lg2 = input.nextDouble();

        double fg = finalGrade( pg1, pg2, lg1, lg2 );

        System.out.printf( "\n The final grade is %.1f\n", fg );
    }

    static double finalGrade( double pg1, double pg2, double lg1, double lg2 )
    {
        pg1 = (4 <= pg2 && pg2 < 6) ? (pg1+pg2)/2 : ( (pg2 >= 6) ? pg2 : pg1 );

        if ( lg2 >= 6 ) {
            lg1 = lg2;
        } else if ( lg2 >= 4 ) {
            lg1 = (lg1+lg2)/2;
        }

        return 0.75*( 0.5*pg1+0.5*pg2 ) + 0.25*( 0.5*lg1+0.5*lg2 );
    }
}
