package linear;

import java.util.*;

/**
 * Class for testing lists of integers.
 *
 * @author Jon Ander Gómez
 * @version 2020-05-06
 */
public class TestLists
{
    public static void main( String [] args )
        throws Exception
    {
        Random r = new Random();

        ListIntArray  la1 = new ListIntArray();
        ListIntArray  la2 = new ListIntArray();

        for( int i=0; i < 10; i++ ) {
            int value = r.nextInt( 100 );
            la1.append(value);
            la2.insertInOrder(value);
        }

        System.out.println("\n");
        System.out.println( la1 );
        System.out.println("\n");
        System.out.println( la2 );
        System.out.println("\n");

        ListIntArray  la3 = la1.clone();

        System.out.println("\n");

        System.out.printf( "la1 and la3 %s equal\n", la1.equals(la3) ? "are" : "are not" );

        System.out.println("\n");

        test1( la1.clone() );
        test1( la2.clone() );

        test2( la1.clone() );
        test2( la2.clone() );

        test3( la2.clone() );
    }

    private static void test1( ListIntArray l )
        throws Exception
    {
        System.out.println( l + "\n" );

        l.begin(); 
        while( ! l.isEmpty() ) {
            l.delete();        
            System.out.println( l + "\n" );
            if ( ! l.next() ) l.begin();
        }
    }

    private static void test2( ListIntArray l )
        throws Exception
    {
        System.out.println( l + "\n" );

        for( int i=0; i <= 50; ) {
            if ( l.find(i) ) {
                l.delete();
                System.out.println( l + "   removed " + i + "\n" );
            } else {
                i++;
            }
        }
    }
    private static void test3( ListIntArray l )
        throws Exception
    {
        System.out.println( l + "\n" );

        for( boolean valid=l.begin(); valid; valid=l.next() ) {

            l.set( l.get()+1 );

            System.out.println( l + "\n" );
        }
    }
}
