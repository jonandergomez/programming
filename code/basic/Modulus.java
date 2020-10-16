public class Modulus
{
    public static void main(String [] args)
    {
        int a = 123456;

        System.out.printf(" %8d     %8d\n", a / 10, a % 10);
        System.out.printf(" %8d     %8d\n", a / 100, a % 100);
        System.out.printf(" %8d     %8d\n", a / 1000, a % 1000);
        System.out.printf(" %8d     %8d\n", a / 10000, a % 10000);
        System.out.printf(" %8d     %8d\n", a / 100000, a % 100000);
        System.out.printf(" %8d     %8d\n", a / 1000000, a % 1000000);
    }
}
