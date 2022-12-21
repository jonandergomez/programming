import java.util.*;


public class ProblemsInClass
{
    public static void main(String [] args)
    {
        double [] a = createRandomArray(10, 100, 10);        
        double [] b = createRandomArray(10,  10, 50);        

        double r = scalarProduct(a, b);

        System.out.println("a: " + toString(a));
        System.out.println("b: " + toString(b));
        System.out.println("The scalar product of these two vectors is " + r);

        char [] s = createRandomArrayOfChars(80);

        System.out.println();
        for (int i = 0; i < s.length; i++) System.out.print(s[i]);
        System.out.println();

        converToUppercase(s);

        for (int i = 0; i < s.length; i++) System.out.print(s[i]);
        System.out.println();
    }

    /** precondition: a.length is equal to b.length */
    public static double scalarProduct(double [] a, double [] b)
    {
        double total = 0;

        for (int i = 0; i < a.length; i++) {
            total += a[i] * b[i];
        }

        return total;
    }
    public static double [] elementWiseProduct(double [] a, double [] b)
    {
        double [] c = new double [a.length];

        for (int i = 0; i < a.length; i++) {
            c[i] = a[i] * b[i];
        }

        return c;
    }

    public static double [] minAndMax_v1(double [] a)
    {
        double min = a[0];
        double max = a[0];

        for (int i = 1; i < a.length; i++) {
            if (a[i] < min) { // is the current element (a[i]) less than the minimum so far?
                min = a[i];
            }
            if (a[i] > max) { // is the current element (a[i]) greater than the maximum so far?
                max = a[i];
            }
        }
        double [] result = new double [2];

        result[0] = min;
        result[1] = max;

        return result;
    }

    public static double [] minAndMax_v2(double [] a)
    {
        double min =  Double.MAX_VALUE;
        double max = -Double.MAX_VALUE;

        for (int i = 0; i < a.length; i++) {
            if (a[i] < min) { // is the current element (a[i]) less than the minimum so far?
                min = a[i];
            }
            if (a[i] > max) { // is the current element (a[i]) greater than the maximum so far?
                max = a[i];
            }
        }
        double [] result = new double [2];

        result[0] = min;
        result[1] = max;

        return result;
    }


    public static double [] createRandomArray(int size, double mean, double standard_deviation)
    {
        Random r = new Random();
        double [] a = new double [size];

        // fill the array with random values
        for (int i = 0; i < a.length; ++i)
            a[i] = mean + standard_deviation * r.nextGaussian();

        return a;
    }

    public static char [] createRandomArrayOfChars(int size)
    {
        Random r = new Random();
        char [] a = new char [size];

        // fill the array with random values
        for (int i = 0; i < a.length; ++i)
            a[i] = (char)(' ' + r.nextInt('z' - ' ' + 1));

        return a;
    }

    public static void converToUppercase(char [] s)
    {
        for (int i = 0; i < s.length; i++) {
            if ('a' <= s[i] && s[i] <= 'z') {
                s[i] = (char)(s[i] - 'a' + 'A');
            }
        }
    }


    public static String toString(double [] a)
    {
        StringBuffer sb = new StringBuffer();

        sb.append("{" + a[0]);
        for (int i = 1; i < a.length; i++) {
            sb.append(", " + a[i]);
        }
        sb.append("}");

        return sb.toString();
    }
}
