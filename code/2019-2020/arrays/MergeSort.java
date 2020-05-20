
import java.util.*;
import java.io.*;


public class MergeSort 
{
    private static Scanner input = new Scanner( System.in ).useLocale( Locale.US );


    public static void main( String [] args )
    {
    }

    public static int [] naturalMerge_victor( int [] a, int [] b )
    {
        int i=0, j=0, k=0;
        int [] c = new int [a.length+b.length];

        while( k < c.length ) {
            
            if ( j >= b.length  ||  a[i] <= b[j] ) {

                c[k++] = a[i++];

            } else {
                
                c[k++] = b[j++];
            }
        }

        return c;
    }
    public static int [] naturalMerge_jon( int [] a, int [] b )
    {
        int i=0, j=0, k=0;
        int [] c = new int [a.length+b.length];

        while( i < a.length && j < b.length ) {
            
            if ( a[i] <= b[j] ) {
                c[k++] = a[i++];
            } else {
                c[k++] = b[j++];
            }
        }

        while( i < a.length ) c[k++] = a[i++];
        while( j < b.length ) c[k++] = b[j++];

        return c;
    }
    public static void naturalMerge( int [] left, int [] right, int [] dest )
    {
        if ( dest.length != left.length + right.length )
            throw new Error( "natural merge cannot be applied!" );

        int i=0, j=0, k=0;

        while( i < left.length && j < right.length ) {
            
            if ( left[i] <= right[j] ) {
                dest[k++] = left[i++];
            } else {
                dest[k++] = right[j++];
            }
        }

        while( i < left.length ) dest[k++] = left[i++];
        while( j < right.length ) dest[k++] = right[j++];
    }

    private static void swap( int [] v, int i, int j )
    {
        int temp = v[i];
        v[i] = v[j];
        v[j] = temp;
    }
    private static int [] extract( int [] v, int from, int to )
    {
        int [] temp = new int [ to-from ];

        for( int i=0; i < temp.length; i++ ) temp[i] = v[from+i];

        return temp;
    }

    public static void mergeSort( int [] v )
    {
        if ( v.length > 2 ) {
            
            int k = v.length / 2;
            int [] left  = extract( v, 0, k );
            int [] right = extract( v, k, v.length );

            mergeSort( left );
            mergeSort( right );

            naturalMerge( left, right, v );

        } else if ( v.length == 2 ) {

            if ( v[0] > v[1] ) swap( v, 0, 1 );
        }
    }
}
