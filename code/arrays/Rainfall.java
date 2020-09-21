import java.util.*;
import java.io.*;


public class Rainfall
{
    private double [][] data;
    private int         year;

    public Rainfall( int year )
    {
        this.year = year;

        this.data = new double [13][];

        for( int month=1; month <= 12; month++ ) {
            
            this.data[month] = new double [1+daysPerMonth(month)];
        }
    }

    private int daysPerMonth( int m )
    {
        switch(m) {
            case  1:
            case  3:
            case  5:
            case  7:
            case  8:
            case 10:
            case 12: return 31;
            
            case  4:
            case  6:
            case  9:
            case 11: return 30;

            case  2: return 28 + (leapYear() ? 1 : 0);

            default:
                throw new RuntimeException( String.format( "The value %d for month is crazy!", m ) );
        }
    }
    private boolean leapYear()
    {
        return ((year % 4) == 0) && ((year % 100) != 0) || ((year % 400) == 0);
    }

    public void randomFill()
    {
        double probabilityOfRainingInADay = 0.25;
        Random r = new Random();
        Random p = new Random();

        for( int month=1; month < data.length; month++ ) {
            // This loop is going to be repeated for all the values of month from 1 to 12, both included.
            for( int day=1; day < data[month].length; day++ ) {
                // This loop is going to be repeated for all the values of day from 1 to the last day of the corresponding month, both included.
                
                if ( r.nextDouble() < probabilityOfRainingInADay ) {
                    
                    data[month][day] = 10 + p.nextInt( 100 );
                }
            }
        }
    }

    public int getYear() { return year; }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();

        for( int month=1; month < data.length; month++ ) {
            for( int day=1; day < data[month].length; day++ ) {
                if ( data[month][day] != 0 ) 
                    sb.append( String.format( "%04d-%02d-%02d  %8.2f\n", year, month, day, data[month][day] ) );
            }
        }

        return sb.toString();
    }

    public double [][] computeAverages()
    {
        double [][] result = new double [data.length][2];        

        for( int month=1; month < data.length; month++ ) {
            for( int day=1; day < data[month].length; day++ ) {
                result[month][0] += data[month][day];
            }
            result[month][1] = result[month][0] / (data[month].length-1);
            result[0][0] += result[month][0]; // We accumulated to the global rainfall the accumulated rainfall of each month
        }
        result[0][1] = result[0][0] / (365 + (leapYear() ? 1 : 0));

        return result;
    }

    public String [] daysItRainedMoreThan( double threshold )
    {
        int counter=0;
        for( int month=1; month < data.length; month++ ) {
            for( int day=1; day < data[month].length; day++ ) {
                if ( data[month][day] > threshold ) counter++;
            }
        }

        String [] result = new String [counter];
        counter=0;

        for( int month=1; month < data.length; month++ ) {
            for( int day=1; day < data[month].length; day++ ) {
                if ( data[month][day] > threshold )
                    result[counter++] = String.format( "%04d-%02d-%02d  %8.2f", year, month, day, data[month][day] );
            }
        }

        return result;
    }

    public double maxRainfall()
    {
        double max = -1;

        for( int month=1; month < data.length; month++ ) {
            for( int day=1; day < data[month].length; day++ ) {
                max = (data[month][day] > max) ? data[month][day] : max;
            }
        }
        return max;
    }



    public static Rainfall loadFromFile( String filename )
        throws Exception // This is for ignoring the checked exceptions that can be thrown in this method.
    {
        Scanner f = new Scanner( new File( filename ) );
        Rainfall rf = null;

        while( f.hasNext() ) {
            String line = f.nextLine().trim();
            String [] components = line.split( "\\s+" );
            String [] date = components[0].split( "-" );
            int year = Integer.parseInt( date[0] );
            int month = Integer.parseInt( date[1] );
            int day = Integer.parseInt( date[2] );
            double rainfall = Double.parseDouble( components[1] );

            if ( rf == null ) rf = new Rainfall( year );

            rf.data[month][day] = rainfall;
        }

        return rf;
    }

    public String findSubsquenceOfAtLeastThreeDaysItRainedMoreThan( int threshold )
    {
        int starting_month=-1,
            starting_day=-1,
            ending_month=-1,
            ending_day=-1,
            counter=0;

        String res="";

        for( int month=1; month < data.length; month++ ) {
            for( int day=1; day < data[month].length; day++ ) {

                if ( data[month][day] > threshold ) {

                    if ( ++counter == 1 ) {
                        starting_month = month;
                        starting_day = day;
                    }
                    ending_month = month;
                    ending_day = day;

                } else {
                
                    if ( counter >= 3 ) {
                    
                        res += String.format( "Subsequence starting at %02d/%02d and ending at %02d/%02d\n",
                                                              starting_day, starting_month, ending_day, ending_month );
                    }

                    starting_month=starting_day=ending_month=ending_day=-1; // Optionall, but just in case!
                    counter=0;
                }
            }
        }

        if ( res.length() > 0 )
            return res;
        else
            return String.format( "No more than two consecutive days it rained more than %d liters/m2", threshold );
    }
}
