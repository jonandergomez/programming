import java.util.Calendar;
import java.util.Scanner;
import java.util.Locale;

public class Timestamp {
    
    private int hour;
    private int minutes;
    private int seconds;

    public Timestamp() {
        Calendar now = Calendar.getInstance();

        this.hour = now.get(Calendar.HOUR_OF_DAY);
        this.minutes = now.get(Calendar.MINUTE);
        this.seconds = now.get(Calendar.SECOND);
    }
    public Timestamp(int hour) {
        this(hour, 0, 0);
    }
    public Timestamp(int hour, int minutes) {
        this(hour, minutes, 0);
    }
    public Timestamp(int hour, int minutes, int seconds) {
        this.hour = hour;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public int getHour() { return hour; }
    public int getMinute() { return minutes; }
    public int getSeconds() { return seconds; }

    public void setHour(int h) { hour = h; }
    public void setMinutes(int m) { minutes = m; }
    public void setSeconds(int s) { seconds = s; }

    @Override 
    public String toString()
    {
        return String.format("%02d:%02d:%02d", hour, minutes, seconds);
    }

    /**
     * Compares to time instants by checking hours, minutes and seconds,
     * and returns less than zero if this is a timestamp earlier than other,
     * greater than zero if this is a timestamp later than other,
     * and zero when both represent the same time instant.
     * 
     * @param other
     * @return less than zero if this is a timestamp earlier than other,
     *         greater than zero if this is a timestamp later than other,
     *         and zero when both represent the same time instant.
     */
    public int compareTo(Timestamp other)
    {
        int  this_in_seconds = ( this.hour * 60 +  this.minutes) * 60 +  this.seconds;
        int other_in_seconds = (other.hour * 60 + other.minutes) * 60 + other.seconds;

        return this_in_seconds - other_in_seconds;
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof Timestamp) {
            return this.compareTo((Timestamp)o) == 0;
        } else {
            return false;
        }
    }

    public static void main(String [] args) {
        Scanner input = new Scanner(System.in).useLocale(Locale.US);

        Timestamp t1 = new Timestamp();
        Timestamp t2 = new Timestamp(13, 15, 43);

        System.out.println(t1);
        System.out.println(t2);

        System.out.println("Is " + t1 + " earlier than " + t2 + "? " + (t1.compareTo(t2) < 0));

        System.out.print("Hi user, enter your time (hh mm ss): ");
        int h = input.nextInt();
        int m = input.nextInt();
        int s = input.nextInt();

        t2.setHour(h);
        t2.setMinutes(m);
        t2.setSeconds(s);

        System.out.println("Is " + t1 + " earlier than " + t2 + "? " + (t1.compareTo(t2) < 0));
    }
} 