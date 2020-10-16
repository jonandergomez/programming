public class RealDataTypes2
{
    public static void main(String [] args)
    {
        float  x = 2.0F / 7.0F;
        double y = 2.0 / 7.0;

        System.out.println();
        System.out.println();
        System.out.printf(" x =   %22.17f      %22.17e\n", x, x);
        System.out.printf(" y =   %22.17f      %22.17e\n", y, y);
        System.out.println();
        System.out.println();
        System.out.printf("  Float: min = %22.15e   max = %22.16e \n",
                                Float.MIN_VALUE, Float.MAX_VALUE);
        System.out.printf(" Double: min = %22.15e   max = %22.16e \n",
                                Double.MIN_VALUE, Double.MAX_VALUE);

        System.out.println();
        System.out.println();

        x = 1.2345670e+10F;
        System.out.printf("x = %.30f\n", x);
        for (int i=0; i < 100000; i++) x++;
        System.out.printf("x = %.30f\n", x);
        x += 100000;
        System.out.printf("x = %.30f\n", x);

        System.out.println();
        System.out.println();

        y = 1.2345670e+10;
        System.out.printf("y = %.30f\n", y);
        for (int i=0; i < 100000; i++) y++;
        System.out.printf("y = %.30f\n", y);
        y += 100000;
        System.out.printf("y = %.30f\n", y);
    }
}
