import java.util.*;

public class CheckingExpressionsWithFloats
{
  private static Scanner input = new Scanner(System.in).useLocale(Locale.US);
  public static void main(String [] args)
  {
      float a, b;

      System.out.print("\n enter a value for A: ");
      a = input.nextFloat();
      System.out.print("\n enter a value for B: ");
      b = input.nextFloat();

      float quotient = (int)(a / b);
      float remainder = a % b;
      //boolean z = (a / b) * b + (a % b) == a;
      boolean z = (int)(a / b) * b + (a % b) == a;

      System.out.println();
      System.out.println("quotient is " + quotient);
      System.out.println("remainder is " + remainder);
      System.out.println("quotient * b is " + (quotient * b));
      System.out.println();
      System.out.printf("(%f / %f) * %f + (%f %% %f) == %f --> %s\n\n",
                           a, b,  b,    a,  b,     a,
                           z ? "true" : "false");


      float lhs = (int)(a / b) * b + (a % b);
      float rhs = a;

      System.out.printf("lhs: %30.20f\n", lhs);
      System.out.printf("rhs: %30.20f\n", rhs);
  }
}
