import java.util.Locale;
import java.util.Scanner;
/**
 * Class TestMeasure: a program class to check the functionality of class Measure.
 *
 * @author IIP
 * @version Academic year 2020/2021
 */

public class TestMeasure
{
    private TestMeasure() { }
    
    public static void main(String[] args)
    {
        /*
           a.(0.25 points)
           Create a measure 'm1' with the default values for the attributes.
         */
        // TO BE COMPLETED
        Measure m1 = new Measure();

        /*
           b.(0.75 points)
           Create an object of the class Scanner, referenced by a reference variable
           to objects of the class Scanner, use the identifier 'kbd' or 'input'.
           Then, ask the user for the name of the station and the noise level.
         */
        Scanner input = new Scanner(System.in).useLocale(Locale.US);
        System.out.print("\n Enter the name of the station: " );
        String station = input.nextLine().trim();
        System.out.print("\n Enter the noise level: " );
        double noise = input.nextDouble();

      
        /*
           c.(0.5 points)
           Create another measure 'm2' with the station name and the noise level read
           previously from the keyboard, and using the current UTC time as the instant.
         */
        // TO BE COMPLETED
        Measure m2 = new Measure(new TimeInstant(), station, noise);
       
        /*
           d.(0.25 points)
           Update the measure 'm1' to reduce its noise level at 50%
         */
        // TO BE COMPLETED
        m1.setNoise(m1.getNoise() / 2.0);

        /*
           e.(0.25 points)
           Show both measures on screen.
         */
        System.out.println(m1);
        System.out.println(m2);
        
        /*
           f.(0.5 points)
           Show on screen whether both measures ('m1' and 'm2') exceed the
           maximum allowed noise level.
         */
        // TO BE COMPLETED
        System.out.println("Are both measures excedding the limits? " + (m1.exceedsMax() && m2.exceedsMax()));

        /* not valid alternative
        System.out.println("Is measures 1 excedding the limits? " + m1.exceedsMax());
        System.out.println("Is measures 2 excedding the limits? " + m2.exceedsMax());
        */
        
        /*
           g.(0.5 points)
           Show on screen whether both measures are equal.
         */
        // TO BE COMPLETED
        System.out.println("Are both measures equal? " + m1.equals(m2));

    }
}
