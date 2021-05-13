package linear;

import java.util.*;

/**
 * Class for working with stacks of integers using
 * a linked list as container.
 *
 * @author Jon Ander Gómez
 * @version 2020-05-19
 */
public class StackIntFromList
{
    // Attributes
    ListIntLinked list;


    // Constructors

    /**
     * Creates an empty stack.
     */
    public StackIntFromList()
    {
        list = new ListIntLinked();
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
        return list.size();
    }

    /**
     * Returns if the stack is empty.
     * <em>T(n) &isin; O(1)</em>
     *
     * @return <code>true</code> if stack is empty, <code>false</code> otherwise.
     */
    public boolean isEmpty()
    {
        return list.isEmpty();
    }

    /**
     * Adds a new element on the top of the stack.
     * <em>T(n) &isin; O(1)</em>
     *
     * @throws Exception for indicating stack overflow is no space left on internal array.
     */
    public void push(int value)
        throws Exception
    {
        if (list.isEmpty()) {
            list.append(value);
        } else {
            list.begin(); // Sets the cursor pointing to the first element.
            list.insert(value);
        }
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
        if (size() == 0)
            throw new Exception("Stack Underflow");

        list.begin();
        int temporary_value = list.get();
        list.delete();

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
        if (size() == 0)
            throw new Exception("Stack Underflow");

        list.begin();
        return list.get();
    }

    /**
     * Returns if the provided object is an an StackIntFromList and the contents is
     * equal to the contents of this stack.
     * <em>T(n) &isin; O(size)</em>
     *
     * @return <code>true</code> if both stacks contain the same values and in the same order,
               <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object o)
    {
        if (o instanceof StackIntFromList) {
            StackIntFromList other = (StackIntFromList)o;

            if (this.size() != other.size()) return false;

            try {
                boolean valid = this.list.begin() && other.list.begin();
                while (valid) {
                    if (this.list.get() != other.list.get()) return false;
                    valid = this.list.next() && other.list.next();
                }
                return true;
            }
            catch (Exception e) {
                return false;
            }
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
        return list.toString();
    }

    /**
     * Returns a new stack which is a copy of the current stack.
     * <em>T(n) &isin; O(size)</em>
     *
     * @return StackIntFromList with the same contents of the current stack.
     *
     */
    @Override
    public StackIntFromList clone()
    {
        StackIntFromList newStack = new StackIntFromList();

        newStack.list = this.list.clone();
        newStack.list.begin();

        return newStack;
    }
}
