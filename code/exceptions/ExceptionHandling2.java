import java.util.Scanner;

public class ExceptionHandling2
{
    static Scanner input = new Scanner(System.in);
    public static void main(String [] args)
    {
        int [] A = { 6, 7, 8, 9, 10, 11, 1, 3, 2, 4 };
        boolean valueOK;
        int index = 0;
        do {
            valueOK = true;
            try {
                System.out.print("Position in the array? ");
                index = input.nextInt();
                showValue(A, index);
            }
            catch (ArrayIndexOutOfBoundsException aioobe) {
                System.out.printf("ERROR: %d is not a valid index!\n", index);
                valueOK = false;
            }
        } while (! valueOK);
    }
    public static void showValue(int [] A, int i)
        throws ArrayIndexOutOfBoundsException
    {
        System.out.println(A[i]);
    }
}