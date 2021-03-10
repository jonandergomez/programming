import java.util.*;

public class NaturalNumbers
{
    public static void main(String [] args)
    {
        int n = 10;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-n"))
                n = Integer.parseInt(args[i+1]);
        }

        naturalNumbers(n);
        System.out.println();
        reverseNaturalNumbers(n);
        System.out.println();
        symmetricSequence(n);
        System.out.println();
    }

    /*
        if n == 1: print(1)
        if n >  1: naturalNumbers(n - 1); print(n)
    */
    private static void naturalNumbers(int n)
    {
        if (n == 1) { // trivial case
            System.out.print(1);
        } else {
            // general case
            naturalNumbers(n - 1); // recursive call
            System.out.print(" " + n); // backward path
        }
    }

    /*
        if n == 1: print(1)
        if n >  1: print(n); reverseNaturalNumbers(n - 1)
    */
    private static void reverseNaturalNumbers(int n)
    {
        if (n == 1) { // trivial case
            System.out.print(1);
        } else {
            // general case
            System.out.print(n + " "); // forward path
            reverseNaturalNumbers(n - 1); // recursive call
        }
    }

    /*
        if n == 0: print(0)
        if n >  0: print(n); symmetricSequence(n - 1); print(n)
    */
    private static void symmetricSequence(int n)
    {
        if (n == 0) { // trivial case
            System.out.print(0);
        } else {
            // general case
            System.out.print(n + " "); // forward path
            symmetricSequence(n - 1); // recursive call
            System.out.print(" " + n); // backward path
        }
    }
}
