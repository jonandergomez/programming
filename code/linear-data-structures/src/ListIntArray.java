package linear;

import java.util.*;

/**
 * Class for managing lists of integers by using an array as
 * internal representation.
 * Obviously, the implementation of this class is not efficient,
 * we did it for academic purposes. The goal is to illustrate
 * students how to do it, and why it is inefficient because of
 * data movements that can be avoided when using linked sequences.
 * Additionally, this implementation uses an interest point
 * instead of creating iterators, which are beyond the scope
 * of this semester.
 *
 * @author Jon Ander Gómez
 * @version 2020-05-05
 */
public class ListIntArray
{
    // Attributes
    int []  data;
    int     size;
    int     cursor; // index of the interest point

    // Constructors

    /**
     * Creates a new empty list with capacity to store up to 100 elements.
     */
    public ListIntArray()
    {
        this(100);
    }

    /**
     * Creates a new empty list with the capacity specified as argument.
     *
     * @param capacity Initial capacity of the list.
     */
    public ListIntArray(int capacity)
    {
        data = new int [capacity];
        size = 0;
        cursor = -1; // none element is the current element or interest point
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
     * Increases the capacity of the internal array by moving the contents
     * to a new one, then the new array is used.
     * <em>T(n) &isin; O(n)</em>
     */
    private void augmentCapacity()
    {
        int [] temp = new int [ data.length + 1000 ];

        for (int i = 0; i < size; i++) temp[i] = data[i];

        data = temp;
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
        if (size() == data.length) augmentCapacity();

        data[size++] = x;

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
        if (cursor < 0 || cursor >= size || isEmpty())
            return append(x);

        if (size() == data.length) augmentCapacity();

        for (int i = size; i > 0 && i > cursor; i--) data[i] = data[i - 1];

        data[cursor] = x;
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
        if (cursor < 0 || cursor >= size)
            throw new Exception("No current element to be removed from the list!");

        if (isEmpty())
            throw new Exception("Empty list! No elements can be removed!");

        for (int i = cursor + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        if (cursor == --size) --cursor;

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
        if (cursor < 0 || cursor >= size)
            throw new Exception("No current element to be returned from the list!");

        if (isEmpty())
            throw new Exception("Empty list! No elements can be returned!");

        return data[cursor];
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
        if (cursor < 0 || cursor >= size)
            throw new Exception("No current element to be replaced from the list!");

        if (isEmpty())
            throw new Exception("Empty list! No elements can be replaced!");

        int temp = data[cursor];
        data[cursor] = x;

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
        if (isEmpty()) {
            cursor = -1;
            return false;
        }
        cursor = 0;
        return true;
    }
    /**
     * Moves the cursor to reference the next element of the list.
     * <em>T(n) &isin; O(1)</em>
     *
     * @return <code>true</code> if the operation was successful, <code>false</code> otherwise.
     */
    public boolean next()
    {
        return ++cursor < size;
    }
    /**
     * Moves the cursor to reference the previous element of the list.
     * <em>T(n) &isin; O(1)</em>
     *
     * @return <code>true</code> if the operation was successful, <code>false</code> otherwise.
     */
    public boolean previous()
    {
        return --cursor >= 0;
    }
    /**
     * Set the cursor to reference the last element of the list.
     * <em>T(n) &isin; O(1)</em>
     *
     * @return <code>true</code> if the operation was successful, <code>false</code> otherwise.
     */
    public boolean end()
    {
        if (isEmpty()) {
            cursor = -1;
            return false;
        }
        cursor = size - 1;
        return true;
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
            cursor = -1;
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
        if (isEmpty()) return append(x);

        if (size() == data.length) augmentCapacity();

        int i = size;
        while (i > 0 && data[i - 1] > x) {
            data[i] = data[i - 1];
            i--;
        }
        data[i] = x;
        ++size;

        cursor = i; // cursor references last inserted element

        return true;
    }

    /**
     * Clears the contents of the list.
     * <em>T(n) &isin; O(1)</em>
     */
    public void clear()
    {
        cursor = -1;
        size = 0;
    }

    /**
     * Compares the current list with the provided object if it is an object of the class <code>ListIntArray</code>.
     * <em>T(n) &isin; O(n)</em>
     *
     * @param o reference to an object to be compared with the current list.
     *
     * @return <code>true</code> if <code>o</code> is a list and both lists have the same contents, <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object o)
    {
        if (o instanceof ListIntArray) {
            ListIntArray other = (ListIntArray)o;

            boolean equal = this.size == other.size;

            for (int i = 0; i < this.size && equal; i++)
                equal = this.data[i] == other.data[i];

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
        for (int i = 0; i < size; i++) sb.append(" " + this.data[i]);
        sb.append(" }");

        return sb.toString();
    }

    /**
     * Returns a new list with the same content of the current one.
     * <em>T(n) &isin; O(n)</em>
     *
     * @return <code>ListIntArray</code>
     */
    @Override
    public ListIntArray clone()
    {
        ListIntArray l = new ListIntArray(this.data.length);

        for (int i = 0; i < this.size; i++) l.data[i] = this.data[i];

        l.size = this.size;

        l.cursor = this.cursor; // or l.cursor = -1;

        return l;
    }
}
