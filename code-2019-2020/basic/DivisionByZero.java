public class DivisionByZero
{
    public static void main( String [] args )
    {
        int a, b, c;

        a = 2; b = 7; c = a/b;
        System.out.printf( "a = %d | b = %d | c = %d\n", a, b, c );
        a = 2; b = 0; c = a/b;
        System.out.printf( "a = %d | b = %d | c = %d\n", a, b, c );
    }
}
