public class DataTypeConversions
{
    public static void main(String [] args)
    {
        long  x, y, z;
        int j = 55, k, i, num = 6, den = 10;
        float f;
        double d = 123.67, q;

        x = 98;                    System.out.println("x = " + x);
        y = j;                     System.out.println("y = " + y);
        z = 9 * j;                 System.out.println("z = " + z);
        k = (int) 55L;             System.out.println("k = " + k);
        j = (int) y;               System.out.println("j = " + j);
        i = (int) d;               System.out.println("i = " + i);
        f = (float) d;             System.out.println("f = " + f);
        d = 3.40282347e+50;        System.out.println("d = " + d);
        f = (float) d;             System.out.println("f = " + f);
        q = num / den;             System.out.println("q = " + q);
        q = (double)num / den;     System.out.println("q = " + q);
    }
}
