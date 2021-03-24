import java.util.*;


public class KeyPalindrome
{
    public static void main(String [] args)
    {
        int [] a = {20, 15, 14, 32, 10, 7, 22};

        int k = a.length + 3;
        if (keyPalindrome(a, k)) {
            System.out.printf("it is keyPalindrome(%d)\n", k);
        } else {
            System.out.printf("it is NOT keyPalindrome(%d)\n", k);
        }
        k = a.length + 1;
        if (keyPalindrome(a, k)) {
            System.out.printf("it is keyPalindrome(%d)\n", k);
        } else {
            System.out.printf("it is NOT keyPalindrome(%d)\n", k);
        }
        k = 22;
        if (keyPalindrome(a, k)) {
            System.out.printf("it is keyPalindrome(%d)\n", k);
        } else {
            System.out.printf("it is NOT keyPalindrome(%d)\n", k);
        }
    }

    /*
        k >= a.length
        0 <= i <= a.length/2

        trivial cases:
            return true   if   i > a.length / 2
            return false  if   k < a.length

        general cases:
            i <= a.length / 2:
                return false                     if abs(a[i] - a[a.length-i-1]) >= k-i
                return keyPalindrome(a, k, i+1)  otherwise
     */
    public static boolean keyPalindrome(int [] a, int k)
    {
        return keyPalindrome(a, k, 0);
    }
    public static boolean keyPalindrome(int [] a, int k, int i)
    {
        if (k < a.length)
            return false;
        else if (i > a.length / 2)
            return true;
        else if (Math.abs(a[i] - a[a.length - i - 1]) >= k - i)
            return false;
        else
            return keyPalindrome(a, k, i + 1);
    }
}
