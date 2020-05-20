public class RealDataTypes
{
    public static void main( String [] args )
    {
        float  x = 2.0F / 7.0F;
        double y = 2.0 / 7.0;

        System.out.printf( " x =   %22.17f      %22.17e\n", x, x );
        System.out.printf( " y =   %22.17f      %22.17e\n", y, y );
        System.out.println();
        System.out.printf( "  Float: min = %22.15e   max = %22.16e \n",
                                Float.MIN_VALUE, Float.MAX_VALUE );
        System.out.printf( " Double: min = %22.15e   max = %22.16e \n",
                                Double.MIN_VALUE, Double.MAX_VALUE );
    }
}
