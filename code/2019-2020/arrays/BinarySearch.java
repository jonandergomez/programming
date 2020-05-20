
import java.util.*;
import java.io.*;


public class BinarySearch 
{
    int iterative( int [] a, int x )
    {
        int left = 0, right = a.length-1;

        while( left <= right ) {
            int k = (left+right) / 2;

            if ( a[k] == x ) {
                return k;
            } else if ( a[k] < x ) {
                left = k+1;
            } else /* if ( a[k] > x ) */  {
                right = k-1;
            }
        }

        return -1;
    }

    int recursive( int [] a, int x )
    {
        return recursive( a, x, 0, a.length-1 );
    }
    int recursive( int [] a, int x, int left, int right )
    {
        if ( left > right ) {
            return -1;
        } else {

            int k = (left+right) / 2;

            if ( a[k] == x ) {
                return k;
            } else if ( a[k] < x ) {
                return recursive( a, x, k+1, right );
            } else /* if ( a[k] > x ) */  {
                return recursive( a, x, left, k-1 );
            }
        }
    }
}
