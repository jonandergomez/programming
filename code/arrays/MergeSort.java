
import java.util.*;
import java.io.*;


public class MergeSort
{
    private static Scanner input = new Scanner(System.in).useLocale(Locale.US);


    public static void main(String [] args)
    {
    }

    public static void selectionSort(int [] a)
    {
        // at each iteration of the outer loop the sorted subarray
        // a[0..i] is increased by adding a new element
        for (int i = 0; i < a.length; i++) {
            // search for the minimum in a[i .. a.length - 1]
            int k = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[k]) k = j;
            }
            // store the minimum in a[i] if not already there
            if (k != i) swap(a, k, i);
        }
    }

    public static void insertionSort(int [] a)
    {
        // at each iteration of the outer loop the sorted subarray
        // a[0..i] is increased by adding a new element
        for (int i = 1; i < a.length; i++) {
            // the new element is inserted into its correct position in
            // order to guarantee it the sorte subarray a[0..i] is sorted
            // temporary save a[i] in x
            int j = i, x = a[i];
            // look for the correct position of x in a[0..i] by shifting
            // one position to the right all the values in a[0..i] greater
            // than x (i.e., a[i])
            while (j > 0 && x < a[j - 1]) {
                a[j] = a[j - 1];
                j--;
            }
            // insert x in a[j], where j is the position that corresponds to x within a[0..i]
            a[j] = x;
        }
    }

    public static int [] naturalMergeOneLoop(int [] a, int [] b)
    {
        int i = 0, j = 0, k = 0;
        int [] c = new int [a.length + b.length];

        while (k < c.length) {
            if (j >= b.length  ||  a[i] <= b[j])    c[k++] = a[i++];
            else                                    c[k++] = b[j++];
        }

        return c;
    }
    public static int [] naturalMergeTwoLoops(int [] a, int [] b)
    {
        int i = 0, j = 0, k = 0;
        int [] c = new int [a.length + b.length];

        while (i < a.length  &&  j < b.length) {
            if (a[i] <= b[j])   c[k++] = a[i++];
            else                c[k++] = b[j++];
        }
        while (i < a.length)    c[k++] = a[i++];
        while (j < b.length)    c[k++] = b[j++];

        return c;
    }
    public static void naturalMerge(int [] left, int [] right, int [] dest)
    {
        if (dest.length != left.length + right.length)
            throw new Error("natural merge cannot be applied!");

        int i = 0, j = 0, k = 0;

        while (i < left.length  &&  j < right.length) {
            if (left[i] <= right[j])    dest[k++] =  left[i++];
            else                        dest[k++] = right[j++];
        }
        while (i <  left.length)        dest[k++] =  left[i++];
        while (j < right.length)        dest[k++] = right[j++];
    }

    private static void swap(int [] v, int i, int j)
    {
        int temp = v[i];
        v[i] = v[j];
        v[j] = temp;
    }
    private static int [] extract(int [] v, int from, int to)
    {
        int [] temp = new int [to - from];

        for (int i = 0; i < temp.length; i++) temp[i] = v[from + i];

        return temp;
    }

    public static void mergeSort(int [] v)
    {
        if (v.length > 2) {
            // splitting of the array v into two halves
            int k = v.length / 2;
            int [] left  = extract(v, 0, k);
            int [] right = extract(v, k, v.length);

            // sort each half recursively
            mergeSort(left);
            mergeSort(right);

            // merge both sorted halves and store the result in v
            naturalMerge(left, right, v);

        } else if (v.length == 2) {
            // swap the two values of an array of size 2 if were not already sorted in ascending order
            if (v[0] > v[1]) swap(v, 0, 1);
        }
        // if v.length == 1 the array of size 1 is sorted by definition, so, nothing to do
    }
}
