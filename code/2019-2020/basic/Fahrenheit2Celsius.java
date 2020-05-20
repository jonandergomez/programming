public class Fahrenheit2Celsius
{
    public static void main( String [] args )
    {
        double f = -40;
        double c = (5/9) * (f - 32);

        System.out.printf( " %.3f ºF  = %.3f ºC\n" , f, c );
    }
}
