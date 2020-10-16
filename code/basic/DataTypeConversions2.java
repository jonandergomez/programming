public class DataTypeConversions2
{
    public static void main(String [] args)
    {
        double minValue = 10.0, maxValue = 20.0;
        int numIntervals = 2;
        double value = 14.9;
        value=15.0;

        double sizeInterval = (maxValue - minValue) / numIntervals;
        int indexInterval = (int)((value - minValue) / sizeInterval);

        System.out.printf("%.2f belongs to interval %d",
                            value, indexInterval );

        double a = minValue + indexInterval * sizeInterval;
        double b = minValue + (indexInterval+1) * sizeInterval;

        System.out.printf("which is [%.2f, %.2f[\n", a, b);
    }
}
