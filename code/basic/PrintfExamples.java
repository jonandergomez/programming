import java.util.*;

public class PrintfExamples
{
    public static void main(String [] args)
    {
        boolean b = true;
        char    c = '€';
        int     i = 7845;
        float   f = 235.123F;
        double  d = 435.4235255;
        String  s = "abcdefghijklmnopqrstuvwxyz";

        System.out.printf("b = %b\n", b);
        System.out.printf("c = %c\n", c);

        System.out.printf("i = %07d\n", i);
        System.out.printf("i = %7d\n", i);
        System.out.printf("i = %6d\n", i);
        System.out.printf("i = %5d\n", i);
        System.out.printf("i = %4d\n", i);
        System.out.printf("i = %3d\n", i);
        System.out.printf("i = %2d\n", i);
        System.out.printf("i = %d\n", i);
        System.out.printf("i = %o\n", i);
        System.out.printf("i = %x\n", i);

        System.out.printf("f = %20.5f\n", f);
        System.out.printf("f = %20.4f\n", f);
        System.out.printf("f = %20.3f\n", f);
        System.out.printf("f = %20.2f\n", f);
        System.out.printf("f = %20.1f\n", f);
        System.out.printf("f = %20.0f\n", f);
        System.out.printf("f = %.9e\n", f);

        System.out.printf("d = %20.5f\n", d);
        System.out.printf("d = %20.4f\n", d);
        System.out.printf("d = %20.3f\n", d);
        System.out.printf("d = %20.2f\n", d);
        System.out.printf("d = %20.1f\n", d);
        System.out.printf("d = %20.0f\n", d);
        System.out.printf("d = %.9e\n", d);

        System.out.printf("s = %s\n", s);
        System.out.printf("s = %.10s\n", s);
        System.out.printf("s = %30.10s\n", s);
        System.out.printf("s = %-30.10s\n", s);
    }
}
