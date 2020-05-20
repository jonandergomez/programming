
import java.util.*;
import java.io.*;


public class Rainfall1 
{
    private int []  rainfall;
    private String  month;
    private int     numDays;


    private static int daysPerMonth( String m, int year )
    {
        switch( m.toLowerCase() ) {
            case "january" :
            case "march" :
            case "may" :
            case "july" :
            case "august" :
            case "october" :
            case "december" :
                return 31;

            case "april":
            case "june":
            case "september":
            case "november":
                return 30;

            case "february":
                return 28 + ((year % 4 == 0 && year % 100 == 10 || year % 400 == 0) ? 1 : 0);

            default :
                throw new RuntimeException( "Invalid month name or identifier " + m );
        }
    }
    private static void fillRainfall( double rainProbability, int [] a )
    {
        Random r4day = new Random();
        Random r4liters = new Random();

        for( int i=1; i < a.length; i++ ) {
            if ( r4day.nextDouble() > (1.0-rainProbability) ) {
                a[i] = 10 + r4liters.nextInt( 101 );
            }
        }
    }

    public Rainfall1( String month, int year )
    {
        this.month = month;
        this.numDays = daysPerMonth( month, year );
        this.rainfall = new int [ this.numDays + 1 ];

        fillRainfall( 0.25, this.rainfall );
    }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();

        for( int i=1; i < rainfall.length; i++ ) {
            sb.append( " " + rainfall[i] );
        }

        return sb.toString();
    }

    public double average()
    {
        double sum=0;

        for( int i=1; i < rainfall.length; i++ ) {
            sum += rainfall[i];
        }

        return sum / (rainfall.length-1); // The average of all the days of the month or of all the days it rained?
    }
    public int maximum()
    {
        int max = rainfall[1];

        for( int i=2; i < rainfall.length; i++ ) {
            if ( max < rainfall[i] ) max = rainfall[i];
        }
        return max;

    }
    public int minimum()
    {
        int min = 0;

        for( int i=1; i < rainfall.length; i++ ) {
            if ( rainfall[i] != 0 ) {
                if ( min == 0 ) min = rainfall[i];
                else if ( rainfall[i] < min ) min = rainfall[i];
            }
        }
        return min;

    }
    public int [] minMaxAvg()
    {
        int [] result = new int [3];
        /*
        result[0] = this.minimum();
        result[1] = this.maximum();
        result[2] = (int)this.average();
        */
        result[0] = rainfall[1];
        result[1] = rainfall[1];
        result[2] = rainfall[1];

        for( int day=2; day < rainfall.length; day++ ) {

            result[0] = (result[0] <= 0 || rainfall[day] > 0 && rainfall[day] < result[0]) ? rainfall[day] : result[0];
            result[1] = (rainfall[day] > result[1]) ? rainfall[day] : result[1];
            result[2] += rainfall[day];
        }
        result[2] /= (rainfall.length-1);

        return result;
    }
    public int accumulatedRain()
    {
        int rainTotal=0;

        for( int i=1; i < rainfall.length; i++ ) {
            rainTotal += rainfall[i];
        }

        return rainTotal;
    }
    public int dayItRainedTheMost()
    {
        int day=1;

        for( int i=2; i < rainfall.length; i++ ) {
            if ( rainfall[i] > rainfall[day] )
                day = i;
        }

        return day;
    }
    public int dayItRainedTheLeast()
    {
        int min = this.minimum();

        for( int i=1; i < rainfall.length; i++ ) {
            if ( rainfall[i] == min )
                return i;
        }

        return 0;
    }
}
