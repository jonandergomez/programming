
import java.util.*;


public class PascalTriangle
{
    public static void main(String [] args)
    {
        int n = 6;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-n"))
                n = Integer.parseInt(args[i+1]);
        }

        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= row; col++)
                System.out.printf("  %5d", pascalTriangle(row, col));
            System.out.println();
        }
    }

    /*
        pt(r, c) = 1 if c == 1 or c == r
        pt(r, c) = pt(r-1, c-1) + pt(r-1, c) otherwise
    */
    private static int pascalTriangle(int row, int col)
    {
        if (col == 1  ||  col == row) {
            return 1;
        } else {
            return pascalTriangle(row - 1, col - 1) + pascalTriangle(row - 1, col);
        }
    }
}
