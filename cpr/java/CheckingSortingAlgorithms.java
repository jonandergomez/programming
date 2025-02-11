
package es.upv.etsinf.sorting;

import java.util.*;
import es.upv.etsinf.strings.StringSearchCheck;

public class CheckingSortingAlgorithms
{
    private static Random r = new Random();

    public static void main(String [] args)
    {
        String [] list = new String [20];

        for (int i = 0; i < list.length; i++) list[i] = StringSearchCheck.randomFill(r, 15);

        show(list);

        // Arrays.sort( list, new StringComparator() );
        Arrays.sort(                    list, new ObjectComparator()); checkAscendingOrder(list); show(list); randomOrder(list);
        SortingAlgorithms.selectionSort(list, new ObjectComparator()); checkAscendingOrder(list); show(list); randomOrder(list);
        SortingAlgorithms.insertionSort(list, new ObjectComparator()); checkAscendingOrder(list); show(list); randomOrder(list);
        SortingAlgorithms.shellSort(    list, new ObjectComparator()); checkAscendingOrder(list); show(list); randomOrder(list);
        SortingAlgorithms.quickSort(    list, new ObjectComparator()); checkAscendingOrder(list); show(list); randomOrder(list);
        SortingAlgorithms.mergeSort(    list, new ObjectComparator()); checkAscendingOrder(list); show(list); randomOrder(list);
        SortingAlgorithms.heapSort(     list, new ObjectComparator()); checkAscendingOrder(list); show(list); randomOrder(list);
    }

    private static void show(String [] list)
    {
        System.out.println();
        for (String str : list) System.out.println(str);
        System.out.println();
    }
    private static void randomOrder(String [] list)
    {
        for (int i = 0; i < list.length; i++)
            swap(list, r.nextInt(list.length), r.nextInt(list.length));

        // show(list);
    }
    private static void swap(String [] list, int i, int j)
    {
        String aux = list[i]; list[i] = list[j]; list[j] = aux;
    }

    private static void checkAscendingOrder(String [] list)
    {
        for (int i = 1; i < list.length; i++)
            if (list[i - 1].compareTo(list[i]) > 0)
                throw new RuntimeException("Array not sorted in ascending order!");
    }
}
