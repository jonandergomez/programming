import java.util.*;

public class Problem22
{
    public static void main(String [] args)
    {
        int [] v = new int [20];

        Random r = new Random();

        for (int i = 0; i < v.length; i++) v[i] = r.nextInt(20);

        int sum = recursiveSum(v, v.length - 1);
        System.out.printf("Sum of all the elements of v is equal to %d\n", sum);

        int max = recursiveMax(v, v.length - 1);
        System.out.printf("Max in v is %d\n", max);

        for (int value = 0; value < 20; value++) {
            int count = recursiveCount(v, v.length - 1, value);
            if (count > 0)
                System.out.printf("%3d appears %3d times in the array.\n", value, count);
            else
                System.out.printf("%3d DOES NOT appear in the array.\n", value);
        }

        int firstNonZero = firstNonNull(v, 0);
        int lastNonZero = lastNonNull(v, v.length - 1);
        int trailingZeroes = countTrailingZeros(v, v.length - 1);

        show(v);
        reverse(v, 3, 7);
        show(v);

        System.out.printf("first non zero at position %d\n", firstNonZero);
        System.out.printf("last  non zero at position %d\n", lastNonZero);
        System.out.printf("found a total of %d trailing zeroes\n", trailingZeroes);


        boolean isSorted = checkAscendingOrder(v, v.length - 1);
        if (isSorted)
            System.out.println("The array is sorted in ascending order.");
        else
            System.out.println("The array is NOT sorted in ascending order.");
        Arrays.sort(v);
        isSorted = checkAscendingOrder(v, v.length - 1);
        if (isSorted)
            System.out.println("The array is sorted in ascending order.");
        else
            System.out.println("The array is NOT sorted in ascending order.");

        show(v);
        reverse(v, 7, 14);
        show(v);

    }

    private static void show(int [] v)
    {
        System.out.println();
        for (int x : v) System.out.print(" " + x);
        System.out.println();
        System.out.println();
    }

    /*
        precondition:
            0 <= left <= right < v.length

        trivial case:
            left >= right  i.e. right-left+1 <= 1

        general case:
            left < right   i.e. right-left+1 > 1
    */
    private static void reverse(int [] v, int left, int right)
    {
        if (left < right) {
            int temp = v[left];
            v[left] = v[right];
            v[right] = temp;

            reverse(v, left + 1, right - 1);
        }
    }

    /*
        sum(v) :  0                             if |v| == 0
                  v[|v|-1] + sum(v[0:|v|-1])    if |v| > 0
    */
    private static int recursiveSum(int [] v, int index)
    {
        if (index < 0) return 0;

        return v[index] + recursiveSum(v, index - 1);
    }

    /*
        max(v) :  -infinity                         if |v| == 0
                  max(v[|v|-1], max(v[0:|v|-1])     if |v| > 0
    */
    private static int recursiveMax(int [] v, int index)
    {
        if (index < 0) return Integer.MIN_VALUE;

        return Integer.max(v[index], recursiveMax(v, index - 1));
    }

    /*
        count(v, x):
            0                           if |v| == 0
            count(v[0:|v|-1], x)        if |v| > 0 and last value on v is not x
            1 + count(v[0:|v|-1], x)    if |v| > 0 and last value on v is x
    */
    private static int recursiveCount(int [] v, int index, int value)
    {
        if (index < 0)
            return 0;
        else if (v[index] == value)
            return 1 + recursiveCount(v, index - 1, value);
        else
            return recursiveCount(v, index - 1, value);
    }

    /*
        precondition:
            0 <= index < v.length

        sorted(v, index):
            true                    if |v| <= 1
            false                   if |v| >= 2 and v[index-1] > v[index]
            sorted(v[0:index-1])    if |v| >= 2 and v[index-1] <= v[index]


        {7, 7, 7, 7, 7, 7, 7}
            use v[index - 1] >  v[index] to return true in this example
            use v[index - 1] >= v[index] to return false in this example
    */
    private static boolean checkAscendingOrder(int [] v, int index)
    {
        if (index <= 1)
            return true;
        else if (v[index - 1] > v[index]) // use >= if you want to check strictly increasing
            return false;
        else
            return checkAscendingOrder(v, index - 1);
    }

    /*
        precondition:
            0 <= index < v.length

        fnn(v, index):
            -1                  if index == v.length
            index               if v[index] != 0
            fnn(v, index+1)     if v[index] == 0
    */
    private static int firstNonNull(int [] v, int index)
    {
        if (index >= v.length)  return -1;
        else if (v[index] != 0) return index;
        else                    return firstNonNull(v, index + 1);
    }
    /*
        precondition:
            0 <= index < v.length

        fnn(v, index):
            -1                  if index < 0
            index               if v[index] != 0
            fnn(v, index-1)     if v[index] == 0
    */
    private static int lastNonNull(int [] v, int index)
    {
        if (index < 0)          return -1;
        else if (v[index] != 0) return index;
        else                    return lastNonNull(v, index - 1);
    }

    /*
        precondition:
            0 <= index < v.length

        count(v, index):
            0                       if index < 0
            0                       if v[index] != 0
            1 + count(v, index-1)   if v[index] == 0
    */
    private static int countTrailingZeros(int [] v, int index)
    {
        if (index < 0)          return 0;
        else if (v[index] != 0) return 0;
        else                    return 1 + countTrailingZeros(v, index - 1);
    }
}
