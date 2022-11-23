import java.util.*;

public class Grades1b
{
	public static void main(String [] args)
	{
		Scanner input = new Scanner(System.in).useLocale(Locale.US);
		double grade;

		System.out.print("\n\nPlease, enter you grade: " );
		grade = input.nextDouble();
		System.out.println();
		
		if (grade < 0.0 || grade > 10.0) {
			System.out.println("Invalid grade: " + grade);
		} else if (grade == 10.0) {
			System.out.println("Honor Mention");
		} else if (/* 10 > grade && */ grade >= 9.0) {
			System.out.println("Excellent");
		} else if (/* 9 > grade && */ grade >= 7.0) {
			System.out.println("Notable");
		} else if (/* 7 > grade &&  */grade >= 6.0) {
			System.out.println("Good");
		} else if (/* 6 > grade &&  */grade >= 5.0) {
			System.out.println("Passed");
		} else if (/* 5 > grade &&  */grade >= 3.0) {
			System.out.println("Failed");
		} else { //if (/* 3 > grade &&  */grade >= 0.0) {
			System.out.println("Mega Failed");
		}

		System.out.println();
		System.out.println();
	}
}
