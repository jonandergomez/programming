public class Overflow2
{
    public static void main( String [] args )
    {
        float f = 1.0e+38F;
        double d = 1.0e+308;

        System.out.printf( " %e * 10 = %e\n", f, f*10 );
        System.out.printf( " %e * 10 = %e\n", d, d*10 );

        System.out.printf( " (5.0/0.0) + 166.386 = %f\n", (5.0/0.0)+166.386 );
    }
}
