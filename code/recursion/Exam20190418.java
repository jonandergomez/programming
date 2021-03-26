
public class Exam20190418
{
    public static void main(String [] args)
    {
        String [] array = {"ship", "bus", "train", "moto", "bicycle", "aircraft"};

        System.out.println("contents of the array:");
        for (String s : array) System.out.print(" " + s);
        System.out.println();
        System.out.println();

        for (int n = 3; n <= 10; n++) {
            System.out.printf("%3d words of length %3d were found in the array.\n",
                        countWordsOfLengthN(array, n), n);
        }
    }

    /*
        preconditions:
            v != null
            n >= 0
            0 <= index < v.length

        point (b)
        trivial case:
            when index is less than zero, what means the array is empty

            return 0

        general case:
            index >= 0 :: that indicates the size of the subarray pending
                          to be explored is (index+1)

            cwofn(v, n, index):
                1 + cwofn(v, n, index-1)    if len(a[index]) == n
                cwofn(v, n, index - 1)      otherwise
    */
    private static int countWordsOfLengthN(String [] v, int n, int index) // point (a)
    {
        // point (c)
        if (index < 0)
            return 0;
        else if (v[index].length() == n)
            return 1 + countWordsOfLengthN(v, n, index - 1);
        else
            return countWordsOfLengthN(v, n, index - 1);
    }

    // wrapper method
    private static int countWordsOfLengthN(String [] v, int n)
    {
        return countWordsOfLengthN(v, n, v.length - 1); // point (d)
    }
}
