package etsinf.prg.agenda1;

import java.util.Calendar;

/**
 * Class designed to represent instants of time with
 * precision a the level of seconds.
 * Then, this class will include attributes for year, month, day, hour, minutes
 * and seconds.
 */
public class Timestamp
{
    /** Array containing the days per month. Useful for checking correct dates. */
    private final static int [] daysPerMonth = {0, 31, 28, 31, 30, 31, 30,
                                                   31, 31, 30, 31, 30, 31};

    /** Year, potentially all integer values are considered as valid. */
    private int year;
    /** Month, valid values: integers ranging from 1 to 12. */
    private int month;
    /** Day, valid values: integers ranging from 1 to 31. */
    private int day;
    /** Hour, valid values: integers ranging from 0 to 23. */
    private int hour;
    /** Minutes, valid values: integers ranging from 0 to 59. */
    private int minutes;
    /** Seconds, valid values: integers ranging from 0 to 59. */
    private int seconds;

    /**
      * Default constructor; creates an object representing the current time.
      */
    public Timestamp()
    {
        Calendar now = Calendar.getInstance();
        year    = now.get(Calendar.YEAR);
        month   = now.get(Calendar.MONTH) + 1; // +1 because month is stored with values ranging from 0 to 11
        day     = now.get(Calendar.DAY_OF_MONTH);
        hour    = now.get(Calendar.HOUR_OF_DAY);
        minutes = now.get(Calendar.MINUTE);
        seconds = now.get(Calendar.SECOND);
    }
    /**
      * Generic constructor; creates an object from the provided values for all the attributes.
      *
      * @param year integer with the value of the year.
      * @param month integer with the value of the month ranging from 1 to 12.
      * @param day integer with the value of the day of the mounth ranging from 1 to 31.
      * @param hour integer with the value of the hours in the day ranging from 0 to 23.
      * @param minutes integer with the value of the minutes ranging from 0 to 59.
      * @param seconds integer with the value of the seconds ranging from 0 to 59.
      */
    public Timestamp(int year, int month, int day, int hour, int minutes, int seconds)
    {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minutes = minutes;
        this.seconds = seconds;

        /* Checkings for future versions
        if (!(1 <= this.month && this.month <= 12))
            throw new RuntimeException("Invalild month!");
        int maxDays = Timestamp.daysPerMonth(this.year, this.month);
        if (!(1 <= this.day && this.day <= maxDays))
            throw new RuntimeException("Invalild day!");
        if (!(1 <= this.hour && this.hour <= 23))
            throw new RuntimeException("Invalild hour!");
        if (!(1 <= this.minutes && this.minutes <= 59))
            throw new RuntimeException("Invalild minutes!");
        if (!(1 <= this.seconds && this.seconds <= 59))
            throw new RuntimeException("Invalild seconds!");
        */
    }
    public Timestamp(String s)
    {
        // 000000000011111111112
        // 012345678901234567890
        // yyyy-mm-dd HH:MM:SS
        this.year    = Integer.parseInt(s.substring( 0,  4));
        this.month   = Integer.parseInt(s.substring( 5,  7));
        this.day     = Integer.parseInt(s.substring( 8, 10));
        this.hour    = Integer.parseInt(s.substring(11, 13));
        this.minutes = Integer.parseInt(s.substring(14, 16));
        this.seconds = Integer.parseInt(s.substring(17, 19));
    }

