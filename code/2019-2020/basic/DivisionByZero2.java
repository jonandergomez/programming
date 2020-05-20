public class DivisionByZero2
{
    public static void main( String [] args )
    {
        double x, y, z;

        x =  1.0; y = 0.0; z = x / y;
        System.out.printf( "x = %13e | y = %13e | z = %13e\n", x, y, z );
        x = -1.0; y = 0.0; z = x / y;
        System.out.printf( "x = %13e | y = %13e | z = %13e\n", x, y, z );
        x =  0.0; y = 0.0; z = x / y;
        System.out.printf( "x = %13e | y = %13e | z = %13e\n", x, y, z );
    }
}
