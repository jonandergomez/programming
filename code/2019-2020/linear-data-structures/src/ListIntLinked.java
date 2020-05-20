package linear;

import java.util.*;

/**
 * Class for managing lists of integers by using a double-linked
 * sequence as internal representation.
 * This implementation uses an interest point instead of creating
 * iterators, which are beyond the scope of this semester.
 *
 * @author Jon Ander Gómez
 * @version 2020-05-05
 */
public class ListIntLinked
{
    // Attributes
    NodeInt first;
    NodeInt last;
    NodeInt cursor; // reference to the interest point
    int     size;

    // Constructors

    /**
     * Creates a new empty list.
     *
     */
    public ListIntLinked()
    {
        first = last = cursor = null;
        size = 0;
    }

    // Other methods
    /**
     * Returns the number of integers stored in the list.
     * <em>T(n) &isin; O(1)</em>
     *
     *
     * @return integer indicating the size of the list.
     */
    public int size()
    {
        return size;
    }

    /**
     * Returns the number of integers stored in the list.
     * <em>T(n) &isin; O(1)</em>
     *
     * @return <code>true</code> if the list is empty, <code>false</code> otherwise.
     */
    public boolean isEmpty()
    {
        return size() == 0;
    }

    /**
     * Appends a new integer after the last one in the list, if the list was
     * empty, then the new value is becomes both the first and the last.
     * <em>T(n) &isin; O(1)</em>
     *
     * @param x integer value to be appended at the end of the list.
     * @return <code>true</code> if the operation was successful, <code>false</code> otherwise.
     */
    public boolean append( int x )
    {
        NodeInt node = new NodeInt( x );

        if ( size() == 0 ) {
            first = last = cursor = node; // trivial
        } else {
            last.setNext( node ); // (1) connects the last node with the new one such that the new node is the next of the last one
            node.setPrevious( last ); // (2) connects the new node with the last one such that the last node is the previous of the new one
            last = node; // (3) updates 'last' attribute to reference the last node in the list
        }

        ++size;

        return true;
    }
    /**
     * Inserts a new element in the current position of the interest point by
     * shifting one position to the right all the values in the list from the
     * position of the cursor to the end.
     * If the list was empty or the cursor is out of the list, then the new
     * element is appended using the method <code>append()</code>.
     * <em>T(n) &isin; O(1)</em>
     *
     * @param x integer value to be inserted in the list at the current position of cursor.
     * @return <code>true</code> if the operation was successful, <code>false</code> otherwise.
     */
    public boolean insert( int x )
    {
        if ( cursor == null || isEmpty() ) return append(x);

        NodeInt node = new NodeInt( x );

        if ( cursor == first ) {

            node.setNext( first ); // (1) connects the first node so far to be the next of the new one
            first.setPrevious( node ); // (2) connects the new one to be previous of the first one so far
            first = node; // (3) updates 'first' attribute to reference the new node as the first of the list

        } else {

            NodeInt previous = cursor.getPrevious();

            node.setPrevious( previous ); // (1) connects the new node with the previous of the cursor
            node.setNext( cursor ); // (2) connects the new node with the cursor

            previous.setNext( node ); // (3) connects the previous of the cursor to have as next the new node
            cursor.setPrevious( node ); // (4) connects the cursor to have as previous the new node
        }
        // cursor = node; // updates 'cursor' attribute to reference the new inserted node

        ++size;

        return true;
    }

    /**
     * Removes the element referenced by the cursor, i.e., removes the value stored where
     * the cursor is pointing to.
     * Cursor is updated if the element removed was the last one.
     * <em>T(n) &isin; O(1)</em>
     *
     * @return <code>true</code> if the operation was successful, <code>false</code> otherwise.
     * @throws Exception if cursor is not referencing a valid element in the list or the list is empty.
     */
    public boolean delete()
        throws Exception
    {
        if ( cursor == null )
            throw new Exception( "No current element to be removed from the list!" );

        if ( isEmpty() )
            throw new Exception( "Empty list! No elements can be removed!" );

        if ( size == 1 ) {

            first = last = cursor = null;

        } else if ( cursor == first ) {

            first = first.getNext(); // (a.1)
            first.setPrevious(null); // (a.2)

            cursor = first; // (a.3)

        } else if ( cursor == last ) {

            last = last.getPrevious(); // (b.1)
            last.setNext(null); // (b.2)

            cursor = last; // (b.3)

        } else {

            NodeInt previous = cursor.getPrevious();
            NodeInt     next = cursor.getNext();

            previous.setNext( next ); // (c.1)
            next.setPrevious( previous ); // (c.2)

            cursor = next; // (c.3)
        }

        --size;

        return true;
    }

