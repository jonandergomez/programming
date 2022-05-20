package linear;

import java.util.*;

/**
 * Class for working with queues of integers using
 * arrays as internal representation.
 * This implementation uses a circular buffer thanks
 * to modular arithmetics for updating the indexes which
 * refer to first and last elements on the queue.
 * As it can be seen in the push() method, the maximum capacity
 * of the queue is one element less than the size of the array
 * used as internal representation of the queue.
 *
 * @author Jon Ander Gómez
 * @version 2020-04-28
 */
public class QueueIntArray
{
    // Attributes
    int [] buffer;
    int first;
    int last;

    // Constructors

    /**
     * Creates an empty array with capacity for 100 elements.
     */
    public QueueIntArray()
    {
        this(100);
    }
    /**
     * Creates an empty array with the given capacity.
     *
     * @param size Capacity of the internal array.
     */
    public QueueIntArray(int size)
    {
        buffer = new int [size];
        first = 0;
        last = (first - 1 + buffer.length) % buffer.length; // buffer.length-1
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
        return (last - first + 1 + buffer.length) % buffer.length;
    }

    /**
     * Returns if the queue is empty.
     * <em>T(n) &isin; O(1)</em>
     *
     * @return <code>true</code> if queue is empty, <code>false</code> otherwise.
     */
    public boolean isEmpty()
    {
        return 0 == size();
    }

    /**
     * Appends a new element at the end of the queue.
     * <em>T(n) &isin; O(1)</em>
     *
     * @throws Exception for indicating queue overflow is no space left on internal array.
     */
    public void push(int value)
        throws Exception
    {
        // maximum size is buffer.length-1 because it could happen that
        // (last+1)%buffer.length == first, whose meaning is that the queue
        // is empty. This is the limitation of using modular arithmetics.
        if (size() == buffer.length - 1)
            throw new Exception("Queue Overflow");

        last = (last + 1) % buffer.length;
        buffer[last] = value;
    }

    /**
     * Returns and removes the first element in the queue.
     * <em>T(n) &isin; O(1)</em>
     *
     * @return Integer value stored at the first position of the queue.
     * @throws Exception for indicating queue underflow if queue is empty.
     */
    public int pop()
        throws Exception
    {
        if (size() == 0)
            throw new Exception("Queue Underflow");

        int temporary_value = buffer[first];
        first = (first + 1) % buffer.length;
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
        if (size() == 0)
            throw new Exception("Queue Underflow");

        return buffer[first];
    }

    /**
     * Returns if the provided object is an an QueueIntArray and the contents is
     * equal to the contents of this queue.
     * <em>T(n) &isin; O(size)</em>
     *
     * @return <code>true</code> if both queues contain the same values and in the same order,
               <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object o)
    {
        if (o instanceof QueueIntArray) {
            QueueIntArray other = (QueueIntArray)o;

            if (this.size() == 0 && other.size() == 0) return true;

            if (this.size() != other.size()) return false;

            try {
                boolean are_equal = true;
                for (int i = 0; i < this.size(); i++) {

                    are_equal = are_equal && (this.front() == other.front());

                    this.push(this.pop());
                    other.push(other.pop());
                }

                return are_equal;
            }
            catch (Exception e) {
                e.printStackTrace(System.err);
                throw new Error("Unexpected exception here!");
            }
        }
        return false;
    }
    public boolean equals_2(Object o)
    {
        if (o instanceof QueueIntArray) {
            QueueIntArray other = (QueueIntArray)o;

            if (this.size() == 0 && other.size() == 0) return true;

            if (this.size() != other.size()) return false;

            try {
                boolean are_equal = true;
                int i1 = this.first;
                int i2 = other.first;
                for (int i = 0; i < this.size() && are_equal; i++) {

                    are_equal = are_equal && (this.buffer[i1] == other.buffer[i2]);

                    i1 = (i1 + 1) % this.buffer.length;
                    i2 = (i2 + 1) % other.buffer.length;
                }

                return are_equal;
            }
            catch (Exception e) {
                e.printStackTrace(System.err);
                throw new Error("Unexpected exception here!");
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
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        try {
            for (int i = 0; i < size(); i++) {
                sb.append(" " + this.front());
                this.push(this.pop());
            }
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
            throw new Error("Unexpected exception here!");
        }
        sb.append(" }");

        return sb.toString();
    }

    /**
     * Returns a new queue which is a copy of the current queue.
     * <em>T(n) &isin; O(size)</em>
     *
     * @return QueueIntArray with the same contents of the current queue.
     */
    @Override
    public QueueIntArray clone()
    {
        QueueIntArray newQueue = new QueueIntArray(buffer.length);

        try {
            for (int i = 0; i < size(); i++) {
                newQueue.push(this.front());
                this.push(this.pop());
            }
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
            throw new Error("Unexpected exception here!");
        }

        return newQueue;
    }


    public boolean isContained(int x)
        throws Exception
    {
        boolean wasFound = false;

        for (int i = 0; i < this.size(); i++) {

            wasFound = wasFound || (this.front() == x);

            this.push(this.pop());
        }
       return wasFound;
    }
}
