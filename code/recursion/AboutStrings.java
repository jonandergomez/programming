
public class AboutStrings
{
    public static void main(String [] args)
    {
        for (int i = 1; i < args.length; i++) {
            String text = args[i - 1];
            String pattern = args[i];

            if (isPrefix(text, pattern)) {
                System.out.printf("%s is prefix of %s\n", pattern, text);
            }
            if (isSuffix(text, pattern)) {
                System.out.printf("%s is suffix of %s\n", pattern, text);
            }
            if (isContained(text, pattern)) {
                System.out.printf("%s is contained in %s\n", pattern, text);
            }
            if (isPalindrome(pattern)) {
                System.out.printf("%s is palindrome\n", pattern);
            }
        }
    }

    private static boolean isPrefix(String text, String pattern)
    {
        //return isPrefix_r(text, pattern);
        return isPrefix_r(text, pattern, pattern.length() - 1);
    }
    private static boolean isSuffix(String text, String pattern)
    {
        //return isSuffix_r(text, pattern);
        return isSuffix_r(text, pattern, text.length() - 1, pattern.length() - 1);
    }
    /*
        isContained(t, p):
            true if  |p| == 0
            false if |p| > |t|
            true if p is prefix of t
            isContained(t.substring(1), p)

        As homework do the version with isSuffix
    */
    private static boolean isContained_r(String text, String pattern)
    {
        if (pattern.length() == 0)
            return true;
        else if (pattern.length() > text.length())
            return false;
        else if (isPrefix(text, pattern))
            return true;
        else
            return isContained_r(text.substring(1), pattern);
    }
    private static boolean isContained_r(String text, String pattern, int t)
    {
        if (pattern.length() == 0)
            return true;
        else if (pattern.length() > text.length())
            return false;
        else if ()
            return false;
        else if (isSuffix_r(text, pattern, t, pattern.length() - 1))
            return true;
        else
            return isContained_r(text, pattern, t - 1);
    }
    public static boolean isContained(String text, String pattern)
    {
        return isContained_r(text, pattern, text.length() - 1);
    }
    /*
        isPalindrome(t):
            true if |t| == 0
            true if |t| == 1
            false if t[0] != t[|t|-1]
            isPalindrome(t[1:|t|-1]) -- removing first and last character
    */
    private static boolean isPalindrome(String text)
    {
        return isPalindrome(text, 0, text.length() - 1);
        /*
        if (text.length() <= 1)
            return true;
        else if (text.charAt(0) != text.charAt(text.length() - 1))
            return false;
        else
            return isPalindrome(text.substring(1, text.length() - 1));
        */
    }
    private static boolean isPalindrome(String text, int left, int right)
    {
        // this method assumes the initial call is isPalindrome(text, 0, |text|-1)

        //System.out.printf("text: %s  left = %d  right = %d\n", text, left, right);

        if (right - left <= 0)
            return true;
        else if (text.charAt(left) != text.charAt(right))
            return false;
        else
            return isPalindrome(text, left + 1, right - 1);

        // do as homework the version that ignores non-alphabetic symbols
    }

    /*
        isPrefix(t, p):
            true if |p| == 0
            false if |p| > |t|
            false if t[0] != p[0]
            isPrefix(t.substring(1), p.substring(1))
    */
    private static boolean isPrefix_r(String text, String pattern)
    {
        /*
        System.out.printf("text...: %s\n", text);
        System.out.printf("pattern: %s\n", pattern);
        System.out.println("---------------------------------");
        */

        if (pattern.length() == 0)
            return true;
        else if (text.length() < pattern.length())
            return false;
        else if (text.charAt(0) != pattern.charAt(0))
            return false;
        else
            return isPrefix_r(text.substring(1), pattern.substring(1));
    }
    private static boolean isPrefix_r(String text, String pattern, int i)
    {
        if (i < 0)
            return true;
        else if (text.length() < pattern.length())
            return false;
        else if (text.charAt(i) != pattern.charAt(i))
            return false;
        else
            return isPrefix_r(text, pattern, i - 1);
    }

    /*
        isSuffix(t, p):
            true if |p| == 0
            false if |p| > |t|
            false if t[|t|-1] != p[|t|-1]
            isPrefix(t.substring(0, |t|-1), p.substring(0, |p|-1))
    */
    private static boolean isSuffix_r(String text, String pattern)
    {
        if (pattern.length() == 0)
            return true;
        else if (pattern.length() > text.length())
            return false;
        else if (pattern.charAt(pattern.length() - 1) != text.charAt(text.length() - 1))
            return false;
        else
            return isSuffix_r(   text.substring(0,    text.length() - 1),
                              pattern.substring(0, pattern.length() - 1));
    }
    private static boolean isSuffix_r(String text, String pattern, int t, int p)
    {
        if (p < 0)
            return true;
        else if (pattern.length() > text.length())
            return false;
        else if (pattern.charAt(p) != text.charAt(t))
            return false;
        else
            return isSuffix_r(text, pattern, t - 1, p - 1);
    }
}