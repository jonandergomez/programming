import java.util.Random;

public class BasicOperations
{
    /** Array of integers to be used in several basic operations. */
    private int [] v;
    /** Number of valid elements stored in the array v. */
    private int    n;

    public BasicOperations(int size)
    {
        v = new int [size];
        n = 0;
    }

    public int capacity() { return v.length; }
    public int size() { return n; }
    public void reset() { n = 0; }

    @Override 
    public String toString()
    {
        StringBuffer sb = new StringBuffer();

        sb.append(String.format("array size %3d: ", n));
        sb.append("{");
        if (n > 0) sb.append(v[0]);
        for (int i = 1; i < n; i++) sb.append(", " + v[i]);
        sb.append("}");
        return sb.toString();
    }

    public static String toString(int [] a)
    {
        StringBuffer sb = new StringBuffer();

        sb.append("{");
        if (a.length > 0) sb.append(a[0]);
        for (int i = 1; i < a.length; i++) sb.append(", " + a[i]);
        sb.append("}");
        return sb.toString();
    }

    public static int [] genRandomArray(int size)
    {
        int [] v = new int [size];
        Random r = new Random();

        for (int i = 0; i < v.length; i++)
            v[i] = r.nextInt(100);

        return v;
    }

    /** 
     * Returns the index of the first position
     * of x in the array, -1 if it does not
     * exist in the array.
     * 
     * @param x The value to be found in the array.
     */
    public int find(int x)
    {
        int i = 0;

        while (i < n && v[i] != x) i++;

        return (i < n) ? i : -1;
    }

    /** 
     * Removes the element at the given position.
     * 
     * @param pos Index of the position whose element must be removed.
     */
    public void removeAtPos(int pos)
    {
        if (0 <= pos && pos < n) {
            for (int i = pos; i < n; i++) v[i] = v[i + 1];
            --n;
        }
    }

    /** 
     * Removes the given value if contained in the array.
     * 
     * @param x The value to be removed from the array if it exists.
     */
    public void remove(int x)
    {
        int pos = find(x);
        if (pos >= 0) removeAtPos(pos);
    }

    /**
     * Adds a new element in the array after the stored elements,
     * i.e., in the first free position.
     * 
     * @param x New value to be stored in the array.
     */
    public void add(int x)
    {
        if (n == v.length) increaseCapacity(10);

        v[n++] = x;
    }

    /**
     * Inserts a new element in the array at the specified position.
     * 
     * @param x New value to be stored in the array.
     * @param pos Position at the new element must be inserted.
     */
    public void insert(int x, int pos)
    {
        if (n == v.length) increaseCapacity(10);

        // Move all the existing elements from pos to n one position to the right.
        for (int i = n; i > pos; i--) v[i] = v[i - 1];

        // Now, position pos is free and x can be stored in such position.
        v[pos] = x;
    }

    /**
     * Inserts a new element in the array at the corresponding position in order
     * to guarantee the array remains sorted in ascending order.
     * Precondition, the array is already sorted in ascending order.
     * 
     * @param x New value to be stored in the array.
     */
    public void insertInOrder(int x)
    {
        if (n == v.length) increaseCapacity(10);

        int i = n - 1;
        // Shifting one position to the right all the values in v greater than x.
        while (i >= 0 && v[i] > x) {
            v[i + 1] = v[i];
            i--;
        }
        v[i + 1] = x;
        n++;
    }

    /**
     * Increases the size of the array, but not the number
     * of elements stored in it.
     * 
     * @param s Number of new positions to be available in the array.
     */
    public void increaseCapacity(int s)
    {
        // Creation of a new array larger than the existing one.
        int [] newArray = new int [v.length + s];

        // Copying the contents of the existing array to the new one.
        for (int i = 0; i < n; i++) newArray[i] = v[i];

        // The new array substitutes the existing one.
        v = newArray;
    }

    /**
     * 
     */
    public static void main(String [] args)
    {
        BasicOperations bo = new BasicOperations(100);
        System.out.println("Capacity = " + bo.capacity() + ", size = " + bo.size());

        int [] temp = BasicOperations.genRandomArray(10);

        BasicOperations.toString(temp);

        for (int i = 0; i < temp.length; i++) {
            bo.add(temp[i]);
            System.out.println(bo);
        }

        bo.reset();
        System.out.println("\nAfter the reset: ");
        System.out.println(bo);
        System.out.println();

        for (int i = 0; i < temp.length; i++) {
            bo.insertInOrder(temp[i]);
            System.out.println(bo);
        }
        System.out.println("\nLet's remove the elements: ");
        for (int i = 0; i < temp.length; i++) {
            System.out.println("    removing " + temp[i]);
            System.out.println(bo);
            bo.remove(temp[i]);
            System.out.println(bo);
        }
    }
}
