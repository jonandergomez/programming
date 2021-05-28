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

    /**
     * Creates an stack as a copy of the stack given as parameter.
     */
    public StackIntLinked(StackIntLinked s)
    {
        /* this initialization is not a copy, this must never be used
        this.size = s.size;
        this.top = s.top;
        */

        this();

        NodeInt temp = null;

        while (s.top != null) {
            temp = new NodeInt(s.top.getValue(), temp);
            s.top = s.top.getNext();
            s.size--;
        }

        while (temp != null) {
            s.top = new NodeInt(temp.getValue(), s.top);
            s.size++;
            this.top = new NodeInt(temp.getValue(), this.top);
            this.size++;
            temp = temp.getNext();
        }
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
    public void push(int value)
        throws Exception
    {
        top = new NodeInt(value, top);
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
        if (size() == 0)
            throw new Exception("Stack Underflow");

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
        if (size() == 0)
            throw new Exception("Stack Underflow");

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
    public boolean equals(Object o)
    {
        if (o instanceof StackIntLinked) {
            StackIntLinked other = (StackIntLinked)o;

            NodeInt a = this.top;
            NodeInt b = other.top;

            while (a != null  ||  b != null) {
                if (a == null || b == null) return false;
                if (a.getValue() != b.getValue()) return false;
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
        sb.append("{");
        // complete here
        NodeInt a = top;
        while (a != null) {
            sb.append(" " + a.getValue());
            a = a.getNext();
        }
        sb.append(" }");

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

        clone(newStack);

        return newStack;
    }
    /**
     * Private method for cloning a stack tacking advantage of
     * the call stack of the system.
     */
    private void clone(StackIntLinked newStack)
    {
        if (! this.isEmpty()) {
            try {
                // forward path of the recursive method
                int value = this.pop();

                // recursive call
                clone(newStack);

                // backward path of the recursive method
                newStack.push(value);
                this.push(value);
            }
            catch(Exception e) {
                e.printStackTrace(System.err);
                throw new Error("Unexpected exception!");
            }
        }
    }

    // from exam of academic year 2019/2020
    public static StackIntLinked removeGreaterThan(StackIntLinked s, int x)
    {
        StackIntLinked temp = new StackIntLinked();
        StackIntLinked output = new StackIntLinked();

        // all the elements in 's' are moved to 'temp'
        while (! s.isEmpty()) temp.push(s.pop());

        // elements in 'temp' are extracted and distributed
        while (! temp.isEmpty()) {
            if (temp.top() <= x) // lower than or equal to 'x' in 's'
                s.push(temp.pop());
            else // greater than 'x' in 'output'
                output.push(temp.pop());
        }
        // 'output' is returned with the removed elements
        return output;
    }

    // from an exam of academic year 2019/2020
    public int removeLessThanInOrd(int x)
    {
        int counter = 0;

        while (top != null && top.getValue() < x) {
            top = top.getNext();
            --size;
            ++counter;
        }

        return counter;
    }
    public int removeLessThan(int x)
    {
        int counter = 0;

        NodeInt temp = null;

        while (top != null) {
            temp = new NodeInt(top.getValue(), temp);
            top = top.getNext();
            --size;
        }

        while (temp != null) {
            if (temp.getValue() >= x) {
                top = new NodeInt(temp.getValue(), top);
                ++size;
            } else {
                ++counter;
            }
            temp = temp.getNext();
        }

        return counter;
    }
    public int removeLessThan_v2(int x)
    {
        int [] temp = new int [this.size];

        for (int i = 0; i < this.size /* this.top != null */; i++) {
            temp[i] = this.top.getValue();
            this.top = this.top.getNext();
        }

        // in this case the elements are restored into the
        // stack in the reverse order they were previously
        this.size = 0;
        for (int i = 0; i < temp.length; i++) {
        // for (int i = temp.length -1; i >= 0; i--) // to leave the elements in the same order
            if (temp[i] >= x) {
                this.top = new NodeInt(temp[i], this.top);
                ++this.size;
            }
        }

        return temp.length - this.size;
    }
}
