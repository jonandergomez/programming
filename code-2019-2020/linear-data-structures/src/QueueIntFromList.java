package linear;

import java.util.*;

/**
 * Class for working with queues of integers using
 * a linked list as container.
 *
 * @author Jon Ander Gómez
 * @version 2020-05-19
 */
public class QueueIntFromList
{
    // Attributes
    ListIntLinked   list;

    // Constructors

    /**
     * Creates an empty queue.
     */
    public QueueIntFromList()
    {
        list = new ListIntLinked();
    }

    // Methods

    /**
     * Returns the number of elements stored in the queue.
     * <em>T(n) &isin; O(1)</em>
     *
     * @return Integer indicating the queue size.
     */
    public int size()
    {
        return list.size();
    }

    /**
     * Returns if the queue is empty.
     * <em>T(n) &isin; O(1)</em>
     *
     * @return <code>true</code> if queue is empty, <code>false</code> otherwise.
     */
    public boolean isEmpty()
    {
        return list.isEmpty();
    }

    /**
     * Adds a new element on the queue after the last one.
     * <em>T(n) &isin; O(1)</em>
     *
     */
    public void push( int value )
    {
        list.append(value);
    }

    /**
     * Returns and removes the first element of the queue.
     * <em>T(n) &isin; O(1)</em>
     *
     * @return Integer value stored at the first position of the queue.
     * @throws Exception for indicating queue underflow if queue is empty.
     */
    public int pop()
        throws Exception
    {
        if ( size() == 0 )
            throw new Exception( "Queue Underflow" );

        list.begin();
        int temporary_value = list.get();
        list.delete();

        return temporary_value;
    }

    /**
     * Returns but does not remove the first element of the queue.
     * <em>T(n) &isin; O(1)</em>
     *
     * @return Integer value stored at the first position of the queue.
     * @throws Exception for indicating queue underflow if queue is empty.
     */
    public int front()
        throws Exception
    {
        if ( size() == 0 )
            throw new Exception( "Queue Underflow" );

        list.begin();
        return list.get();
    }

    /**
     * Returns if the provided object is an object of the class QueueIntFromList
     * and the contents is equal to the contents of this queue.
     * <em>T(n) &isin; O(size)</em>
     *
     * @return <code>true</code> if both queues contain the same values and in the same order,
               <code>false</code> otherwise.
     */
    @Override
    public boolean equals( Object o )
    {
        if ( o instanceof QueueIntFromList ) {

            QueueIntFromList other = (QueueIntFromList)o;

            try {
                boolean valid = this.list.size() == other.list.size();
                valid = valid && this.list.begin() && other.list.begin();
                while( valid ) {
                    if ( this.list.get() != other.list.get() ) return false;
                    valid = this.list.next() && other.list.next();
                }
                return true;
            }
            catch( Exception e ) {
                return false;
            }

        }
        return false;
    }

    /**
     * Returns an string as the representation of the current queue.
     * <em>T(n) &isin; O(size)</em>
     *
     * @return String with a representation of the current queue.
     */
    @Override
    public String toString()
    {
        return this.list.toString();
    }

    /**
     * Returns a new queue which is a copy of the current queue.
     * <em>T(n) &isin; O(size)</em>
     *
     * @return QueueIntFromList with the same contents of the current queue.
     */
    @Override
    public QueueIntFromList clone()
    {
        QueueIntFromList newQueue = new QueueIntFromList();

        newQueue.list = this.list.clone();
        newQueue.list.begin();

        return newQueue;
    }
}
