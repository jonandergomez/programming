
package es.upv.etsinf.sorting;

import java.util.Arrays;
import java.util.Comparator;

public class SortingAlgorithms
{
    // O(n*n)
    public static void selectionSort(Object [] a, ObjectComparator c)
    {
        selectionSort(a, 0, a.length, c);
    }
    public static void selectionSort(Object [] a, int from, int to, ObjectComparator c)
    {
        for (int i = from; i < to; i++) {
            int k = i;
            for (int j = i + 1; j < to; j++) {
                if (c.compare(a[k], a[j]) > 0) k = j;
            }
            swap(a, i, k);
        }
    }

    // O(n*n)
    public static void insertionSort(Object [] a, ObjectComparator c)
    {
        insertionSort(a, 0, a.length, c);
    }
    public static void insertionSort(Object [] a, int from, int to, ObjectComparator c)
    {
        for (int i = from + 1; i < to; i++) {

            Object aux = a[i];
            int j = i;

            while (j > from && c.compare( aux, a[j-1] ) < 0) {
                a[j] = a[j - 1];
                --j;
            }
            a[j] = aux;
        }
    }

    // O(n*log(n))
    public static void quickSort(Object [] a, ObjectComparator c)
    {
        quickSort(a, 0, a.length - 1, c);
    }
    public static void quickSort(Object [] a, int from, int to, ObjectComparator c)
    {
        if (to - from > 15) {

            // Select three existing items in the array
            Object o1 = a[from],
                   o2 = a[(from + to) / 2],
                   o3 = a[to];

            // Choose the median of the selected items
            if (c.compare(o2, o3) > 0) { Object aux = o2; o2 = o3; o3 = aux; }
            if (c.compare(o1, o2) > 0) { Object aux = o1; o1 = o2; o2 = aux; }
            if (c.compare(o2, o3) > 0) { Object aux = o2; o2 = o3; o3 = aux; }

            Object pivot = o2;

            int left = from, right = to;
            while (left <= right) {

                while ( left < to   && c.compare(a[left],  pivot) <= 0) left++;
                while (right > from && c.compare(a[right], pivot) >= 0) right--;

                if (left <= right) {
                    if (left < right) swap(a, left, right);
                    ++left; --right;
                }
            }

            //if ( c.compare( pivot, a[right] ) != 0 ) throw new RuntimeException( "The pivot is not in the correct position!" );

            quickSort(a, from, right, c);
            quickSort(a, left, to, c);

        } else if (from < to) {
            insertionSort(a, from, to + 1, c);
        }
    }

    // O(n*log(n)*log(n))
    public static void shellSort(Object [] a, ObjectComparator c)
    {
        shellSort(a, 0, a.length, c);
    }
    public static void shellSort(Object [] a, int from, int to, ObjectComparator c)
    {
        for (int step = (to - from) / 2; step > 0; step = (step == 2) ? 1 : (int)(step / 2.2)) {

            for (int i = from + step; i < to; i++) {

                Object aux = a[i];
                int j = i;

                while (j >= from + step  &&  c.compare(aux, a[j - step]) < 0) {
                    a[j] = a[j - step];
                    j -= step;
                }
                a[j] = aux;
            }
        }
    }

    // O(n*log(n))
    public static void mergeSort(Object [] a, ObjectComparator c)
    {
        mergeSort(a, 0, a.length - 1, c);
    }
    public static void mergeSort(Object [] a, int from, int to, ObjectComparator c)
    {
    // Put special attention to the fact that 'to' in this algorithm is a valid index for the array
    // And 'm' is the index for the first item in the right half of the array slice delimited by 'from' and 'to'

        if (to - from + 1 > 2) {

            int m = (from + to + 1) / 2;
            mergeSort(a, from, m - 1, c);
            mergeSort(a, m, to, c);
            naturalMerge(a, from, m, to, c);

        } else if (to > from) {

            if (c.compare(a[from], a[to]) > 0) swap(a, from, to);
        }
    }
    public static void naturalMerge(Object [] a, int from, int m, int to, ObjectComparator c)
    {
        Object [] aux = new Object [to - from + 1];

        int i = from, j = m, k = 0;
        while (i < m && j <= to) {
            if (c.compare(a[i], a[j]) <= 0) {
                aux[k++] = a[i++];
            } else {
                aux[k++] = a[j++];
            }
        }
        while(i <  m ) aux[k++] = a[i++];
        while(j <= to) aux[k++] = a[j++];

        for (i = 0; i < aux.length; i++) a[from + i] = aux[i];
    }

    // O(n*log(n))
    public static void heapSort(Object [] a, ObjectComparator c)
    {
        // Convert the array into a max-heap
        buildMaxHeap(a, c);

        for (int i = a.length - 1; i >= 1; --i) {
            swap(a, 0, i);
            maxHeapify(a, c, 0, i);
        }
    }
    private static void buildMaxHeap(Object [] a, ObjectComparator c)
    {
        for (int i = a.length / 2; i >= 0; --i) maxHeapify(a, c, i, a.length);
    }
    private static void maxHeapify(Object [] a, ObjectComparator c, int pos, int heapSize)
    {
        pos++; // Heap data structure uses index in the range [1,n] instead of [0,n-1]

        int left  = 2 * pos;
        int right = left + 1;

        int largest = pos;

        if (left  <= heapSize  &&  c.compare(a[left  - 1], a[largest - 1]) > 0) largest = left;
        if (right <= heapSize  &&  c.compare(a[right - 1], a[largest - 1]) > 0) largest = right;

        if (largest != pos) {
            swap(a, pos - 1, largest - 1);
            maxHeapify(a, c, largest - 1, heapSize);
        }
    }

    private static void swap(Object [] a, int i, int j)
    {
        Object aux = a[i]; a[i] = a[j]; a[j] = aux;
    }

    // A particular case of the Bucket Sort algorithm, O(n+k)
    public static void countingSort(int [] a)
    {
        int min = a[0], max = a[0];
        for (int i = 1; i < a.length; i++) {
            min = Math.min(min, a[i]);
            max = Math.max(max, a[i]);
        }

        if (max - min <= 10000000) { // To use 40MB of RAM at most

            int [] counter = new int [max - min + 1];

            for (int i = 0; i < a.length; i++) counter[a[i] - min]++;

            int k = 0;
            for (int i = 0; i < counter.length; i++) {
                while (-counter[i] > 0) a[k++] = i + min;
            }
        } else {
            Arrays.sort(a);
        }
    }
}
