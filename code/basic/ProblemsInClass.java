import java.util.Scanner;
import java.util.Locale;

public class ProblemsInClass
{
    public static void main(String [] args)    
    {
        int h, m;

        Scanner input = new Scanner(System.in).useLocale(Locale.US);

        while (input.hasNext()) {
            System.out.print("Please, could you enter the hour of the day? ");
            h = input.nextInt();
            System.out.print("Please, could you enter the minutes within the hour of the day? ");
            m = input.nextInt();

            String s = "AM";
            System.out.printf("%02d:%02d\n", h, m);

            if (0 <= h && h <= 11 || h % 24 == 0) s = "AM"; else s = "PM";
            if (h % 24 == 0) h = 12;
            if (h > 12) h = h % 12;

            System.out.printf("%02d:%02d %s\n", h, m, s);
        }
    }
}
