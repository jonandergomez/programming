import java.util.Locale;
import java.util.Scanner;

public class Figures
{
    private static Scanner input = new Scanner(System.in).useLocale(Locale.US);

    public static void main(String [] args)
    {
        int height = 10;
        int offset = 0;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-h")) {
                height = Integer.parseInt(args[i + 1]);
            } else if (args[i].equals("-o")) {
                offset = Integer.parseInt(args[i + 1]);
            }
        }

        //tree(height, '*', offset);

        christmas_tree(height, '*', 'O', 0.15F, offset);
    }

    private static void christmas_tree(int height,
                                       char symbol1,
                                       char symbol2,
                                       float bulbs_proportion,
                                       int offset)
    {
        int log_width = (2 * height + 1) / 5;
        if (log_width < 1) log_width = 1;
        if (log_width % 2 == 0) log_width++;

        int log_height = height / 3;
        if (log_height < 1) log_height = 1;

        for (int i = 0; i < height; i++) {
            line(offset, ' ');
            line(height-i, ' ');
            for (int j = 0; j < 1 + 2 * i; j++) {
                System.out.printf("%c", (Math.random() < bulbs_proportion
                                        ? symbol2
                                        : symbol1));
            }
            System.out.println();
        }
        square(log_height, log_width, '#', offset + height - 1 - log_width / 2);
    }

    private static void tree(int height, char symbol, int offset)
    {
        up_triangle(height, symbol, offset);

        int log_width = (2 * height + 1) / 5;
        if (log_width < 1) log_width = 1;
        if (log_width % 2 == 0) log_width++;

        int log_height = height / 3;
        if (log_height < 1) log_height = 1;

        square(log_height, log_width, symbol, offset + height - 1 - log_width / 2);
    }

    private static void square(int height, int width, char symbol, int offset)
    {
        for (int row = 0; row < height; row++) {
            line(offset, ' ');
            line(width, symbol);
            System.out.println();
        }
    }
    private static void diamond(int height, char symbol, int offset)
    {
        if (height % 2 == 0) ++height;

          up_triangle(height / 2 + 1, symbol, offset);
        down_triangle(height / 2,     symbol, offset + 1);
    }
    private static void up_triangle(int height, char symbol, int offset)
    {
        int white_spaces = height - 1;
        int num_symbols = 1;

        for (int row = 0; row < height; row++) {
            line(offset, ' ');
            line(white_spaces, ' ');
            line(num_symbols, symbol);
            System.out.println();
            white_spaces--;
            num_symbols += 2;
        }
    }
    private static void down_triangle(int height, char symbol, int offset)
    {
        int white_spaces = 0;
        int num_symbols = 2 * height - 1;

        for (int row = 0; row < height; row++) {
            line(offset, ' ');
            line(white_spaces, ' ');
            line(num_symbols, symbol);
            System.out.println();
            white_spaces++;
            num_symbols -= 2;
        }
    }
    private static void line(int width, char symbol)
    {
        for (int i = 0; i < width; i++)
            System.out.printf("%c", symbol);
    }
}