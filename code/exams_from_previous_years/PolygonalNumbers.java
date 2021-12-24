import java.util.*;
import java.io.*;


public class PolygonalNumbers 
{
    private static Scanner input = new Scanner(System.in).useLocale(Locale.US);


    public static void main(String [] args)
    {
        System.out.println(check(15, 3));
        System.out.println(check(19, 3));
    }

    public static boolean check(int k, int s)
    {
        int i = 1;
        int n;

        do {
            n = i * ((s - 2) * i - (s - 4)) / 2;
            i++;
        } while (n < k);

        return k == n;
    }


    public static int [] countValues(int [] edges, double [] values)
    {
        int [] counters = new int [edges.length];

        for (int i = 0; i < edges.length; i++) {

            for (int j = 0; j < values.length; j++) {

                if (values[j] < edges[i]) counters[i]++;
            }
        }

        return counters;
    }
}
