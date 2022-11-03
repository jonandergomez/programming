import etsinf.prg.agenda1.*;

/**
  * Class for testing the different functionalities provided by the classes
  * included in the package <code>agenda1</code>.
  */
public class TestingAgenda1
{
    public static void main(String [] args)
    {
        Timestamp now = new Timestamp();
        System.out.println();
        System.out.println("Current time is " + now);
        System.out.println();

        Timestamp t1 = new Timestamp(1970, 1, 1, 0, 0, 0);
        TimeDelta d1 = new TimeDelta(t1, now);

        System.out.printf("Transcurred %11d seconds from %s\n", System.currentTimeMillis()/1000L, t1);
        System.out.println();
        System.out.printf("Transcurred %11d seconds from %s\n", d1.getTotalSeconds(), t1);
        System.out.printf("Transcurred %11d minutes from %s\n", d1.getTotalMinutes(), t1);
        System.out.printf("Transcurred %11d hours   from %s\n", d1.getTotalHours(),   t1);
        System.out.printf("Transcurred %11d days    from %s\n", d1.getTotalDays(),    t1);
        System.out.println();
        System.out.println("Transcurred " + d1 + " from " + t1);
        System.out.println();

        Timestamp t2 = new Timestamp(now.getYear(), random(1,12), random(1,28),
                                     random(0,23), random(0,59), random(0,59));
        showTimeDeltas(now,t2);
        System.out.println();

        t1 = new Timestamp(random(1950,2030), random(1,12), random(1,28),
                           random(0,23), random(0,59), random(0,59));
        t2 = new Timestamp(random(1950,2030), random(1,12), random(1,28),
                           random(0,23), random(0,59), random(0,59));
        showTimeDeltas(t1, t2);
        System.out.println();

        t2 = t1.clone();
        t2.addMinutes(30 * random(1,4));

        Event e = new Event(t1, t2, "Important meeting", "Virtual via Teams");

        System.out.println(e);
        System.out.println();

        t2 = t1.clone();
        t2.addMinutes(30 * random(1,6));
        e = new Event(t1, t2, "Beers & tapas with friends", "Bar Levante");
        System.out.println(e);
        System.out.println();
    }

    private static void showTimeDeltas(Timestamp t1, Timestamp t2)
    {
        if (t1.compareTo(t2) <= 0) {
            TimeDelta d = new TimeDelta(t1, t2);
            System.out.println("Transcurred " + d + " from " + t1 + " till " + t2);
        } else {
            TimeDelta d = new TimeDelta(t2, t1);
            System.out.println("Transcurred " + d + " from " + t2 + " till " + t1);
        }
    }

    private static int random(int min, int max)
    {
        return min + (int)((max - min + 1) * Math.random());
    }
}