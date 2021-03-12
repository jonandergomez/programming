
import java.util.*;


public class PascalTriangle2
{
    private long [][] pascalTriangle;

    public PascalTriangle2(int max_row)
    {
        pascalTriangle = new long [max_row + 1][max_row + 1];

        pascalTriangle[1][1] = 1;
        for (int row = 2; row <= max_row; row++) {
            pascalTriangle[row][1] = 1;
            pascalTriangle[row][row] = 1;
            for (int col = 2; col < row; col++)
                pascalTriangle[row][col] = pascalTriangle[row - 1][col - 1]
                                         + pascalTriangle[row - 1][col];
        }
    }

    public long get(int row, int col)
    {
        return pascalTriangle[row][col];
    }

    public static void main(String [] args)
    {
        int n = 6;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-n"))
                n = Integer.parseInt(args[i+1]);
        }

        PascalTriangle2 pt = new PascalTriangle2(n);

        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= row; col++)
                System.out.printf("  %5d", pt.get(row, col));
            System.out.println();
        }
    }
}
