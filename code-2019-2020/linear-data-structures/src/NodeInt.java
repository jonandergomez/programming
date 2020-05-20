package linear;

import java.util.*;

public class NodeInt
{
    private int     value;
    private NodeInt next;
    private NodeInt previous;

    public NodeInt( NodeInt previous, int value, NodeInt next )
    {
        this.previous = previous;
        this.value = value;
        this.next = next;
    }
    public NodeInt( int value, NodeInt next )
    {
        this( null, value, next );
    }
    public NodeInt( int value )
    {
        this(null,value,null);
    }
    public int getValue() { return value; }
    public void setValue( int v ) { value = v; }

    public NodeInt getNext() { return next; }
    public void setNext( NodeInt next ) { this.next = next; }

    public NodeInt getPrevious() { return previous; }
    public void setPrevious( NodeInt previous ) { this.previous = previous; }

    @Override
    public String toString()
    {
        return "<" + value + ">";
    }

    @Override
    public boolean equals( Object o )
    {
        if ( o instanceof NodeInt ) {

            NodeInt other = (NodeInt)o;

            return other.value == this.value;
        }
        return false;
    }
}
