package linear;

import java.util.*;

/**
 * Class for working with queues of integers using
 * linked sequences as internal representation.
 *
 * @author Jon Ander Gómez
 * @version 2020-04-28
 */
public class QueueIntLinked
{
    // Attributes
    int     size;
    NodeInt first;
    NodeInt last;

    // Constructors

    /**
     * Creates an empty queue.
     */
    public QueueIntLinked()
    {
        size = 0;
        first = last = null;
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
        return size;
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
     * Adds a new element on the queue after the last one.
     * <em>T(n) &isin; O(1)</em>
     *
     */
    public void push(int value)
    {
        if (first == null) {
            first = last = new NodeInt(value);
        } else {
            last.setNext(new NodeInt(value));
            last = last.getNext();
        }
        ++size;
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
        if (size() == 0)
            throw new Exception("Queue Underflow");

        int temporary_value = first.getValue();
        first = first.getNext();
        --size;
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

        return first.getValue();
    }

    /**
     * Returns if the provided object is an object of the class QueueIntLinked
     * and the contents is equal to the contents of this queue.
     * <em>T(n) &isin; O(size)</em>
     *
     * @return <code>true</code> if both queues contain the same values and in the same order,
               <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object o)
    {
        if (o instanceof QueueIntLinked) {
            QueueIntLinked other = (QueueIntLinked)o;

            if (this.size() == 0 && other.size() == 0) return true;

            if (this.size() != other.size()) return false;

            NodeInt a = this.first;
            NodeInt b = other.first;

            while (a != null) {
                if (a.getValue() != b.getValue()) return false;

                a = a.getNext();
                b = b.getNext();
            }
            return true;
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
        NodeInt a = this.first;
        while (a != null) {
            sb.append(" " + a.getValue());
            a = a.getNext();
        }
        sb.append(" }");

        return sb.toString();
    }

    /**
     * Returns a new queue which is a copy of the current queue.
     * <em>T(n) &isin; O(size)</em>
     *
     * @return QueueIntLinked with the same contents of the current queue.
     */
    @Override
    public QueueIntLinked clone()
    {
        QueueIntLinked newQueue = new QueueIntLinked();

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

    // from second mid term exam of academic year 2018/2019
    public void split(int x)
    {
        NodeInt previous = null;
        NodeInt current = this.first;

        // Search
        while (current != null && current.getValue() != x) {
            previous = current;
            current = current.getNext();
        }

        // If successful, then do the insertion
        if (current != null) {
            current.setValue(x / 2 + x % 2);
            if (current == this.first) {
                this.first = new NodeInt(x / 2, current);
            } else {
                previous.setNext(new NodeInt(x / 2, current));
            }
            this.size++;
        }
    }
    public static void split(QueueIntLinked q, int x)
    {
        int counter = q.size();
        boolean found = false;

        while (--counter >= 0 && ! found) {
            int value = q.pop();
            if (value == x) {
                found = true;
            } else {
                q.push(value);
            }
        }
        if (found) {
            q.push(x / 2);
            q.push(x / 2 + x % 2);
        }

        while (--counter >= 0) q.push(q.pop());
    }

    // from the retake of academic year 2018/2019
    public static void moveBack(QueueIntLinked q, int x)
    {
        boolean found = false;
        int counter = q.size();

        while (--counter >= 0) {
            int value = q.pop();
            if (value == x) {
                found = true;
            } else {
                q.push(value);
            }
        }
        if (found) {
            q.push(x);
        }
    }
    public void moveBack(int x)
    {
        NodeInt previous = null;
        NodeInt current = this.first;

        while (current != null && current.getValue() != x) {
            previous = current;
            current = current.getNext();
        }

        if (current != null && current != last) {
            if (current == this.first) {
                this.first = current.getNext();
            } else {
                previous.setNext(current.getNext());
            }
            current.setNext(null);
            last.setNext(current);
            last = current;
        }
    }

    // from retake of academic year 2017/2018
    public QueueIntLinked splitQueue()
    {
        QueueIntLinked newQ = new QueueIntLinked();

        NodeInt previous = null;
        NodeInt current = this.first;
        for (int i = 0; i < this.size() / 2; i++) {
            previous = null;
            current = current.getNext();
        }

        newQ.first = current;
        newQ.last = this.last;
        this.last = previous;
        newQ.size = this.size / 2 + this.size % 2;
        this.size = this.size / 2;
        previous.setNext(null);
        newQ.first.setPrevious(null);

        return newQ;
    }
}