    /**
     * Returns the integer stored at the current position of the cursor.
     * <em>T(n) &isin; O(1)</em>
     *
     * @return <code>int</code> at cursor position.
     * @throws Exception if cursor is not referencing a valid element in the list or the list is empty.
     */
    public int get()
        throws Exception
    {
        if ( cursor == null )
            throw new Exception( "No current element to be returned from the list!" );

        if ( isEmpty() )
            throw new Exception( "Empty list! No elements can be returned!" );

        return cursor.getValue();
    }

    /**
     * Replaces the integer at the current position of the cursor.
     * <em>T(n) &isin; O(1)</em>
     *
     * @param x the new value to be stored at cursor position.
     * @return <code>int</code> value stored at cursor position before replacing it.
     * @throws Exception if cursor is not referencing a valid element in the list or the list is empty.
     */
    public int set( int x )
        throws Exception
    {
        if ( cursor == null )
            throw new Exception( "No current element to be replaced from the list!" );

        if ( isEmpty() )
            throw new Exception( "Empty list! No elements can be replaced!" );

        int temp = cursor.getValue();
        cursor.setValue( x );

        return temp;
    }

    /**
     * Set the cursor to reference the first element of the list.
     * <em>T(n) &isin; O(1)</em>
     *
     * @return <code>true</code> if the operation was successful, <code>false</code> otherwise.
     */
    public boolean begin()
    {
        cursor = first;

        return cursor != null;
    }
    /**
     * Moves the cursor to reference the next element of the list.
     * <em>T(n) &isin; O(1)</em>
     *
     * @return <code>true</code> if the operation was successful, <code>false</code> otherwise.
     */
    public boolean next()
    {
        if ( cursor != null )
            cursor = cursor.getNext();

        return cursor != null;
    }
    /**
     * Moves the cursor to reference the previous element of the list.
     * <em>T(n) &isin; O(1)</em>
     *
     * @return <code>true</code> if the operation was successful, <code>false</code> otherwise.
     */
    public boolean previous()
    {
        if ( cursor != null )
            cursor = cursor.getPrevious();

        return cursor != null;
    }
    /**
     * Set the cursor to reference the last element of the list.
     * <em>T(n) &isin; O(1)</em>
     *
     * @return <code>true</code> if the operation was successful, <code>false</code> otherwise.
     */
    public boolean end()
    {
        cursor = last;

        return cursor != null;
    }


    /**
     * Moves the cursor to reference the first appearance of <code>x</code>
     * in the list, or to a non valid position if <code>x</code> is not in the list.
     * <em>T(n) &isin; O(n)</em>
     *
     * @param x the value to be found in the list.
     * @return <code>true</code> if the operation was successful, <code>false</code> otherwise.
     */
    public boolean find( int x )
    {
        try {
            for( boolean valid=begin(); valid; valid=next() ) {
                if ( get() == x ) return true;
            }
        }
        catch( Exception e )
        {
            cursor = null;
        }

        return false;
    }

    /**
     * Inserts <code>x</code> in the corresponding position within the list in order
     * to guarantee the list remains sorted in ascending order.
     * This methods asumes the list was already sorted.
     * <em>T(n) &isin; O(n)</em>
     *
     * @param x integer value to be inserted in the list at the corresponding position such that
     *          the list remains sorted in ascending order.
     * @return <code>true</code> if the operation was successful, <code>false</code> otherwise.
     */
    public boolean insertInOrder( int x )
    {
        if ( isEmpty() || x > last.getValue() ) {

            return append(x);

        } else if ( x < first.getValue() ) {

            begin();

        } else {

            cursor = last;
            while( cursor != null  &&  cursor.getValue() > x ) {
                cursor = cursor.getPrevious();
            }
            if ( cursor != null ) cursor = cursor.getNext(); // needs to be corrected because the while loop ends when a value <= x has been found

        }

        return insert(x);
    }

