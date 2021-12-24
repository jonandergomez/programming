package linear;

import java.util.*;

/**
 * Class for testing stacks of integers.
 *
 * @author Jon Ander Gómez
 * @version 2020-04-28
 */
public class TestStacks
{
    public static void main(String [] args)
        throws Exception
    {
        Random r = new Random();

        StackIntArray  sa = new StackIntArray();
        StackIntLinked sl = new StackIntLinked();

        for (int i = 0; i < 10; i++) {
            int value = r.nextInt(100);
            sa.push(value);
            sl.push(value);
        }

        System.out.println("\n");

        System.out.println(sa);
        System.out.println(sl);

        System.out.println("\n");

        StackIntArray  sa2 = sa.clone();
        StackIntLinked sl2 = sl.clone();

        System.out.println(sa2);
        System.out.println(sl2);

        System.out.println("\n");

        System.out.printf("sa and sa2 %s equal\n", sa.equals(sa2) ? "are" : "are not");
        System.out.printf("sl and sl2 %s equal\n", sl.equals(sl2) ? "are" : "are not");

        System.out.println("\n");
    }
}
