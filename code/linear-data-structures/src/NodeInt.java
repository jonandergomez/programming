package linear;

import java.util.*;

public class NodeInt
{
    private int     value;
    private NodeInt next;
    private NodeInt previous;

    public NodeInt(NodeInt previous, int value, NodeInt next)
    {
        this.previous = previous;
        this.value = value;
        this.next = next;
    }
    public NodeInt(int value, NodeInt next)
    {
        this(null, value, next);

        /*
            ===========================
            Inserting in a simple way that leaves the 
            values in the linked sequence in the reverse
            order with respect the order the values are
            inserted.
            ===========================
            NodeInt first = null;
            first = new NodeInt(7, first);
            first = new NodeInt(13, first);
            first = new NodeInt(-3, first);
            first = new NodeInt(9, first);

            ===========================
            Non-operative way of leaving the values in the
            same order they are inserted, but every time
            the whole linked sequence is traversed till
            finding the last element in order to append
            every new element to be inserted.
            ===========================
            NodeInt first = null;

            first = new NodeInt(7, null);

            NodeInt temp = first;
            while (temp.getNext() != null) temp = temp.getNext();
            temp.setNext(new NodeInt(13, null));

            temp = first;
            while (temp.getNext() != null) temp = temp.getNext();
            temp.setNext(new NodeInt(-3, null));

            temp = first;
            while (temp.getNext() != null) temp = temp.getNext();
            temp.setNext(new NodeInt(9, null));

            ===========================
            Optimum strategy using first and last.
            ===========================
            NodeInt first = last = null;

            first = last = new NodeInt(7, null);
            last.setNext(new NodeInt(13, null)); last = last.getNext();
            last.setNext(new NodeInt(-3, null)); last = last.getNext();
            last.setNext(new NodeInt( 9, null)); last = last.getNext();
        */
    }
    public NodeInt(int value)
    {
        this(null, value, null);
    }
    public int getValue() { return value; }
    public void setValue(int v) { value = v; }

    public NodeInt getNext() { return next; }
    public void setNext(NodeInt next) { this.next = next; }

    public NodeInt getPrevious() { return previous; }
    public void setPrevious(NodeInt previous) { this.previous = previous; }

    @Override
    public String toString()
    {
        return "<" + value + ">";
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof NodeInt) {

            NodeInt other = (NodeInt)o;

            return other.value == this.value;
        }
        return false;
    }
}
