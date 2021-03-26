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
        System.out.println("*****************************************");
        for (int value = 5; value <= 20; value += 5) {
            int count = countLowerThanX(v, value, v.length - 1);
            if (count > 0)
                System.out.printf("%3d values lower than %3d were found in the array.\n", count, value);
            else
                System.out.printf("There are no values lower than %d in the array.\n", value);
        }
        System.out.println("*****************************************");
        int count = countOddsInEvenPositions(v, v.length - 1);
        if (count > 0)
            System.out.printf("A total of %d odd numbers were found in even positions of the array.\n", count);
        else
            System.out.printf("No odd numbers were found in even positions of the array.\n");

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
        duplicateValues(v, 7, 14);
        show(v);

        int b = 5 * v.length + r.nextInt(20 * v.length);
        if (isSumEqualToB(v, b, 0, v.length - 1))
            System.out.printf("sum(v) is equal to %d\n", b);
        else
            System.out.printf("sum(v) is NOT equal to %d\n", b);
        b = 0;
        for (int x : v) b += x;
        if (isSumEqualToB(v, b, 0, v.length - 1))
            System.out.printf("sum(v) is equal to %d\n", b);
        else
            System.out.printf("sum(v) is NOT equal to %d\n", b);

        int pos = subsequenceOfConsecutives(v, 0, v.length - 1);
        show(v);
        if (pos >= 0)
            System.out.printf("A sequence of three or more consecutive numbers starts at position %d of the array.\n", pos);
        else
            System.out.printf("No sequence of three or more consecutive numbers was found in the array.\n");
        Arrays.sort(v);
        pos = subsequenceOfConsecutives(v, 0, v.length - 1);
        show(v);
        if (pos >= 0)
            System.out.printf("A sequence of three or more consecutive numbers starts at position %d of the array.\n", pos);
        else
            System.out.printf("No sequence of three or more consecutive numbers was found in the array.\n");
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
        precondition:
            0 <= left <= right < v.length

        trivial cases:
        left >  right        do nothing
        left == right        duplicate value at left

        general case:
            left < right     duplicate values at left and right
    */
    private static void duplicateValues(int [] v, int left, int right)
    {
        if (left < right) {
            v[left]  *= 2;
            v[right] *= 2;
            duplicateValues(v, left + 1, right - 1);
        } else if (left == right) {
            v[left]  *= 2;
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
        if (index < 0)              return 0;
        else if (v[index] == value) return recursiveCount(v, index - 1, value) + 1;
        else                        return recursiveCount(v, index - 1, value);
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

    /*
        precondition:
            0 <= index < v.length

        isSumEqualToB(v, b, sum, index):
            true                                            if index < 0 and sum == b
            false                                           if index < 0 and sum != b
            false                                           if index >= 0 and sum > b
            isSumEqualToB(v, b, sum + v[index], index-1)    if index >= 0
    */
    private static boolean isSumEqualToB(int [] v, int b, int sum, int index)
    {
        if (index < 0)
            return sum == b; // after adding the first element of the array return if sum == b
        else if (sum > b)
            return false; // if before reaching the trivial case the sum is greater than b, stop
        else
            return isSumEqualToB(v, b, sum + v[index], index - 1);
    }

    /*
        precondition:
            0 <= index < v.length

        countLowerThanX(v, x, index):
            0                                       if index < 0
            1 + countLowerThanX(v, x, index-1)      if index >= 0 and v[index] < x
            countLowerThanX(v, x, index-1)          if index >= 0 and v[index] >= x
    */
    private static int countLowerThanX(int [] v, int x, int index)
    {
        if (index < 0)          return 0;
        else if (v[index] < x)  return countLowerThanX(v, x, index - 1) + 1;
        else                    return countLowerThanX(v, x, index - 1);
    }

    /*
        precondition:
            0 <= index < v.length

        oddsInEvenPositions(v, index):
            0                                       if index <  0
            1 + oddsInEvenPositions(v, index-1)     if index >= 0 and v[index] is odd and index is even
            oddsInEvenPositions(v, index-1)         otherwise
    */
    private static int countOddsInEvenPositions(int [] v, int index)
    {
        if (index < 0)                                  return 0;
        else if (index % 2 == 0  &&  v[index] % 2 != 0) return countOddsInEvenPositions(v, index - 1) + 1;
        else                                            return countOddsInEvenPositions(v, index - 1);
    }


    /*
        precondition:
            0 <= i < v.length

        subsequenceOfConsecutives(v, count, index):
            -1                                                  if i < 0 -- because the empty array has no sequences of three consecutive numbers
            -1                                                  if i == 0 and count < 3 -- no remaining elements to reach count >= 3
            0                                                   if i == 0 and count >= 3
            min(i, subsequenceOfConsecutives(v, count+1, i-1))  if i >= 0 and count >= 3 and v[i-1]+1 == v[i]
            min(i, subsequenceOfConsecutives(v, 0, i-1))        if i >= 0 and count >= 3 and v[i-1]+1 != v[i]
            subsequenceOfConsecutives(v, 2, i-1))               if i >= 0 and count == 0 and v[i-1]+1 == v[i]
            subsequenceOfConsecutives(v, count+1, i-1))         if i >= 0 and count >  0 and v[i-1]+1 == v[i]
            subsequenceOfConsecutives(v, 0, i-1))               otherwise, i.e., if i >= 0 and v[i-1]+1 != v[i]
    */
    private static int subsequenceOfConsecutives(int [] v, int count, int i)
    {
        if (i < 0)
            return -1;

        else if (i == 0)
            return (count >= 3) ? 0 : -1; // to guarantee the correct usage of v[i-1] in other cases

        else if (count >= 3 && v[i - 1] + 1 == v[i]) {
            int pos = subsequenceOfConsecutives(v, count + 1, i - 1);
            return (pos < 0) ? i : Integer.min(pos, i); // do not compute the min(pos,i) when pos == -1

        } else if (count >= 3 && v[i - 1] + 1 != v[i]) {
            int pos = subsequenceOfConsecutives(v, 0, i - 1);
            return (pos < 0) ? i : Integer.min(pos, i); // do not compute the min(pos,i) when pos == -1

        } else if (v[i - 1] + 1 == v[i])
            return subsequenceOfConsecutives(v, (count == 0) ? 2 : count + 1, i - 1);

        else
            return subsequenceOfConsecutives(v, 0, i - 1);
    }
}
