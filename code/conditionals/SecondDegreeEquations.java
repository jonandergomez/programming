

import java.util.*;
import java.io.*;


public class SecondDegreeEquations 
{
    private static Scanner input = new Scanner( System.in ).useLocale( Locale.US );


    public static void main( String [] args )
    {
        double a, b, c;

        System.out.print( "\n Enter the values for A, B and C: " );
        a = input.nextDouble();
        b = input.nextDouble();
        c = input.nextDouble();

        if ( a == 0.0 ) {
            if ( b == 0.0 ) {
                if ( c == 0.0 ) {
                    System.out.printf( "There are infinite solutions!\n" );
                } else {
                    System.out.printf( "ERROR!\n" );
                }
            } else {
                System.out.printf( "One degree equation x = %.6f\n", -c/b );
            }
        } else {
            if ( b*b >= 4*a*c ) {
                if ( b*b == 4*a*c ) {
                    double x = -b / (2*a);
                    System.out.printf( "Double solution x = %.6f\n", x );
                } else {
                    double sqrt = Math.sqrt( b*b - 4*a*c );
                    double x1 = (-b+sqrt) / (2*a);
                    double x2 = (-b-sqrt) / (2*a);
                    System.out.printf( "x1 = %.6f\n", x1 );
                    System.out.printf( "x2 = %.6f\n", x2 );
                }
            } else {
                double sqrt = Math.sqrt( 4*a*c - b*b );
                double real = -b / (2*a);
                double imag = sqrt / (2*a);
                System.out.printf( "x1 = %.6f%+.6fi\n", real, imag );
                System.out.printf( "x2 = %.6f%+.6fi\n", real, -imag );
            }
        }
    }
}
