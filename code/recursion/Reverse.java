
public class Reverse
{
    public static void main(String [] args)
    {
        int n = 12345678;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-n"))
                n = Integer.parseInt(args[i + 1]);
        }

        reverse(n);
    }

    /*
        print(n) if n < 10
        print(n % 10) ; reverse(n / 10)  if n >= 0
    */
    private static void reverse(int n)
    {
        if (n < 10) {
            System.out.println(n);
        } else {
            System.out.print(n % 10); // forward path
            reverse(n / 10); // recursive call
        }
    }
}