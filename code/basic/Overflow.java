public class Overflow
{
    public static void main( String [] args )
    {
        byte b = 127;
        System.out.printf( "byte   %32d+1 = %d\n", b, ++b );
        short s = 32767;
        System.out.printf( "short  %32d+1 = %d\n", s, ++s );
        int i = 0x07fffffff;
        System.out.printf( "int    %32d+1 = %d\n", i, i+1 );
        long l = 0x07fffffffffffffffL;
        System.out.printf( "long   %32d+1 = %d\n", l, l+1 );

        i = 1000000;
        System.out.printf( "   %d * %d = %d\n", i, i, i*i );
        l = 1000000;
        System.out.printf( "   %d * %d = %d\n", l, l, l*l );
    }
}
