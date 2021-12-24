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
public class ListIntLinked2
    implements Iterable
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
    public ListIntLinked2()
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
    public boolean append(int x)
    {
        NodeInt node = new NodeInt(x);

        if (size() == 0) {
            first = last = cursor = node; // trivial
        } else {
            last.setNext(node); // (1) connects the last node with the new one such that the new node is the next of the last one
            node.setPrevious(last); // (2) connects the new node with the last one such that the last node is the previous of the new one
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
     * <em>T(n) &isin; O(n)</em>
     *
     * @param x integer value to be inserted in the list at the current position of cursor.
     * @return <code>true</code> if the operation was successful, <code>false</code> otherwise.
     */
    public boolean insert(int x)
    {
        if (cursor == null || isEmpty()) return append(x);

        NodeInt node = new NodeInt(x);

        if (cursor == first) {

            node.setNext(first); // (1) connects the first node so far to be the next of the new one
            first.setPrevious(node); // (2) connects the new one to be previous of the first one so far
            first = node; // (3) updates 'first' attribute to reference the new node as the first of the list

        } else {

            NodeInt previous = cursor.getPrevious();

            node.setPrevious(previous); // (1) connects the new node with the previous of the cursor
            node.setNext(cursor); // (2) connects the new node with the cursor

            previous.setNext(node); // (3) connects the previous of the cursor to have as next the new node
            cursor.setPrevious(node); // (4) connects the cursor to have as previous the new node
        }
        // cursor = node; // updates 'cursor' attribute to reference the new inserted node

        ++size;

        return true;
    }

    /**
     * Removes the element referenced by the cursor, i.e., removes the value stored where
     * the cursor is pointing to.
     * Cursor is updated if the element removed was the last one.
     * <em>T(n) &isin; O(n)</em>
     *
     * @return <code>true</code> if the operation was successful, <code>false</code> otherwise.
     * @throws Exception if cursor is not referencing a valid element in the list or the list is empty.
     */
    public boolean delete()
        throws Exception
    {
        if (cursor == null)
            throw new Exception("No current element to be removed from the list!");

        if (isEmpty())
            throw new Exception("Empty list! No elements can be removed!");

        if (size == 1) {

            first = last = cursor = null;

        } else if (cursor == first) {

            first = first.getNext(); // (a.1)
            first.setPrevious(null); // (a.2)

            cursor = first; // (a.3)

        } else if (cursor == last) {

            last = last.getPrevious(); // (b.1)
            last.setNext(null); // (b.2)

            cursor = last; // (b.3)

        } else {

            NodeInt previous = cursor.getPrevious();
            NodeInt     next = cursor.getNext();

            previous.setNext(next); // (c.1)
            next.setPrevious(previous); // (c.2)

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
        if (cursor == null)
            throw new Exception("No current element to be returned from the list!");

        if (isEmpty())
            throw new Exception("Empty list! No elements can be returned!");

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
    public int set(int x)
        throws Exception
    {
        if (cursor == null)
            throw new Exception("No current element to be replaced from the list!");

        if (isEmpty())
            throw new Exception("Empty list! No elements can be replaced!");

        int temp = cursor.getValue();
        cursor.setValue(x);

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
        if (cursor != null)
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
        if (cursor != null)
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
    public boolean find(int x)
    {
        try {
            for (boolean valid = begin(); valid; valid = next()) {
                if (get() == x) return true;
            }
        }
        catch (Exception e)
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
    public boolean insertInOrder(int x)
    {
        if (isEmpty() || x > last.getValue()) {

            return append(x);

        } else if (x < first.getValue()) {

            begin();

        } else {

            cursor = last;
            while (cursor != null  &&  cursor.getValue() > x) {
                cursor = cursor.getPrevious();
            }
            if (cursor != null) cursor = cursor.getNext(); // needs to be corrected because the while loop ends when a value <= x has been found

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
    public boolean equals(Object o)
    {
        if (o instanceof ListIntLinked2) {
            ListIntLinked2 other = (ListIntLinked2)o;

            boolean equal = this.size == other.size;

            NodeInt a = this.first;
            NodeInt b = other.first;

            for (int i = 0; i < this.size && equal; i++)
                equal = a.getValue() == b.getValue() && ! (a == this.cursor ^ b == other.cursor);

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

        sb.append("{");
        for (NodeInt a = first; a != null; a = a.getNext()) sb.append(" " + a.getValue());
        sb.append(" }");

        return sb.toString();
    }

    /**
     * Returns a new list with the same content of the current one.
     * <em>T(n) &isin; O(n)</em>
     *
     * @return <code>ListIntLinked</code>
     */
    @Override
    public ListIntLinked2 clone()
    {
        ListIntLinked2 l = new ListIntLinked2();

        for (NodeInt a = first; a != null; a = a.getNext()) l.append(a.getValue());

        l.cursor = null;

        return l;
    }


    /**
     * Sorts in place the list in ascending order by using the merge-sort algorithm.
     */
    public void sort()
    {
        if (size() > 1) {

            ListIntLinked2  left = new ListIntLinked2();
            ListIntLinked2 right = new ListIntLinked2();

            NodeInt node = this.first;
            for (int i = 0; i < size / 2; i++) node = node.getNext();

            left.first = this.first;
            left.last = node.getPrevious();
            left.cursor = null;
            left.size = this.size / 2;
            right.first = node;
            right.last = this.last;
            right.size = this.size - left.size;

            left.last.setNext(null);
            right.first.setPrevious(null);

            this.clear();

             left.sort();
            right.sort();

            if (left.last.getValue() <= right.first.getValue()) {

                left.last.setNext(right.first);
                right.first.setPrevious(left.last);
                this.first = left.first;
                this.last = right.last;
                this.size = left.size + right.size;

            } else {
                naturalMerge(this, left, right);
            }

             left.clear();
            right.clear();
        }
    }
    private static void naturalMerge(ListIntLinked2 target, ListIntLinked2 left, ListIntLinked2 right)
    {
        target.clear();

        NodeInt a = left.first;
        NodeInt b = right.first;

        target.size = left.size + right.size;

        while (a != null || b != null) {

            if (b == null || (a != null && a.getValue() <= b.getValue())) {

                if (target.first == null) {
                    target.first = target.last = a;
                } else {
                    target.last.setNext(a);
                    a.setPrevious(target.last);
                    target.last = a;
                }
                a = a.getNext();
                target.last.setNext(null);
                a.setPrevious(null);

            } else {

                if (target.first == null) {
                    target.first = target.last = b;
                } else {
                    target.last.setNext(b);
                    b.setPrevious(target.last);
                    target.last = b;
                }
                b = b.getNext();
                target.last.setNext(null);
                b.setPrevious(null);
            }
        }
    }

    /** This is an example of how to build an iterator, but not useful for non-generic classes. */
    @Override
    /*
    public Iterator<Integer> iterator()
    {
        ListIntLinked2 theList = this;

        PrimitiveIterator.OfInt it = new PrimitiveIterator.OfInt() {

            NodeInt cursor = theList.first;

            @Override
            public Integer next()
            {
                int value = cursor.getValue();
                cursor = cursor.getNext();
                return value;
            }
            @Override
            public int nextInt()
            {
                int value = cursor.getValue();
                cursor = cursor.getNext();
                return value;
            }

            @Override
            public boolean hasNext()
            {
                return cursor != null;
            }
        };

        return it;
    }
    */
    public Iterator<Integer> iterator()
    {
        ListIntLinked2 theList = this;

        ListIterator<Integer> it = new ListIterator<Integer>() {

            NodeInt cursor = theList.first;
            int index = 0;

            @Override
            public void add(Integer x)
            {
                NodeInt saved_cursor = theList.cursor;
                theList.cursor = cursor;
                theList.insert(x);
                theList.cursor = saved_cursor;
            }

            @Override
            public boolean hasNext()
            {
                return cursor != null;
            }
            @Override
            public boolean hasPrevious()
            {
                return cursor != null;
            }
            @Override
            public Integer next()
            {
                int value = cursor.getValue();
                cursor = cursor.getNext();
                ++index;
                return value;
            }

            @Override
            public int nextIndex()
            {
                return index + 1;
            }

            @Override
            public Integer previous()
            {
                int value = cursor.getValue();
                cursor = cursor.getPrevious();
                --index;
                return value;
            }

            @Override
            public int previousIndex()
            {
                return index - 1;
            }

            @Override
            public void remove()
            {
                if (cursor == null) throw new NoSuchElementException();

                try {
                    NodeInt saved_cursor = theList.cursor;
                    theList.cursor = cursor;
                    theList.delete();
                    cursor = theList.cursor;
                    if (saved_cursor != cursor) {
                        theList.cursor = saved_cursor;
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace(System.err);
                }
            }

            @Override
            public void set(Integer x)
            {
                cursor.setValue(x);
            }
        };

        return it;
    }

    public static void main(String [] args)
    {
        Random r = new Random();

        ListIntLinked2 l = new ListIntLinked2();

        for (int i = 0; i < 10; i++) l.insertInOrder(r.nextInt(100));

        //System.out.println(l + "\n");
        printList(l);

        Iterator it = l.iterator();

        printList(l);
        while (it.hasNext()) {
            it.remove();
            it.next();
            printList(l);
        }
    }
    private static void printList(ListIntLinked2 l)
    {
        // We have to use Object because ListIntLinked2 is not a generic class from which
        // the data type can be deduced by the compiler at compile time.
        //
        for (Object x : l)
            System.out.printf(" %d", x);

        System.out.println();
    }
}
