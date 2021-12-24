import java.util.*;
import java.io.*;


public class Triangles
{
    private static Scanner input = new Scanner(System.in).useLocale(Locale.US);


    public static void main(String [] args)
    {
        int height = 10;
        int offset = 0;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-h")) {
                height = Integer.parseInt(args[i+1]);
            } else if (args[i].equals("-o")) {
                offset = Integer.parseInt(args[i+1]);
            }
        }

        triangle_a(height, offset);
        triangle_b(height, offset);
        triangle_up(height, offset);
        triangle_down(height - 1, offset + 1);
    }

    public static void line(int n, char symbol)
    {
        while (--n >= 0) System.out.print(symbol);
    }
    public static void triangle_a(int h, int o)
    {
        for (int row = 1; row <= h; row++) {
            line(o + h - row, ' ');
            line(row, '*');
            System.out.println();
        }
    }
    public static void triangle_b(int h, int o)
    {
        for (int row = 1; row <= h; row++) {
            line(o, ' ');
            line(row, '*');
            System.out.println();
        }
    }
    public static void triangle_up(int h, int o)
    {
        for (int row = 1; row <= h; row++) {
            line(o + h - row, ' ');
            line(2 * row - 1, '*');
            System.out.println();
        }
    }
    public static void triangle_down(int h, int o)
    {
        for (int row = h; row >= 1; row--) {
            line(o + h - row, ' ');
            line(2 * row - 1, '*');
            System.out.println();
        }
    }
}