    /**
      * Returns whether the year of the current date represented by the current
      * object is a leap year.
      * This method is an instance method (i.e. a non-static method) to be invoked
      * with respect to an existing object.
      *
      * @return Whether the year is a leap year.
      */
    public boolean isLeapYear()
    {
        // reuse the static method for the value for the year of the current object
        return Timestamp.isLeapYear(this.year);
    }
    /**
      * Returns whether the value for a year provided as argument is a leap year.
      * This method is a static method to be invoked by using the name of this
      * class; as in the example of the instance method with the same name.
      *
      * @param y Value for the year.
      * @return Whether the year is a leap year.
      */
    public static boolean isLeapYear(int y)
    {
        return ((y % 4 == 0) && (y % 100 != 0)) || (y % 400 == 0);
    }
    /** Returns the number of days taking into account if the year is a leap year.
      *
      * @param y Value for the year.
      * @param m Value for the month. This method assumes as a precondition that
      *          the value is in the correct range [1,12].
      * @return The number of days of month <code>m</code> according to year <code>y</code>.
      */
    public static int daysPerMonth(int y, int m)
    {
        return daysPerMonth[m] + (m == 2 && isLeapYear(y) ? 1 : 0);
    }


    /** Getter method for attribute year.
      * @return The value of the year.
      */
    public int getYear() { return year; }

    /** Getter method for attribute month.
      * @return The value of the month [1,12].
      */
    public int getMonth() { return month; }

    /** Getter method for attribute day.
      * @return The value of the day [1,31].
      */
    public int getDay() { return day; }

    /** Getter method for attribute hour.
      * @return The value of the hour [0,23].
      */
    public int getHour() { return hour; }

    /** Getter method for attribute minutes.
      * @return The value of the minutes [0,59].
      */
    public int getMinutes() { return minutes; }

    /** Getter method for attribute seconds.
      * @return The value of the seconds [0,59].
      */
    public int getSeconds() { return seconds; }

    /** Setter method for attribute year.
      * @param year <code>int</code> with the new value for the attribute year.
      */
    public void setYear(int year) { this.year = year; }

    /** Setter method for attribute month.
      * @param month <code>int</code> with the new value for the attribute month.
      */
    public void setMonth(int month)
    {
        if (month < 1 || month > 12)
            throw new RuntimeException("Invalid month!");

        this.month = month;
    }

    /** Setter method for attribute day.
      * @param day <code>int</code> with the new value for the attribute day.
      */
    public void setDay(int day)
    {
        int maxDays = daysPerMonth(this.year, this.month);

        if (day < 1 || day > maxDays)
            throw new RuntimeException("Invalid day!");

        this.day = day;
    }

    /** Setter method for attribute hour.
      * @param hour <code>int</code> with the new value for the attribute hour.
      */
    public void setHour(int hour)
    {
        if (hour < 0 || hour > 23)
            throw new RuntimeException("Invalid hour!");

        this.hour = hour;
    }

    /** Setter method for attribute minutes.
      * @param minutes <code>int</code> with the new value for the attribute minutes.
      */
    public void setMinutes(int minutes)
    {
        if (minutes < 0 || minutes > 59)
            throw new RuntimeException("Invalid minutes!");

        this.minutes = minutes;
    }

    /** Adds the given amount of minutes.
      * @param minutes <code>int</code> with the minutes to be added to this timestamp.
      */
    public void addMinutes(int minutes)
    {
        this.minutes += minutes;
        this.hour += this.minutes / 60;
        this.minutes %= 60;
        int exceding_days = this.day + this.hour / 24;
        this.hour %= 24;
        this.day = 0;
        while (exceding_days > daysPerMonth(this.year, this.month)) {
            exceding_days -= daysPerMonth(this.year, this.month);
            this.month++;
            if (this.month > 12) {
                this.month = 1;
                this.year++;
            }
        }
        this.day = exceding_days;
    }
    /** Setter method for attribute seconds.
      * @param seconds <code>int</code> with the new value for the attribute seconds.
      */
    public void setSeconds(int seconds)
    {
        if (seconds < 0 || seconds > 59)
            throw new RuntimeException("Invalid seconds!");

        this.seconds = seconds;
    }

