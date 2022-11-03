package etsinf.prg.agenda3;

/**
 * Class designed to represent lapses/periods of time with
 * precision a the level of seconds.
 */
public class TimeDelta
{
    /** Seconds */
    private long seconds;

    /** Constructor that creates a new object of the class <code>TimeDelta</code>
      * given the amount of seconds provided as parameter.
      *
      * @param seconds Value of the seconds of the lapse computed externally to
      *                this class.
      */
    public TimeDelta(long seconds)
    {
        this.seconds = Math.abs(seconds);
    }

    /** Constructor that creates a new object of the class <code>TimeDelta</code>
      * from two objects of the class <code>Timestamp</code>.
      * First parameter represents the starting time instant, and
      * second parameter the ending time instant.
      *
      * @param start An object of the class <code>Timestamp</code>.
      * @param end An object of the class <code>Timestamp</code>.
      */
    public TimeDelta(Timestamp start, Timestamp end)
    {
        int rc = start.compareTo(end);
        if (rc > 0)
            throw new RuntimeException("Incorrect timestamps for computing a lapse of time. "
                                     + "Starting time is later than ending time.");

        seconds = 0;
        if (rc < 0) {
            long days = 0;
            // add the days corresponding to entire years between both timestamps
            for (int year=start.getYear()+1; year < end.getYear(); ++year)
                days += Timestamp.isLeapYear(year) ? 366 : 365;

            if (start.getYear() < end.getYear()) {
                // add the days corresponding to the remaining months from next month
                // of starting timestamp to the end of its year
                for (int month=start.getMonth()+1; month <= 12; ++month)
                    days += Timestamp.daysPerMonth(start.getYear(), month);

                // add the days corresponding to the entire months of the year of
                // the ending timestamp
                for (int month=1; month < end.getMonth(); ++month)
                    days += Timestamp.daysPerMonth(end.getYear(), month);

                // add the remaining entire days of the month corresponding
                // to the starting time
                days += Timestamp.daysPerMonth(start.getYear(), start.getMonth()) - start.getDay();
                // add the entire days of the month corresponding to the ending time
                days += end.getDay()-1;

            } else if (start.getYear() == end.getYear()) {

                if (start.getMonth() < end.getMonth())
                    // add the remaining entire days of the month corresponding
                    // to the starting time
                    days += Timestamp.daysPerMonth(start.getYear(), start.getMonth());

                // discount the days of the starting timestamp
                days -= start.getDay();

                // add the days corresponding to the entire months between both
                // both timestamps
                for (int month=start.getMonth()+1; month < end.getMonth(); ++month)
                    days += Timestamp.daysPerMonth(start.getYear(), month);

                // add the entire days of the month corresponding to the ending time
                days += end.getDay()-1;
            }

            // seconds corresponding to all entire days between both timestamps
            seconds = days * 24 * 60 * 60;
             // add all the seconds of the first day as an entire day
            seconds += 24 * 60 * 60;
            // subtract the seconds of the first day according to the starting time within the day
            seconds -= ((start.getHour() * 60 + start.getMinutes()) * 60 + start.getSeconds());
            // add the seconds of the ending day
            seconds += (end.getHour() * 60 + end.getMinutes()) * 60 + end.getSeconds();
        }
    }

    /** Returns the total number of seconds in the lapse of time.
      * @return Total number of seconds.
      */
    public long getTotalSeconds() { return seconds; }
    /** Returns the total number of entire minutes in the lapse of time.
      * @return Total number of entire minutes.
      */
    public long getTotalMinutes() { return seconds / 60; }
    /** Returns the total number of entire hours in the lapse of time.
      * @return Total number of entire hours.
      */
    public long getTotalHours() { return seconds / (60 * 60); }
    /** Returns the total number of entire days in the lapse of time.
      * @return Total number of entire days.
      */
    public long getTotalDays() { return seconds / (24 * 60 * 60); }

    /** Returns an string representing the object. Some examples:
      * <ul>
      * <li> 400 days, one hour and 13 seconds </li>
      * <li> 40 minutes and one second </li>
      * <li> five seconds </li>
      * <li> 1 day and three hours </li>
      * </ul>
      *
      * @return An object of the class <code>String</code> with the
      *         text describing the lapse of time with detail of days to seconds.
      */
    public String toString()
    {
        long l = seconds; // to do not change the attribute seconds
        int s = (int)(l % 60); // extract the seconds
        l /= 60; // reduce to minutes
        int m = (int)(l % 60); // extract the minutes
        l /= 60; // reduce to hours
        int h = (int)(l % 24); // extract the hours
        l /= 24; // reduce to days
        int d = (int)l;

        String result_d = "";
        if (d > 1)
            result_d = d + " days";
        else if (d == 1)
            result_d = "one day";

        String result_h = "";
        if (h > 1)
            result_h += h + " hours";
        else if (h == 1)
            result_h += "one hour";

        String result_m = "";
        if (m > 1)
            result_m += m + " minutes";
        else if (m == 1)
            result_m += "one minute";

        String result_s = "";
        if (s > 1)
            result_s += s + " seconds";
        else if (s == 1)
            result_s += "one second";

        String connector = " and ";
        String result = result_s;

        if (result_m.length() > 0) {
            if (result.length() > 0) {
                result = result_m + connector + result;
                connector = ", ";
            } else
                result = result_m;
        }

        if (result_h.length() > 0) {
            if (result.length() > 0) {
                result = result_h + connector + result;
                connector = ", ";
            } else
                result = result_h;
        }

        if (result_d.length() > 0) {
            if (result.length() > 0) {
                result = result_d + connector + result;
                connector = ", ";
            } else
                result = result_d;
        }

        return result;
    }
}
