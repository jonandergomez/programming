package etsinf.prg.agenda2;

/**
 * Class designed to represent events to be included in the calendar of an agenda.
 */
public class Event
{
    /** Starting time with detail at the level of minutes. */
    private Timestamp start;
    /** Ending time with detail at the level of minutes. */
    private Timestamp end;
    /** Title of the event. */
    private String title;
    /** Location where the event will take place. */
    private String location;
    /** Number of expected attendants.
        In future and more complex implementations this will be a list of
        objects of the class <code>Contact</code> not implemented in this version.
    */
    private int attendants;

    /** Constructor that creates a new object of the class <code>Event</code>
      * given starting and ending times, the title and the location.
      * Initially the number of attendants is set to 1, i.e. the organiser, and
      * as people confirms this attribute will be increased thanks to method
      * <code>addAttendant()</code>.
      *
      * @param start The reference to an object of the class
      *                  <code>Timestamp</code> with the time the
      *                  event will start.
      * @param end The reference to an object of the class
      *                  <code>Timestamp</code> with the time the
      *                  event will finish.
      * @param title The reference to an object of the class
      *               <code>String</code> with the title of the event.
      * @param location The reference to an object of the class
      *               <code>String</code> with the location of the event.
      */
    public Event(Timestamp start, Timestamp end, String title, String location)
    {
        this.start = start.clone(); // clone the object to avoid problems if the original object is modified
        this.start.setSeconds(0);
        this.end = end.clone(); // clone the object to avoid problems if the original object is modified
        this.end.setSeconds(0);
        this.title = title; // no necessary to make a copy because String objects are unmutable
        this.location = location; // no necessary to make a copy because String objects are immutable
        this.attendants = 1;
    }

    /** Returns a copy of the starting time of this object to avoid uncontrolled changes.
      *
      * @return A reference to a new object of the class <code>Timestamp</code>
      *         created when invoking this method.
      */
    public Timestamp getStart() { return start.clone(); }

    /** Returns a copy of the ending time of this object to avoid uncontrolled changes.
      *
      * @return A reference to a new object of the class <code>Timestamp</code>
      *         created when invoking this method.
      */
    public Timestamp getEnd() { return end.clone(); }

    /** Returns the title of the event.
      *
      * @return A reference to a the object of the class <code>String</code>
      *         containing the title of this event.
      */
    public String getTitle() { return title; }

    /** Returns the location of the event.
      *
      * @return A reference to a the object of the class <code>String</code>
      *         containing the location of this event.
      */
    public String getLocation() { return location; }

    /** Returns the number of attendants.
      *
      * @return An integer: The number of attendants to the event.
      */
    public int getAttendants() { return attendants; }

    /** Returns the duration of the event.
      *
      * @return A reference to a new object of the class <code>TimeDelta</code>
      *         created when this method is invoked using the current values of
      *         starting and ending times of this event.
      */
    public TimeDelta getDuration() { return new TimeDelta(start, end); }

    /** Changes the starting time.
      * @param newStart Reference to an object of the class <code>Timestamp</code>
      *                 with the new value for the starting time.
      */
    public void setStart(Timestamp newStart)
    {
        this.start = newStart.clone();
        this.start.setSeconds(0);
    }

    /** Changes the ending time.
      * @param newEnd Reference to an object of the class <code>Timestamp</code>
      *               with the new value for the ending time.
      */
    public void setEnd(Timestamp newEnd)
    {
        this.end = newEnd.clone();
        this.end.setSeconds(0);
    }

    /** Changes the title.
      * @param newTitle Reference to an object of the class <code>String</code>
      *                 with the new text for the title.
      */
    public void setTitle(String newTitle) { this.title = newTitle; }

    /** Changes the location.
      * @param newLocation Reference to an object of the class <code>String</code>
      *                    with the new description of the location.
      */
    public void setLocation(String newLocation) { this.location = newLocation; }

    /** Increseases the number of attendants to this event. */
    public void addAttendant() { ++attendants; }

    /** Returns a string representation of the event.
      *
      * @return A reference to a new object of the class <code>String</code>
      *         created when this method is invoked using the current values of
      *         the attributes of this event.
      */
    public String toString()
    {
        return title + " @ " + location + "\n"
             + "     starting at " + start.toString() + "\n"
             + "    finishing at " + end.toString();
    }
}
