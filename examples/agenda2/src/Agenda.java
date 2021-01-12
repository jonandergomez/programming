package etsinf.prg.agenda2;

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
    private Contact [] people;
    /** Array with the list of events. */
    private Event []   events;
    /** Number of valid elements in the array <code>people</code>. */
    private int        peopleSize;
    /** Number of valid elements in the array <code>events</code>. */
    private int        eventsSize;

    public Agenda()
    {
        this(100, 100);
    }
    public Agenda(int initialPeopleSize, int initialEventsSize)
    {
        people = new Contact [initialPeopleSize];
        events = new Event [initialEventsSize];
        peopleSize = 0;
        eventsSize = 0;
    }

    public int getPeopleSize() { return peopleSize; }
    public int getEventsSize() { return eventsSize; }

    public Contact getContactAt(int i)
    {
        return people[i];
    }
    public Event getEventAt(int i)
    {
        return events[i];
    }

    private void growPeople()
    {
        Contact [] newData = new Contact[people.length * 2];

        for (int i = 0; i < people.length; ++i) newData[i] = people[i];

        people = newData;
    }
    private void growEvents()
    {
        Event [] newData = new Event[events.length * 2];

        for (int i = 0; i < events.length; ++i) newData[i] = events[i];

        events = newData;
    }

    public void add(Contact c)
    {
        if (peopleSize == people.length) growPeople();

        people[peopleSize++] = c;
    }
    public void add(Event e)
    {
        if (eventsSize == events.length) growEvents();

        events[eventsSize++] = e;
    }
    public void addInOrder(Contact c)
    {
        if (peopleSize == people.length) growPeople();

        // shift one position to the right all elements that should be
        // after the new one
        int i = peopleSize - 1;
        while (i >= 0 && c.compareTo(people[i]) < 0) {
            people[i+1] = people[i];
            --i;
        }
        people[i+1] = c;
        ++peopleSize;
    }
    public void addInOrder(Event e)
    {
        if (eventsSize == events.length) growEvents();

        // shift one position to the right all elements that should be
        // after the new one
        int i = eventsSize - 1;
        while (i >= 0 && e.getStart().compareTo(events[i].getStart()) < 0) {
            events[i + 1] = events[i];
            --i;
        }
        events[i + 1] = e;
        ++eventsSize;
    }

    public boolean remove(Contact c)
    {
        int pos = find(c);

        if (pos >= 0) {
            // shift one position to the left all the elements that were
            // after the one to be removed
            for (int i = pos + 1; i < peopleSize; i++) people[i - 1] = people[i];
            people[peopleSize - 1] = null;
            --peopleSize;

            return true;
        }
        return false;
    }
    public boolean remove(Event e)
    {
        int pos = find(e);

        if (pos >= 0) {
            // shift one position to the left all the elements that were
            // after the one to be removed
            for (int i = pos + 1; i < eventsSize; i++) events[i - 1] = events[i];
            events[eventsSize - 1] = null;
            --eventsSize;

            return true;
        }
        return false;
    }

    public int find(Contact c)
    {
        // assuming the array is sorted in ascending order, we use the Binary-Search
        int left = 0;
        int right = peopleSize - 1;

        while (left <= right) {

            int k = (left + right) / 2;

            if (people[k].equals(c)) {
                return k; // the contact was found
            } else if (people[k].compareTo(c) < 0) {
                left = k + 1; // if exist in the array, the contact should be in the right half
            } else {
                right = k - 1; // if exist in the array, the contact should be in the left half
            }
        }
        return -1;
    }
    public int find(Event e)
    {
        // not assuming the array is sorted, we use the linear search

        for (int i = 0; i < eventsSize; ++i)
            if (e.equals(events[i])) return i;

        return -1;
    }

    @Override
    public String toString()
    {
        /*
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < size; ++i)
            sb.append(people[i].toString() + "\n");

        return sb.toString();
        */
        return toString(people, peopleSize);
    }

    public String toStringEvents()
    {
        return toString(events, eventsSize);
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
        for (int i = 0; i < peopleSize; ++i)
            if (people[i].getAge(reference) < age)
                ++counter;

        // creates the array to store the people that is younger than 'age'
        Contact [] youngerPeople = new Contact [counter];

        // copies the people that is younger than 'age' to the new array
        counter = 0;
        for (int i = 0; i < peopleSize; ++i)
            if (people[i].getAge(reference) < age)
                youngerPeople[counter++] = people[i];

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
        for (int i = 0; i < peopleSize; ++i)
            if (people[i].getAge(reference) > age)
                ++counter;

        // creates the array to store the people that is older than 'age'
        Contact [] olderPeople = new Contact [counter];

        // copies the people that is older than 'age' to the new array
        counter = 0;
        for (int i = 0; i < peopleSize; ++i)
            if (people[i].getAge(reference) > age)
                olderPeople[counter++] = people[i];

        // returns the array with the people older than 'age'
        return olderPeople;
    }

    public Event [] getUpcomingEvents()
    {
        Timestamp now = new Timestamp();

        // count how many elements are upcoming events
        int counter = 0;
        for (int i = 0; i < eventsSize; ++i) {
            if (events[i].getStart().compareTo(now) > 0)
                ++counter;
        }

        // creates the array to store the upcoming events
        Event [] upcoming = new Event [counter];

        // copies the upcoming events to the new array
        counter = 0;
        for (int i = 0; i < eventsSize; ++i) {
            if (events[i].getStart().compareTo(now) > 0)
                upcoming[counter++] = events[i];
        }

        // returns the array with the upcoming events
        return upcoming;
    }

    public Contact [] getPeopleWhoBornInMonth(int month)
    {
        // counts how many people born at the given month
        int counter = 0;
        for (int i = 0; i < peopleSize; ++i)
            if (people[i].getMonthOfBirth() == month)
                ++counter;

        // creates the array to store the people that was born at the given month
        Contact [] bornAtMonth = new Contact [counter];

        // copies the people that was born at the given month
        counter = 0;
        for (int i = 0; i < peopleSize; ++i)
            if (people[i].getMonthOfBirth() == month)
                bornAtMonth[counter++] = people[i];

        // returns the array with the people that was born at the given month
        return bornAtMonth;
    }

    /**
      * Sorts the list of contacts using the insertion sort algorithm.
      */
    private void sort()
    {
        for (int i = 1; i < peopleSize; ++i) {
            int j = i;
            Contact temp = people[i];
            while (j > 0 && people[j-1].compareTo(temp) > 0) {
                people[j] = people[j - 1];
                j--;
            }
            people[j] = temp;
        }
    }
    /**
      * Sorts the list of events using the selection sort algorithm.
      */
    private void sortEvents()
    {
        for (int i = 0; i < eventsSize; ++i) {
            int k = i;
            for (int j = i + 1; j < eventsSize; ++j) {
                if (events[j].getStart().compareTo(events[k].getStart()) < 0) k = j;
            }
            if (k != i) {
                Event temp = events[k];
                events[k] = events[i];
                events[i] = temp;
            }
        }
    }
    public Contact [] getDuplicateContacts()
    {
        sort();

        int counter = 0;

        for (int i = 1; i < peopleSize; ++i)
            if (people[i].equals(people[i - 1]))
                ++counter;

        Contact [] duplicates = new Contact[counter];

        counter = 0;
        for (int i = 1; i < peopleSize; ++i)
            if (people[i].equals(people[i - 1]))
                duplicates[counter++] = people[i];

        return duplicates;
    }
    public Event [] getDuplicateEvents()
    {
        sortEvents();

        int counter = 0;

        for (int i = 1; i < eventsSize; ++i)
            if (events[i].equals(events[i - 1]))
                ++counter;

        Event [] duplicates = new Event[counter];

        counter = 0;
        for (int i = 1; i < eventsSize; ++i)
            if (events[i].equals(events[i - 1]))
                duplicates[counter++] = events[i];

        return duplicates;
    }
}