    /**
     * Clears the contents of the list.
     * <em>T(n) &isin; O(1)</em>
     */
    public void clear()
    {
        first = last = cursor = null;
        size = 0;
    }

    /**
     * Compares the current list with the provided object if it is an object of the class <code>ListIntLinked</code>.
     * <em>T(n) &isin; O(n)</em>
     *
     * @param o reference to an object to be compared with the current list.
     *
     * @return <code>true</code> if <code>o</code> is a list and both lists have the same contents, <code>false</code> otherwise.
     */
    @Override
    public boolean equals( Object o )
    {
        if ( o instanceof ListIntLinked ) {
            ListIntLinked other = (ListIntLinked)o;

            boolean equal = this.size == other.size;

            NodeInt a = this.first;
            NodeInt b = other.first;

            for( int i=0; i < this.size && equal; i++ )
                equal = a.getValue() == b.getValue() && !( a == this.cursor ^ b == other.cursor);

            return equal;
        }

        return false;
    }

    /**
     * Returns a String representation of the current list.
     * Use with care if the list is too large the resulting string could
     * block an important amount of memory.
     * <em>T(n) &isin; O(n)</em>
     *
     * @return <code>String</code> with all the integer values stored in the list.
     */
    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();

        sb.append( "{" );
        for( NodeInt a = first; a != null; a = a.getNext() ) sb.append( " " + a.getValue() );
        sb.append( " }" );

        return sb.toString();
    }

    /**
     * Returns a new list with the same content of the current one.
     * <em>T(n) &isin; O(n)</em>
     *
     * @return <code>ListIntLinked</code>
     */
    @Override
    public ListIntLinked clone()
    {
        ListIntLinked l = new ListIntLinked();

        for( NodeInt a = first; a != null; a = a.getNext() ) l.append( a.getValue() );

        l.cursor = null;

        return l;
    }


    /**
     * Sorts in place the list in ascending order by using the merge-sort algorithm.
     */
    public void sort()
    {
        if ( size() > 1 ) {

            ListIntLinked  left = new ListIntLinked();
            ListIntLinked right = new ListIntLinked();

            try {

                for( boolean valid=this.begin(); valid;  ) {

                    left.append( this.get() );

                    valid=this.next();

                    if ( valid ) right.append( this.get() );

                    valid=this.next();
                }

                this.clear();

                 left.sort();
                right.sort();

                naturalMerge( this, left, right );

                 left.clear();
                right.clear();
            }
            catch( Exception e )
            {
                e.printStackTrace( System.err );

                throw new Error( "Unexpected error while sorting a list! The program cannot continue. " );
            }
        }
    }
    private static void naturalMerge( ListIntLinked target, ListIntLinked left, ListIntLinked right )
        throws Exception
    {
        target.clear();

        left.begin();
        right.begin();

        while( ! left.isEmpty()  &&  ! right.isEmpty() ) {

            if ( left.get() <= right.get() ) {

                target.append( left.get() );
                left.delete();
                left.begin();

            } else {

                target.append( right.get() );
                right.delete();
                right.begin();

            }
        }

        while( ! left.isEmpty() ) {
            target.append( left.get() );
            left.delete();
            left.begin();
        }
        while( ! right.isEmpty() ) {
            target.append( right.get() );
            right.delete();
            right.begin();
        }
    }

    public void selectionSort()
    {
        for( NodeInt i=this.first; i != null; i=i.getNext() ) {
            NodeInt k = i;
            for( NodeInt j=i.getNext(); j != null; j=j.getNext() ) {
                if ( j.getValue() < k.getValue() ) k = j;
            }
            if ( k.getValue() != i.getValue() ) {
                int temp = k.getValue();
                k.setValue( i.getValue() );
                i.setValue( temp );
            }
        }
    }
    public void insertionSort()
    {
        for( NodeInt i=this.first; i != null; i=i.getNext() ) {
            NodeInt k=i;
            int value_at_i=i.getValue();
            while( k.getPrevious() != null && value_at_i < k.getPrevious().getValue() ) {
                k.setValue(k.getPrevious().getValue());
                k=k.getPrevious();
            }
            if ( k != i ) k.setValue(value_at_i);
        }
    }
}
