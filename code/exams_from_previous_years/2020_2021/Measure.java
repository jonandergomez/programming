package exam1Code;

/**
 * Classe <code>Measure</code> to represent noise measurements at a given time instant
 * in a measurement station located in the street.
 *
 * @author IIP
 * @version Academic year 2020/2021
 */
public class Measure
{
    /*
       a.(0.50 points)
       Three static variables, also known as class variables, that must be public
       and ummutable, i.e. constants.
       Two of them of type int to represent the maximum noise in dB for each period.
       Their identifiers should be MAX_DAY_TIME and MAX_NIGHT_TIME with values 65
       and 30 respectively.
       And a third one as one object of the class String with the identifier
       DEFAULT_STATION set to the value "CITY HALL".
     */
    // TO BE COMPLETED
    public final static double MAX_DAY_TIME = 65.0;
    public final static double MAX_NIGHT_TIME = 35.0;
    public final static String DEFAULT_STATION = "CITY HALL";

    /*
       b.(0.5 points)
       Four attributes (also known as instance variables) to represent the elements
       associated to a noise measurement.
       Following the order in which these attributes are described in the statement
       of the problem, the identifers and the data types are:
       - instant (TimeInstant)
       - noise (double) 
       - station (String)
       - period (int)
     */
    // TO BE COMPLETED
    private  TimeInstant instant;
    private  double noise;
    private  String station;
    private  int period;

    /*
       c.(0.75 points)
       Constructor for creating a new object of the class Measure with a level of noise
       equal to r, corresponding to a measurement in the station e at time t.
       As a precondition you can asume all the values of these parameters are correct.
     */
    // TO BE COMPLETED
    public Measure(TimeInstant t, String e, double r)
    {
        noise = r;
        instant = t;
        station = e;
        period = t.getPeriod();
    }

    /*
       d.(0.75 points)
       Default constructor for creating a new object of the class Measure with a random level
       of noise in the range <code>[20,200]</code> suposedly acquired in the "CITY HALL"
       at the current time.
     */
    // TO BE COMPLETED
    public Measure()
    {
        this(new TimeInstant(), DEFAULT_STATION, random(20, 200));
    }

    /**
     * Generates a random value in the range [min, max+1[
     * @param min A double value indicating the lowest value that can be returned.
     * @param max A double value indicating the highest value that can be returned.
     * @return A random double value ranging from <code>min</code> to <code>max</code>.
     */
    private double random(double min, double max)
    {
        return Math.random() * (max - min + 1) + min;
    }


    /*
       e.(0.5 points)
       Getter and setter methods for attribute noise.
       It can be assumed as a precondition that the parameter of the setter method is a valid value.
     */
    // TO BE COMPLETED
    public double getNoise() { return noise; }
    public void setNoise(double n) { noise = n; }


    /*
       f.(1.25 points)
       Method equals() that overrides the method equals() inherited from class Object.
       This method checks if the current object referenced by "this" represents the
       same time instant that the object given as parameter, but independently of
       the station, that is, this method will check for equality according to attributes
       noise and instant.
     */
    // TO BE COMPLETED   
    @Override
    public boolean equals(Object o)
    {
        return o instanceof Measure
            && this.noise == ((Measure)o).noise
            && this.instant.equals(((Measure).o).instant);
    }

    /*
       g.(0.75 points)
       Method exceedsMax() that returns true if the measurement exceeds the maximum allowed
       noise level corresponding to the period the time instant belongs to; otherwise
       returns false.
     */ 
    // TO BE COMPLETED
    public boolean exceedsMax()
    {
        return this.period == TimeInstant.DAY_TIME   && this.noise > Measure.MAX_DAY_TIME
            || this.period == TimeInstant.NIGHT_TIME && this.noise > Measure.MAX_NIGHT_TIME;
    }

    /*
       h.(1 point)
       Method toString() that overrides the one inherited from the clas Object and returns
       a text description of the measure with the instant, the name of the station and
       the noise level rounded to 2 decimal digits.
     */
    // TO BE COMPLETED
    @Override
    public String toString()
    {
        return String.format("%s %s %.2f", instant.toString(), station, noise);
    }
}
