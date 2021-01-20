package etsinf.prg.exam2;
/**
 * Exercises 2 and 3 - Second mid term exam
 *
 * @author IIP professors
 * @version Academic year 19/20
 */
public class Exercises2and3
{
    /** Hidden method */
    private Exercises2and3() { }

    /**
     * Given an array of positive integers,
     * returns the value of the first repeated element
     * in any other position of the array,
     * otherwise returns <code>-1</code> for indicating
     * no repeated elements exist in the array.
     */
    /** Precondition: v is an array of positive integers. */
    public static int firstRepeatedElement(int[] v)
    {
        int i = 0, j;
        boolean found = false;
        while (i < v.length - 1 && !found) {
            j = i + 1;
            while (j < v.length && !found) {
                found = v[i] == v[j];
                j++;
            }
            i++;
        }
        return (found) ? v[i - 1] : -1;

        /* Alternative code
        for (int i=0; i < v.length-1; i++) {
            for (int j=i+1; j < v.length; j++) {
                if ( v[i] == v[j] ) return v[i];
            }
        }
        return -1;
        */
    }

    /** Precondition: 0 <= epsilon < 1 */
    public static int numTerms(double x, double epsilon)
    {
        if (x <= 0) return -1;

        double cPrev = x;
        double cActual = cPrev * 2;
        int i = 2;
        while (1 / (cActual - cPrev) > epsilon) {
            i++;
            cPrev = cActual;
            cActual = cPrev * i;
        }
        return i;
    }
}
