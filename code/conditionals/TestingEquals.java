import java.util.*;


public class TestingEquals
{
    public static void main(String [] args)
    {
        String s1 = "Hello";
        String s2 = new String("Hello");
        String s3 = "Hello"; //s1;

        test_equality(s1, s2);
        test_equality(s1, s3);
        test_equality(s2, s3);
        test_equality_2(s1, s2);
        test_equality_2(s1, s3);
        test_equality_2(s2, s3);
    }
    public static void test_equality(String a, String b)
    {
        if (a == b)
            System.out.println(a + " is equal to " + b);
        else
            System.out.println(a + " is NOT equal to " + b);
    }
    public static void test_equality_2(String a, String b)
    {
        if (a.equals(b))
            System.out.println(a + " is equal to " + b);
        else
            System.out.println(a + " is NOT equal to " + b);
    }
}
