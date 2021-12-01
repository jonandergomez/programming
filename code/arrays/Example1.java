import java.util.*;

public class Example1
{
    public static void main(String [] args)
    {
        int [] a = new int [2];

        a[0] = 7;
        a[1] = 3;

        swap(a);

        print(a);
    }

    public static void swap(int [] a)
    {
        int temp = a[0];
        a[0] = a[1];
        a[1] = temp;
    }

    public static void print(int [] a)
    {
        for (int i = 0; i < a.length; i++)
            System.out.print(" " + a[i]);
        System.out.println();
    }
}
