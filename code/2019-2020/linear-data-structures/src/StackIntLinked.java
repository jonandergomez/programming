package linear;

import java.util.*;

/**
 * Class for working with stacks of integers using
 * linked sequences as internal representation.
 *
 * @author Jon Ander Gómez
 * @version 2020-04-22
 */
public class StackIntLinked
{
    // Attributes
    int     size;
    NodeInt top;
    // Constructors

    /**
     * Creates an empty array with capacity for 100 elements.
     */
    public StackIntLinked()
    {
        size = 0;
        top = null;
    }

    // Methods

    /**
     * Returns the number of elements stored in the stack.
     * <em>T(n) &isin; O(1)</em>
     *
     * @return Integer indicating the stack size.
     */
    public int size()
    {
        return size;
    }

    /**
     * Returns if the stack is empty.
     * <em>T(n) &isin; O(1)</em>
     *
     * @return <code>true</code> if stack is empty, <code>false</code> otherwise.
     */
    public boolean isEmpty()
    {
        return 0 == size();
    }

    /**
     * Adds a new element on the top of the stack.
     * <em>T(n) &isin; O(1)</em>
     *
     * @throws Exception for indicating stack overflow is no space left on internal array.
     */
    public void push( int value )
        throws Exception
    {
        top = new NodeInt( value, top );
        ++size;
    }

    /**
     * Returns and removes the element on top of the stack.
     * <em>T(n) &isin; O(1)</em>
     *
     * @return Integer value stored at top of the stack.
     * @throws Exception for indicating stack underflow if stack is empty.
     */
    public int pop()
        throws Exception
    {
        if ( size() == 0 )
            throw new Exception( "Stack Underflow" );

        int temporary_value = top.getValue();
        top = top.getNext();
        --size;
        return temporary_value;
    }

    /**
     * Returns but does not remove the element on top of the stack.
     * <em>T(n) &isin; O(1)</em>
     *
     * @return Integer value stored at top of the stack.
     * @throws Exception for indicating stack underflow if stack is empty.
     */
    public int top()
        throws Exception
    {
        if ( size() == 0 )
            throw new Exception( "Stack Underflow" );

        return top.getValue();
    }

    /**
     * Returns if the provided object is an an StackIntLinked and the contents is
     * equal to the contents of this stack.
     * <em>T(n) &isin; O(size)</em>
     *
     * @return <code>true</code> if both stacks contain the same values and in the same order,
               <code>false</code> otherwise.
     */
    @Override
    public boolean equals( Object o )
    {
        if ( o instanceof StackIntLinked ) {
            StackIntLinked other = (StackIntLinked)o;

            NodeInt a = this.top;
            NodeInt b = other.top;

            while( a != null  ||  b != null ) {
                if ( a == null || b == null ) return false;
                if ( a.getValue() != b.getValue() ) return false;
                a = a.getNext();
                b = b.getNext();
            }

            return true;
        }
        return false;
    }

    /**
     * Returns an string as the representation of the current stack.
     * <em>T(n) &isin; O(size)</em>
     *
     * @return String with a representation of the current stack.
     */
    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append( "{" );
        // complete here
        NodeInt a = top;
        while( a != null ) {
            sb.append( " " + a.getValue() );
            a = a.getNext();
        }
        sb.append( " }" );

        return sb.toString();
    }

    /**
     * Returns a new stack which is a copy of the current stack.
     * <em>T(n) &isin; O(size)</em>
     *
     * @return StackIntLinked with the same contents of the current stack.
     *
     */
    @Override
    public StackIntLinked clone()
    {
        StackIntLinked newStack = new StackIntLinked();

        clone( newStack );

        return newStack;
    }
    /**
     * Private method for cloning a stack tacking advantage of
     * the call stack of the system.
     */
    private void clone( StackIntLinked newStack )
    {
        if ( ! this.isEmpty() ) {
            try {
                // forward path of the recursive method
                int value = this.pop();

                // recursive call
                clone( newStack );

                // backward path of the recursive method
                newStack.push( value );
                this.push( value );
            }
            catch( Exception e ) {
                e.printStackTrace( System.err );
                throw new Error( "Unexpected exception!" );
            }
        }
    }
}
