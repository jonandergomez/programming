package etsinf.prg.agenda3;

import java.util.*;
import java.io.*;

/**
  * Class for managing the list of contacts (people) and events in any personal agenda.
  *
  * Internally, an array for contacts and another array for events are used.
  */
public class Agenda
    implements Serializable
{
    /** Array with the list of contacts. */
    private NodeContact firstContact, lastContact;
    /** Array with the list of events. */
    private NodeEvent  firstEvent, lastEvent;
    /** Number of valid elements in the array <code>people</code>. */
    private int        peopleSize;
    /** Number of valid elements in the array <code>events</code>. */
    private int        eventsSize;

    public Agenda()
    {
        firstContact = lastContact = null;
        firstEvent = lastEvent = null;
        peopleSize = 0;
        eventsSize = 0;
    }

    public int getPeopleSize() { return peopleSize; }
    public int getEventsSize() { return eventsSize; }

    public Contact [] getPeople()
    {
        Contact [] array = new Contact [peopleSize];

        int i = 0;
        for (NodeContact nc = firstContact; nc != null; nc = nc.getNext()) {
            array[i++] = nc.getContact();
        }

        return array;
    }
    public Event [] getEvents()
    {
        Event [] array = new Event [eventsSize];

        int i = 0;
        for (NodeEvent ne = firstEvent; ne != null; ne = ne.getNext()) {
            array[i++] = ne.getEvent();
        }

        return array;
    }

    public Contact getContactAt(int i)
    {
        int pos = 0;
        for (NodeContact nc = firstContact; nc != null; nc = nc.getNext()) {
            if (i == pos++) return nc.getContact();
        }
        return null;
    }
    public Event getEventAt(int i)
    {
        int pos = 0;
        for (NodeEvent ne = firstEvent; ne != null; ne = ne.getNext()) {
            if (i == pos++) return ne.getEvent();
        }
        return null;
    }

    public void add(Contact c)
    {
        if (peopleSize == 0) {
            firstContact = lastContact = new NodeContact(c);
        } else {
            NodeContact nc = new NodeContact(lastContact, c, null);
            lastContact.setNext(nc);
            lastContact = lastContact.getNext();
        }
        ++peopleSize;
    }
    public void add(Event e)
    {
        if (eventsSize == 0) {
            firstEvent = lastEvent = new NodeEvent(e);
        } else {
            NodeEvent ne = new NodeEvent(lastEvent, e, null);
            lastEvent.setNext(ne);
            lastEvent = lastEvent.getNext();
        }
        ++eventsSize;
    }
    public void addInOrder(Contact c)
    {
        if (firstContact == null) {
            // if empty simply add
            add(c);

        } else {
            NodeContact nc = firstContact;
            while (nc != null && c.compareTo(nc.getContact()) >= 0) {
                nc = nc.getNext();
            }

            if (nc == null) { // add after the last one, i.e., simply add
                add(c);
            } else if (nc == firstContact) { // add before the first one
                NodeContact newNC = new NodeContact(null, c, firstContact);
                firstContact.setPrevious(newNC);
                firstContact = newNC;
                ++peopleSize;
            } else { // add before nc
                NodeContact newNC = new NodeContact(nc.getPrevious(), c, nc);
                nc.getPrevious().setNext(newNC);
                nc.setPrevious(newNC);
                ++peopleSize;
            }
        }
    }
    public void addInOrder(Event e)
    {
        if (firstEvent == null) {
            // if empty simply add
            add(e);

        } else {
            NodeEvent ne = firstEvent;
            while (ne != null && e.getStart().compareTo(ne.getEvent().getStart()) < 0) {
                ne = ne.getNext();
            }

            if (ne == null) { // add after the last one, i.e., simply add
                add(e);
            } else if (ne == firstEvent) { // add before the first one
                NodeEvent newNE = new NodeEvent(null, e, firstEvent);
                firstEvent.setPrevious(newNE);
                firstEvent = newNE;
                ++peopleSize;
            } else {
                NodeEvent newNE = new NodeEvent(ne.getPrevious(), e, ne);
                ne.getPrevious().setNext(newNE);
                ne.setPrevious(newNE);
                ++peopleSize;
            }
        }
    }

    public boolean remove(Contact c)
    {
        NodeContact nc = find(c);

        if (nc != null) {
            if (nc == firstContact) {

                firstContact = firstContact.getNext();
                if (firstContact != null)
                    firstContact.setPrevious(null);
                else
                    lastContact = null;

            } else if (nc == lastContact) {

                lastContact = lastContact.getPrevious();
                lastContact.setNext(null);

            } else {

                nc.getPrevious().setNext(nc.getNext());
                nc.getNext().setPrevious(nc.getPrevious());
                nc.setPrevious(null); // not strictly necessary
                nc.setNext(null); // not strictly necessary
            }

            --peopleSize;

            return true;
        }
        return false;
    }
    public boolean remove(Event e)
    {
        NodeEvent ne = find(e);

        if (ne != null) {
            if (ne == firstEvent) {

                firstEvent = firstEvent.getNext();
                if (firstEvent != null)
                    firstEvent.setPrevious(null);

            } else if (ne == lastEvent) {

                lastEvent = lastEvent.getPrevious();
                if (lastEvent != null)
                    lastEvent.setNext(null);

            } else {

                ne.getPrevious().setNext(ne.getNext());
                ne.getNext().setPrevious(ne.getPrevious());
                ne.setPrevious(null); // not strictly necessary
                ne.setNext(null); // not strictly necessary
            }

            --eventsSize;

            return true;
        }
        return false;
    }

    public NodeContact find(Contact c)
    {
        for (NodeContact nc = firstContact; nc != null; nc = nc.getNext())
            if (nc.getContact().equals(c))
                return nc;

        return null;
    }
    public NodeEvent find(Event e)
    {
        for (NodeEvent ne = firstEvent; ne != null; ne = ne.getNext())
            if (ne.getEvent().equals(e))
                return ne;

        return null;
    }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();

        for (NodeContact nc = firstContact; nc != null; nc = nc.getNext())
            sb.append(nc.getContact().toString() + "\n");

        return sb.toString();
    }

    public String toStringEvents()
    {
        StringBuffer sb = new StringBuffer();

        for (NodeEvent ne = firstEvent; ne != null; ne = ne.getNext())
            sb.append(ne.getEvent().toString() + "\n");

        return sb.toString();
    }

    // BEGIN: special methods to convert into an string an array of contacts or events
    public static String toString(Object [] a, int size)
    {
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < size; ++i)
        sb.append(a[i].toString() + "\n");

        return sb.toString();
    }
    public static String toString(Object [] a)
    {
        return toString(a, a.length);
    }
    // END: special methods to convert into an string an array of contacts or events


    public Contact [] getYoungerThan(int age)
    {
        Calendar cal = Calendar.getInstance();
        Date reference = new Date(cal.get(Calendar.DAY_OF_MONTH),
                                  cal.get(Calendar.MONTH) + 1,
                                  cal.get(Calendar.YEAR));

        // counts how many people is younger than 'age'
        int counter = 0;
        for (NodeContact nc = firstContact; nc != null; nc = nc.getNext())
            if (nc.getContact().getAge(reference) < age)
                ++counter;

        // creates the array to store the people that is younger than 'age'
        Contact [] youngerPeople = new Contact [counter];

        // copies the people that is younger than 'age' to the new array
        int index = 0;
        for (NodeContact nc = firstContact; nc != null; nc = nc.getNext())
            if (nc.getContact().getAge(reference) < age)
                youngerPeople[index++] = nc.getContact();

        // returns the array with the people younger than 'age'
        return youngerPeople;
    }

    public Contact [] getOlderThan(int age)
    {
        Calendar cal = Calendar.getInstance();
        Date reference = new Date(cal.get(Calendar.DAY_OF_MONTH),
                                  cal.get(Calendar.MONTH) + 1,
                                  cal.get(Calendar.YEAR));

        // counts how many people is older than 'age'
        int counter = 0;
        for (NodeContact nc = firstContact; nc != null; nc = nc.getNext())
            if (nc.getContact().getAge(reference) > age)
                ++counter;

        // creates the array to store the people that is older than 'age'
        Contact [] olderPeople = new Contact [counter];

        // copies the people that is older than 'age' to the new array
        counter = 0;
        for (NodeContact nc = firstContact; nc != null; nc = nc.getNext())
            if (nc.getContact().getAge(reference) > age)
                olderPeople[counter++] = nc.getContact();

        // returns the array with the people older than 'age'
        return olderPeople;
    }

    public Event [] getUpcomingEvents()
    {
        Timestamp now = new Timestamp();

        // count how many elements are upcoming events
        int counter = 0;
        for (NodeEvent ne = firstEvent; ne != null; ne = ne.getNext())
            if (ne.getEvent().getStart().compareTo(now) > 0)
                ++counter;

        // creates the array to store the upcoming events
        Event [] upcoming = new Event [counter];

        // copies the upcoming events to the new array
        counter = 0;
        for (NodeEvent ne = firstEvent; ne != null; ne = ne.getNext())
            if (ne.getEvent().getStart().compareTo(now) > 0)
                upcoming[counter++] = ne.getEvent();

        // returns the array with the upcoming events
        return upcoming;
    }

    public Contact [] getPeopleWhoBornInMonth(int month)
    {
        // counts how many people born at the given month
        int counter = 0;
        for (NodeContact nc = firstContact; nc != null; nc = nc.getNext())
            if (nc.getContact().getMonthOfBirth() == month)
                ++counter;

        // creates the array to store the people that was born at the given month
        Contact [] bornAtMonth = new Contact [counter];

        // copies the people that was born at the given month
        counter = 0;
        for (NodeContact nc = firstContact; nc != null; nc = nc.getNext())
            if (nc.getContact().getMonthOfBirth() == month)
                bornAtMonth[counter++] = nc.getContact();

        // returns the array with the people that was born at the given month
        return bornAtMonth;
    }

    /**
      * Sorts the list of contacts using the insertion sort algorithm.
      */
    private void sort()
    {
        if (peopleSize <= 1) return;

        for (NodeContact i = firstContact.getNext(); i != null; i = i.getNext()) {
            NodeContact j = i;
            Contact temp = i.getContact();
            while (j != null && j.getPrevious() != null && j.getPrevious().getContact().compareTo(temp) > 0) {
                j.setContact(j.getPrevious().getContact());
                j = j.getPrevious();
            }
            j.setContact(temp);
        }
    }
    /**
      * Sorts the list of events using the selection sort algorithm.
      */
    private void sortEvents()
    {
        for (NodeEvent i = firstEvent; i != null; i = i.getNext()) {
            NodeEvent k = i;
            for (NodeEvent j = i.getNext(); j != null; j = j.getNext()) {
                if (j.getEvent().getStart().compareTo(k.getEvent().getStart()) < 0) k = j;
            }
            if (k != i) {
                Event temp = k.getEvent();
                k.setEvent(i.getEvent());
                i.setEvent(temp);
            }
        }
    }
    public Contact [] getDuplicateContacts()
    {
        sort();

        int counter = 0;

        NodeContact previous = firstContact;
        NodeContact current = firstContact.getNext();

        while (current != null) {

            if (current.getContact().equals(previous.getContact()))
                ++counter;

            previous = current;
            current = current.getNext();
        }

        Contact [] duplicates = new Contact[counter];

        counter = 0;
        previous = firstContact;
        current = firstContact.getNext();
        while (current != null) {
            if (current.getContact().equals(previous.getContact()))
                duplicates[counter++] = current.getContact();

            previous = current;
            current = current.getNext();
        }

        return duplicates;
    }
    public Event [] getDuplicateEvents()
    {
        sortEvents();

        int counter = 0;

        NodeEvent previous = firstEvent;
        NodeEvent current = firstEvent.getNext();

        while (current != null) {
            if (current.getEvent().equals(previous.getEvent()))
                ++counter;

            previous = current;
            current = current.getNext();
        }

        Event [] duplicates = new Event[counter];

        counter = 0;
        previous = firstEvent;
        current = firstEvent.getNext();
        while (current != null) {
            if (current.getEvent().equals(previous.getEvent()))
                duplicates[counter++] = current.getEvent();

            previous = current;
            current = current.getNext();
        }

        return duplicates;
    }
}
