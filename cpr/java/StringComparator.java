
package es.upv.etsinf.sorting;

import java.util.Comparator;


public class StringComparator implements Comparator<String>
{
    public int compare( String s1, String s2 )
    {
        return s1.compareTo( s2 );
    }
}
