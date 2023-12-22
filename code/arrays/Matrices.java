
import java.util.*;
import java.io.*;


public class Matrices
{
    private static Scanner input = new Scanner(System.in).useLocale(Locale.US);


    public static void main(String [] args)
    {
        int [][] a = createMatrix(3, 5);
        int [][] b = createMatrix(3, 5);
        int [][] c = add(a, b);

        System.out.println("----------------------------------------------");
        showMatrix(a);
        System.out.println("----------------------------------------------");
        showMatrix(b);
        System.out.println("----------------------------------------------");
        showMatrix(c);
        System.out.println("----------------------------------------------");

        int [] v1 = createArray(5);
        int [] v2 = createArray(11);
        c = tensorProduct(v1, v2);
        System.out.println("----------------------------------------------");
        showArray(v1);
        System.out.println("----------------------------------------------");
        showArray(v2);
        System.out.println("----------------------------------------------");
        showMatrix(c);
        System.out.println("----------------------------------------------");

        int [] r = vectorMatrixProduct(v2, b);
    }

    private static int [] add(int [] a, int [] b)
    {
        if (a.length != b.length)
            throw new RuntimeException("Incompatible arrays!!!");

        int [] c = new int [a.length];

        for (int i = 0; i < c.length; i++) {
            c[i] = a[i] + b[i];
        }

        return c;
    }
    private static int [] elementWiseProduct(int [] a, int [] b)
    {
        if (a.length != b.length)
            throw new RuntimeException("Incompatible arrays!!!");

        int [] c = new int [a.length];

        for (int i = 0; i < c.length; i++) {
            c[i] = a[i] * b[i];
        }

        return c;
    }
    private static int [][] add(int [][] a, int [][] b)
    {
        if (a.length != b.length || a[0].length != b[0].length)
            throw new RuntimeException("Incompatible matrices!!!");

        int [][] r = new int [a.length][a[0].length];

        for (int i = 0; i < r.length; i++) {
            for (int j = 0; j < r[i].length; j++) {
                r[i][j] = a[i][j] + b[i][j];
            }
        }

        return r;
    }
    private static int [][] elementWiseProduct(int [][] a, int [][] b)
    {
        if (a.length != b.length || a[0].length != b[0].length)
            throw new RuntimeException("Incompatible matrices!!!");

        int [][] r = new int [a.length][a[0].length];

        for (int i = 0; i < r.length; i++) {
            for (int j = 0; j < r[i].length; j++) {
                r[i][j] = a[i][j] * b[i][j];
            }
        }

        return r;
    }

    private static int [][] createMatrix(int rows, int columns)
    {
        Random r = new Random();

        int [][] m = new int [rows][columns];

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                m[i][j] = r.nextInt(100);
            }
        }

        return m;
    }
    private static int [] createArray(int n)
    {
        Random r = new Random();

        int [] m = new int [n];

        for (int i = 0; i < m.length; i++) {
            m[i] = r.nextInt(100);
        }

        return m;
    }

    private static void showMatrix(int [][] m)
    {
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.printf(" %5d", m[i][j]);
            }
            System.out.println();
        }
    }
    private static void showArray(int [] m)
    {
        for (int i = 0; i < m.length; i++)
            System.out.printf(" %3d", m[i]);
        System.out.println();
    }

    private static int scalarProduct(int [] a, int [] b)
    {
        int result = 0;

        for (int i = 0; i < a.length; i++)
            result += a[i] * b[i];

        return result;
    }
    private static int [][] tensorProduct(int [] a, int [] b)
    {
        int [][] r = new int [a.length][b.length];

        for (int i = 0; i < r.length; i++) {
            for (int j = 0; j < r[i].length; j++) {
                r[i][j] = a[i] * b[j];
            }
        }

        return r;
    }

    private static int [] vectorMatrixProduct(int [] v, int [][] m)
    {
        if (v.length != m.length)
            throw new RuntimeException(
                String.format("Number of elements of the vector mismatch the"
                            + " number of rows of the matrix: %d != %d",
                            v.length, m.length));

        int [] r = new int [m[0].length];

        for (int i = 0; i < r.length; i++) {
            r[i] = 0;
            for (int j = 0; j < v.length; j++) {
                r[i] += v[j] * m[j][i];
            }
        }

        return r;
    }

    private static int [] matrixVectorProduct(int [][] m, int [] v)
    {
        if (m[0].length != v.length)
            throw new RuntimeException(
                String.format("Number of columns of the matrix mismatch the"
                            + " number of elements of the vector: %d != %d",
                            m[0].length, v.length));

        int [] r = new int [m.length];

        for (int i = 0; i < r.length; i++) {
            r[i] = 0;
            for (int j = 0; j < v.length; j++) {
                r[i] += m[i][j] * v[j];
            }
        }

        return r;
    }

    private static int [][] matrixMatrixProduct(int [][] a, int [][] b)
    {
        if (a[0].length != b.length)
            throw new RuntimeException(
                String.format("Number of columns of the first matrix mismatch"
                            + " the number of rows of the second one: %d != %d",
                            a[0].length, b.length));

        int [][] r = new int [a.length][b[0].length];

        for (int i = 0; i < r.length; i++) {
            for (int j = 0; j < r[0].length; j++) {

                r[i][j] = 0;

                for (int k = 0; k < b.length; k++) {
                    r[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return r;
    }
}
