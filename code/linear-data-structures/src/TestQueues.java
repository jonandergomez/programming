package linear;

import java.util.*;

/**
 * Class for testing queues of integers.
 *
 * @author Jon Ander Gómez
 * @version 2020-04-28
 */
public class TestQueues
{
    public static void main(String [] args)
        throws Exception
    {
        Random r = new Random();

        QueueIntArray  qa = new QueueIntArray();
        QueueIntLinked ql = new QueueIntLinked();

        for (int i = 0; i < 10; i++) {
            int value = r.nextInt(100);
            qa.push(value);
            ql.push(value);
        }

        System.out.println("\n");

        System.out.println(qa);
        System.out.println(ql);

        System.out.println("\n");

        QueueIntArray  qa2 = qa.clone();
        QueueIntLinked ql2 = ql.clone();

        System.out.println(qa2);
        System.out.println(ql2);

        System.out.println("\n");

        System.out.printf("qa and qa2 %s equal\n", qa.equals(qa2) ? "are" : "are not");
        System.out.printf("ql and ql2 %s equal\n", ql.equals(ql2) ? "are" : "are not");

        System.out.println("\n");
    }
}
