import java.util.*;

public class NewSDE
{
	public static void main(String [] args)
	{
		Scanner input = new Scanner(System.in).useLocale(Locale.US);

		System.out.print("\n Type the value for A: " ); double a = input.nextDouble();
		System.out.print("\n Type the value for B: " ); double b = input.nextDouble();
		System.out.print("\n Type the value for C: " ); double c = input.nextDouble();
		System.out.println("\n");

		if (a == 0) {
			if (b == 0) {
				if (c == 0) {
					// a == 0 && b == 0 && c == 0
					System.out.println("Infinite solutions: any value of the unknown satisfies the equation.");
				} else {
					// a == 0 && b == 0 && c != 0
					System.out.println("Absurd: no valid values of coefficients.");
				}
			} else {
				// a == 0 && b != 0
				double x = -c / b;
				if (Math.abs(x) < 1.0e-7) x = Math.abs(x);
				System.out.printf(Locale.US, "It is a first degree equation whose solution is x = %.4f\n", x);
			}
		} else {
			double sqrt_argument = b * b - 4 * a * c;
			if (sqrt_argument > 0.0) {
				// two real solutions

				double sqrt = Math.sqrt(sqrt_argument);
				double x1 = (-b + sqrt) / (2 * a);
				double x2 = (-b - sqrt) / (2 * a);
				System.out.printf(Locale.US, "Values for x are: %.4f and %.4f\n", x1, x2);

			} else if (Math.abs(sqrt_argument) < 1.0e-7) {
				// a double real solution

				double x = (-b) / (2 * a);
				System.out.printf(Locale.US, "Double real solution: value for x is: %.4f\n", x);

			} else {
				// two complex solutions
				double sqrt = Math.sqrt(-sqrt_argument);
				double real = (-b) / (2 * a);
				double imag = Math.abs((sqrt) / (2 * a));

				System.out.printf("Two complex solutions:\n");
				System.out.printf(Locale.US, "\t %.4f + %.4fi\n", real, imag);
				System.out.printf(Locale.US, "\t %.4f - %.4fi\n", real, imag);
			}
		}
	}
}
