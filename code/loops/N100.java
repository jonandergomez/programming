import java.util.Scanner;

class N100
{
    public static void main(String [] args)
    {
        Scanner input = new Scanner(System.in);
        int i, j;

        while (input.hasNext()) {
            i = input.nextInt();
            j = input.nextInt();

            long l = cycle_length(Math.min(i, j), Math.max(i, j));

            System.out.printf("%d %d %d\n", i, j, l);
        }
    }
    public static long cycle_length(int a, int b)
    {
        long max_length = 0;

        for(int n=a; n <= b; n++) {
            long l = cycle(n);
            max_length = Math.max(l, max_length);
        }
        return max_length;
    }
    public static long cycle(int n)
    {
        int counter = 1;

        while (n != 1) {

            ++counter;

            if (n % 2 == 0)
                n /= 2;
            else
                n = 3 * n + 1;
        }

        return counter;
    }
}
