
import java.util.*;
import java.io.*;


public class Test01
{
    private static Scanner input = new Scanner(System.in).useLocale(Locale.US);


    public static void main(String [] args)
    {
        double d1 = 2.0, d2 = 3.0 + d1;

        System.out.printf(" d1 = %f   d2 = %f\n", d1, d2);

        int x1 = 5, x2 = 9;

        final int _X_ = x1 + x2;

        x1 += 2;
        x2 -= 3;

        // _X_ = x1 + x2; // uncomment this line to see the error shown by the compiler

        System.out.printf("x1 = %d  x2 = %d  _X_ = %d\n", x1, x2, _X_);
    }
}
