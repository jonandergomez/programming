import java.util.Random;


public class ComputeAverage
{
    public static void main(String [] args)
    {
        double [] x = new double [1000000];

        double mean = computeAverage(x);
        System.out.println("mean = " + mean);

        fillArray(x);

        mean = computeAverage(x);
        System.out.println("mean = " + mean);
    }

    public static void fillArray(double [] a)
    {
        Random r = new Random();

        // fill the array with random values
        for (int i = 0; i < a.length; ++i)
            a[i] = 67 + 11 * r.nextGaussian();
    }

    public static double computeAverage(double [] a)
    {
        double sum = 0.0;

        for (int i = 0; i < a.length; ++i) sum += a[i];

        return sum / a.length;
    }
}
