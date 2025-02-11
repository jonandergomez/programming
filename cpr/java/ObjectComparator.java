
package es.upv.etsinf.sorting;

import java.util.Comparator;


public class ObjectComparator implements Comparator<Object>
{
    public int compare(Object o1, Object o2)
    {
        if (o1 == null) throw new NullPointerException("First argument is null!");
        if (o2 == null) throw new NullPointerException("Second argument is null!");

        if ((o1 instanceof String && o2 instanceof String) == false)
            throw new ClassCastException("Objects are incompatible for comparison!");


        String s1 = (String)o1;
        String s2 = (String)o2;

        return s1.compareTo(s2);
    }
}
