import java.util.*;

public class CheckingExpressions
{
  private static Scanner input = new Scanner(System.in).useLocale(Locale.US);
  public static void main(String [] args)
  {
      int a, b;

      System.out.print("\n enter a value for A: " );
      a = input.nextInt();
      System.out.print("\n enter a value for B: " );
      b = input.nextInt();

      int quotient = a / b;
      int remainder = a % b;
      boolean z = (a/b)*b + (a%b) == a;

      System.out.println();
      System.out.println("quotient is " + quotient);
      System.out.println("remainder is " + remainder);
      System.out.println("quotient * b is " + (quotient * b));
      System.out.println();
      System.out.printf("(%d/%d)*%d + (%d%%%d) == %d --> %s\n\n",
                           a, b,  b,    a,  b,     a,
                           z ? "true" : "false");
  }
}