    /** Compares two objects of this class to determine if they are equal.
      * This method overrides the method equals of class <code>Object</code>
      * in order to adapt it to objects of the class <code>Timestamp</code>.
      * To perform the correct overriding the parameter must be a reference to
      * objects of the class <code>Object</code>.
      *
      * @param o A reference to the object to be compared with the current one
      *          to check if both are equal.
      * @return <code>true</code> if <code>o</code> references to an object of this
      *         class and all the attributes match, <code>false</code> otherwise.
      */
    @Override
    public boolean equals(Object o)
    {
        // first step is to check if the provided object can be compared with
        // the current one; the operator 'instanceof' is used
        if (o instanceof Timestamp) {
            Timestamp other = (Timestamp)o; // with 'o' we cannot access attributes
            return this.year    == other.year
                && this.month   == other.month
                && this.day     == other.day
                && this.hour    == other.hour
                && this.minutes == other.minutes
                && this.seconds == other.seconds;
        } else {
            // if the provided object is not an object of the class Timestamp
            // the comparison is not possible and we have to return false
            return false;
        }

        /* ALTERNATIVE
            return o instanceof timestamp
                && this.year    == (Timestamp)o.year
                && this.month   == (Timestamp)o.month
                && this.day     == (Timestamp)o.day
                && this.hour    == (Timestamp)o.hour
                && this.minutes == (Timestamp)o.minutes
                && this.seconds == (Timestamp)o.seconds;
        */
    }

    /** Returns an object of the class <code>String</code> with the reprsentation
      * this object using a pure numerical style ready to be used for columns
      * in CSV files and databases.
      * CSV stands for comma separated values.
      *
      * @return A reference to a new created object of the class <code>String</code>.
      */
    @Override
    public String toString()
    {
        // returns the timestamp in the format YYYY-MM-DD hh:mm:ss
        //                                     2020-01-07 01:06:02
        return String.format("%04d-%02d-%02d %02d:%02d:%02d",
                            this.year, this.month, this.day,
                            this.hour, this.minutes, this.seconds);
    }

    /** Compares two timestamps returning an integer following the standard criterion.
      * <ul>
      * <li> &lt;0 if <code>this</code> is previous to <code>other</code>. </li>
      * <li>     0 if <code>this</code> is the same instant than <code>other</code>. </li>
      * <li> &gt;0 if <code>this</code> is later than <code>other</code>. </li>
      * </ul>
      *
      * @param other A reference to another object of the class <code>Timestamp</code>.
      * @return A negative integer value when the current object is earlier than
      *         the other object given as parameter, zero when both timestamps are
      *         equal, and a positive value when the current object is later than
      *         the other object.
      */
    public int compareTo(Timestamp other)
    {
        if      (this.year    < other.year)    return -1;
        else if (this.year    > other.year)    return  1;
        else if (this.month   < other.month)   return -1;
        else if (this.month   > other.month)   return  1;
        else if (this.day     < other.day)     return -1;
        else if (this.day     > other.day)     return  1;
        else if (this.hour    < other.hour)    return -1;
        else if (this.hour    > other.hour)    return  1;
        else if (this.minutes < other.minutes) return -1;
        else if (this.minutes > other.minutes) return  1;
        else if (this.seconds < other.seconds) return -1;
        else if (this.seconds > other.seconds) return  1;
        else                                   return  0;

        /* alternative
                                  YYYYMMDDhhmmss
        long l_this = this.year    * 10000000000L
                    + this.month   * 100000000L
                    + this.day     * 1000000L
                    + this.hour    * 10000L
                    + this.minutes * 100L
                    + this.seconds * 1L;
        long l_other = this.year    * 10000000000L
                     + this.month   * 100000000L
                     + this.day     * 1000000L
                     + this.hour    * 10000L
                     + this.minutes * 100L
                     + this.seconds * 1L;
        return (int)(l_this - l_other);
        */
    }

    /** Clones an object of this class.
      *
      * @return the reference to a new object of the class
      *         <code>Timestamp</code> that is a
      *         copy of the current object.
      */
    @Override
    public Timestamp clone()
    {
        return new Timestamp(this.year, this.month, this.day,
                             this.hour, this.minutes, this.seconds);
    }
}
