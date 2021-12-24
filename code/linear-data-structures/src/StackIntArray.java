package linear;

import java.util.*;

/**
 * Class for working with stacks of integers using
 * arrays as internal representation.
 *
 * @author Jon Ander Gómez
 * @version 2020-04-22
 */
public class StackIntArray
{
    // Attributes
    int top;
    int [] data;

    // Constructors

    /**
     * Creates an empty array with capacity for 100 elements.
     */
    public StackIntArray()
    {
        this(100);
    }
    /**
     * Creates an empty array with the given capacity.
     *
     * @param size Capacity of the internal array.
     */
    public StackIntArray(int size)
    {
        data = new int [size];
        top = -1;
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
        return top + 1;
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
    public void push(int value)
        throws Exception
    {
        if (size() == data.length)
            throw new Exception("Stack Overflow");

        data[++top] = value;
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

        return data[top--];
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

        return data[top];
    }

    /**
     * Returns if the provided object is an an StackIntArray and the contents is
     * equal to the contents of this stack.
     * <em>T(n) &isin; O(size)</em>
     *
     * @return <code>true</code> if both stacks contain the same values and in the same order,
               <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object o)
    {
        if (o instanceof StackIntArray) {
            StackIntArray other = (StackIntArray)o;

            if (this.size() != other.size()) return false;

            for (int i = top; i >= 0; i--)
                if (this.data[i] != other.data[i]) return false;

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
        sb.append("{");
        for (int i = top; i >= 0; i--)
            sb.append(" " + data[i]);
        sb.append(" }");

        return sb.toString();
    }

    /**
     * Returns a new stack which is a copy of the current stack.
     * <em>T(n) &isin; O(size)</em>
     *
     * @return StackIntArray with the same contents of the current stack.
     */
    @Override
    public StackIntArray clone()
    {
        StackIntArray newStack = new StackIntArray(data.length);

        newStack.top = this.top;
        for (int i = top; i >= 0; i--)
            newStack.data[i] = this.data[i];

        return newStack;
    }

    public boolean isContained_iterative(int x)
    {
        for (int i = top; i >= 0; i--)
           if (this.data[i] == x) return true;

        return false;
    }
    public boolean isContained_recursive(int x)
    {
        try {
            if (this.isEmpty()) {
                return false;
            } else if (this.top() == x) {
                return true;
            } else {
                int temp = this.pop();
                boolean b = this.isContained_recursive(x);
                this.push(temp);
                return b;
            }
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
            System.exit(1);
        }
        finally {
            return false;
        }
    }
    public boolean isContained_2(int x)
    {
        StackIntArray temp = new StackIntArray(this.size() + 1);

        boolean xWasIn = false;

        try {
            while (!this.isEmpty()  &&  !xWasIn) {
                int v = this.pop();
                xWasIn = (v == x);
                temp.push(v);
            }
            while (!temp.isEmpty()) {
                this.push(temp.pop());
            }
        }
        catch (Exception e) {
            e.printStackTrace(System.err);
            System.exit(1);
        }
        finally {
            return xWasIn;
        }
    }
}
