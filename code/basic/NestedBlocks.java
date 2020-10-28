import java.util.*;

public class NestedBlocks
{
    public static void main(String [] args)
    { // outer block start
        int x = 10;

        { // nested block starts
            int x = 91;

            System.out.println(x);
        } // nested block ends
        System.out.println(x);
    } // outer block ends
}
