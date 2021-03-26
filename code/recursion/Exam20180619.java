
public class Exam20180619
{
    public static void main(String [] args)
    {
        double [] a = {-7.4, -1.3, 0.0, 1.8, 2.3, 3.6};
        double [] b = {-7.4, -1.3, 1.8, 2.6, 3.6};

        System.out.print("contents of array 'a':");
        for (double d : a) System.out.print(" " + d);
        System.out.println();
        System.out.print("contents of array 'a':");
        for (double d : b) System.out.print(" " + d);
        System.out.println();
        System.out.println();

        int pos = findIntercept(a);
        if (pos >= 0)
            System.out.printf("Intercept found at position %3d in array a.\n", pos);
        else
            System.out.printf("Intercept NOT found within array a.\n", pos);
        pos = findIntercept(b);
        if (pos >= 0)
            System.out.printf("Intercept found at position %3d in array b.\n", pos);
        else
            System.out.printf("Intercept NOT found within array b.\n", pos);
    }

    /*
        preconditions:
            array sorted in ascending order
            initial value for left it is assumed to be zero
            initial value for right it is assumed to be v.length -1

        trivial case:
            with the slice of the array defined by left and right is empty,
            i.e., right - left + 1 == 0

        general case:
            left <= right
    */
    private static int findIntercept(double [] v, int left, int right)
    {
        if (left > right)
            return -1;
        else {
            int k = (left + right) / 2;
            if (v[k] == 0.0) { // Math.abs(v[k] - x) < epsilon :: epsilon can be 1.0e-9
                return k;
            } else if (v[k] < 0.0) {
                return findIntercept(v, k + 1, right);
            } else {
                return findIntercept(v, left, k - 1);
            }
        }
    }
    // wrapper method
    private static int findIntercept(double [] v)
    {
        return findIntercept(v, 0, v.length - 1);
    }
}
