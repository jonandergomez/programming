import java.util.*;


public class Figures2
{
    public static void main(String [] args)
    {
        int offset = 10;
        int h = 22;

        triangle(offset, h, '*', 'O', 0.20);

        int w = 13;
        rectangle(offset + h - 1 - w / 2, 6, 12, '#');
    }

    public static void rectangle(int offset, int height, int width, char c)
    {
        for (int i = 0; i < height; i++) {
            line(offset, ' ');
            line(width, c);
            System.out.println();
        }
    }
    public static void triangle(int offset, int height, char c1, char c2, double p)
    {
        // the width of the base of the triangle will be 2 * height + 1
        int w = 1, o = height - 1;
        for (int i = 0; i < height; i++) {
            line(offset + o, ' ');
            line(w, c1, c2, p);
            System.out.println();
            o--;
            w += 2;
        }
    }


    public static void line(int n, char c)
    {
        while (--n >= 0 ) System.out.print(c);
    }
    public static void line(int n, char c1, char c2, double probability)
    {
        while (--n >= 0 ) System.out.print(Math.random() < probability ? c2 : c1);
    }
}
