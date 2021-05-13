package linear;

import java.util.*;

/**
 * Class for testing lists of integers.
 *
 * @author Jon Ander Gómez
 * @version 2020-05-06
 */
public class TestLists2
{
    public static void main(String [] args)
        throws Exception
    {
        Random r = new Random();

        ListIntLinked  la1 = new ListIntLinked();
        ListIntLinked  la2 = new ListIntLinked();

        for (int i = 0; i < 10; i++) {
            int value = r.nextInt(100);
            la1.append(value);
            la2.insertInOrder(value);
        }

        System.out.println("\n");
        System.out.println(la1);
        System.out.println("\n");
        System.out.println(la2);
        System.out.println("\n");

        ListIntLinked  la3 = la1.clone();

        System.out.println("\n");

        System.out.printf("la1 and la3 %s equal\n", la1.equals(la3) ? "are" : "are not");

        System.out.println("\n");

        test1(la1.clone());
        test1(la2.clone());

        test2(la1.clone());
        test2(la2.clone());

        test3(la2.clone());


        la1.sort();
        System.out.printf("la1 and la2 %s equal after sorting la1\n", la1.equals(la2) ? "are" : "are not");
        System.out.println(la1);
        System.out.println(la2);
        la1.end();
        la2.end();
        System.out.printf("la1 and la2 %s equal after sorting la1 and setting cursor of both lists to the same position\n", la1.equals(la2) ? "are" : "are not");
    }

    private static void test1(ListIntLinked l)
        throws Exception
    {
        System.out.println(l + "\n");

        l.begin();
        while (! l.isEmpty()) {
            l.delete();
            System.out.println(l + "\n");
            if (! l.next()) l.begin();
        }
    }

    private static void test2(ListIntLinked l)
        throws Exception
    {
        System.out.println(l + "\n");

        for (int i = 0; i <= 50; ) {
            if (l.find(i)) {
                l.delete();
                System.out.println(l + "   removed " + i + "\n");
            } else {
                i++;
            }
        }
    }
    private static void test3(ListIntLinked l)
        throws Exception
    {
        System.out.println(l + "\n");

        for (boolean valid = l.begin(); valid; valid = l.next()) {

            l.set(l.get() + 1);

            System.out.println(l + "\n");
        }
    }
}
