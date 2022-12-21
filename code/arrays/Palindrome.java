
public class Palindrome
{
    public static boolean palindrome(String s)
    {
        char [] v = s.toCharArray();
        int n = v.length;

        for (int i = 0; i < n; i++) {
            if (v[i] == ' ') {
                n--;
                for (int j = i; j < n; j++) v[j] = v[j + 1];
            }
        }

        int left = 0, right = n;

        while (left < right && v[left] == v[right]) {
            left++;
            right--;
        }

        return left >= right;
    }

    public static void main(String [] args)
    {
        String s1 = "dabale arroz a la zorra el abad";
        String s2 = "dabale   arroz a   la zorra el   abad";

        System.out.println();
        System.out.printf("|%s| %s palindrome\n", s1, palindrome(s1) ? "is" : "is not");
        System.out.println();
        System.out.printf("|%s| %s palindrome\n", s2, palindrome(s2) ? "is" : "is not");
        System.out.println();
    }
    
}
