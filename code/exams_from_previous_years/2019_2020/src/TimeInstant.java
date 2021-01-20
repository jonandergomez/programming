package etsinf.prg.exam2;
/**
 * Class TimeInstant.
 * <p> This class allows you to represent instants or timestamps with
 * detail of hours and minutes. Thus, this class represents the moment
 * that defines a time instant, in this case, the hours and minutes of
 * any given day.
 * </p>
 *
 *  @author IIP. Grado en Informatica. ETSINF, UPV
 *  @version Academic Year 2019-20
 */
public class TimeInstant
{
    // ATTRIBUTES:
    /** Integer attribute for storing the hours of a TimeInstant.
     *  It should belong to the iterval <code>[0..23]</code>
     */
    private int hours;
    /** Integer attribute for storing the minutes of a TimeInstant.
     *  It should belong to the iterval <code>[0..59]</code>
     */
    private int minutes;

    // CONSTRUCTORS:
    /**
     *  <code>TimeInstant</code> corresponding to <code>iniHours</code>
     *  hours and <code>iniMinutes</code> minutes.
     *  <p> Precondition: <code>0&lt;=iniHours&lt;24, 0&lt;=iniMinutes&lt;60 </code> </p>
     *
     * @param iniHours    Initial value for hours.
     * @param iniMinutes  Initial value for minutes.
     */
    public TimeInstant(int iniHours, int iniMinutes)
    {
        this.hours = iniHours;
        this.minutes = iniMinutes;
    }

    /**
     * <code>TimeInstant</code> (hours and minutes) from current
     * UTC (universal coordinated time).
     */
    public TimeInstant()
    {
        long tMinTotal = System.currentTimeMillis() / (60 * 1000);
        int tMinCurrent = (int) (tMinTotal % (24 * 60));
        hours = tMinCurrent / 60;
        minutes = tMinCurrent % 60;
    }

    // CONSULTORS AND MODIFYERS:
    /** Returns hours of current <code>TimeInstant</code> object.
     *  @return The value of hours.
     */
    public int getHours() { return this.hours; }

    /** Returns minutes of current <code>TimeInstant</code> object.
      * @return The value of minutes.
      */
    public int getMinutes() { return this.minutes; }

    /** Modifies hours of current <code>TimeInstant</code> object.
     *  @param hh The new value for the hours.
     */
    public void setHours(int hh) { this.hours = hh; }

    /** Modifies minutes of current <code>TimeInstant</code> object.
     *  @param mm The new values for the minutes.
     */
    public void setMinutes(int mm) { this.minutes = mm; }

    // OTHER METHODS:
    /** Returns current <code>TimeInstant</code> object in "hh:mm" format.
     * @return A string representation of the current object.
     */
    public String toString()
    {
        return String.format("%02d:%02d", this.hours, this.minutes);
    }

    /** Returns true only if <code> o </code> is an object of the class
     *  <code>TimeInstant</code> whose hours
     *  and minutes match with hours and minutes of the current <code>TimeInstant</code>.
     *  @return <code>true</code> if both objects contain
     *  the same values for the attributes,
     *  otherwise <code>false</code>.
     */
    public boolean equals(Object o)
    {
        return o instanceof TimeInstant
            && this.hours == ((TimeInstant) o).hours
            && this.minutes == ((TimeInstant) o).minutes;
    }

    /** Returns number of minutes from
     *  00:00 until current <code>TimeInstant</code> object
     *  @return The minutes from 00:00
     */
    public int toMinutes() { return this.hours * 60 + this.minutes; }

    /** Chronological comparison of current <code>TimeInstant</code> object
     *  and <code>tInstant</code> parameter<br> Result is negative when current
     *  <code>TimeInstant</code> is previous to <code>tInstant</code>,
     *  zero if they are equal and positive when current <code>TimeInstant</code>
     *  is posterior to <code>tInstant</code>.
     *  @param other An object of the class <code>TimeInstant</code>
     *               for comparing with the current object.
     *  @return An integer with the result of the comparion.
     */
    public int compareTo(TimeInstant other) {
        return this.toMinutes() - other.toMinutes();
    }

    // EXTRA ACTIVITY:
    /** Returns a TimeInstant from its textual description
     *  in a <code>String</code> with format "<code>hh:mm</code>".
     *
     *  @param s An object of the class <code>String</code> representing a
                 time in the format HH:MM.
     *  @return A new object of the class <code>TimeInstant</code> created
     *          from the <code>String</code> passed as argument.
     */
    public static TimeInstant valueOf(String s)
    {
        int hours   = Integer.parseInt(s.substring(0, 2));
        int minutes = Integer.parseInt(s.substring(3, 5));
        return new TimeInstant(hours, minutes);
    }
}
