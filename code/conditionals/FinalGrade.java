
import java.util.*;
import java.io.*;


public class FinalGrade
{
    private static Scanner input = new Scanner(System.in).useLocale(Locale.US);

    public static void main(String [] args)
    {
        System.out.print("\n MG1: "); double mg1 = input.nextDouble();
        System.out.print("\n MG2: "); double mg2 = input.nextDouble();
        System.out.print("\n LG1: "); double lg1 = input.nextDouble();
        System.out.print("\n LG2: "); double lg2 = input.nextDouble();
        System.out.print("\n attendance [0,1]: "); double attendance = input.nextDouble();

        double fg = finalGrade(mg1, mg2, lg1, lg2, attendance);

        System.out.printf("\n The final grade is %.1f\n", fg);
    }

    static double updateGrade1(double g1, double g2)
    {
        if (g2 >= 6) {
            g1 = (g2 > g1) ? g2 : g1; // Math.max(g1, g2);
        } else if (g2 >= 4) {
            double temp = (g1 + g2) / 2.0;
            g1 = (temp > g1) ? temp : g1;
        }
        return g1;
    }
    static double finalGrade(double mg1, double mg2, double lg1, double lg2, double attendance)
    {
        /*
        mg1 = (4 <= mg2 && mg2 < 6) ? (mg1 + mg2)/2 : ((mg2 >= 6) ? mg2 : mg1);

        if (lg2 >= 6) {
            lg1 = lg2;
        } else if (lg2 >= 4) {
            lg1 = (lg1 + lg2) / 2;
        }
        */
        mg1 = updateGrade1(mg1, mg2);
        lg1 = updateGrade1(lg1, lg2);

        double mwg = 0.5 * mg1 + 0.5 * mg2;
        double lwg = 0.5 * lg1 + 0.5 * lg2;
        lwg = (attendance >= 0.8) ? lwg : 0;

        return 0.75 * mwg + 0.25 * lwg;
    }
}
