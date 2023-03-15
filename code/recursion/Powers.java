
public class Powers
{
    /**
     * Recursive version of computing X raised N.
     * 
     * @param x
     * @param n
     * @return x raised n
     */
    public static long power_v1(int x, int n)
    {
        long p;
        if (n == 0) {
            p = 1;
        } else {
            p = power_v1(x, n / 2) * power_v1(x, n / 2);
            if (n % 2 == 1) p *= x;
        }
        return p;
    }
    /**
     * Improved recursive version of computing X raised N.
     * 
     * @param x
     * @param n
     * @return x raised n
     */
    public static long power_v2(int x, int n)
    {
        long p;
        if (n == 0) {
            p = 1;
        } else {
            p = power_v2(x, n / 2);
            p = p * p;
            if (n % 2 == 1) p *= x;
        }
        return p;
    }
    
    public static void main(String [] args)
    {
        int x = 3;
        int n = 10;

        long p1 = power_v1(x, n);
        long p2 = power_v2(x, n);

        System.out.printf("\n%d raised %d is %d, that should be equal to %d\n\n",
                            x, n, p1, p2);
    }
}
