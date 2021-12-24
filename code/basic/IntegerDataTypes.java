public class IntegerDataTypes
{
    public static void main(String [] args)
    {
        System.out.printf("%-12s %20d   %20d \n",
                          "byte",   Byte.MIN_VALUE,    Byte.MAX_VALUE);
        System.out.printf("%-12s %20d   %20d \n",
                          "short", Short.MIN_VALUE,   Short.MAX_VALUE);
        System.out.printf("%-12s %20d   %20d \n",
                          "int", Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.printf("%-12s %20d   %20d \n",
                          "long",   Long.MIN_VALUE,    Long.MAX_VALUE);
    }
}
