import java.util.*;

public class P18
{
    public static void main(String [] args)
    {
        Scanner input = new Scanner(System.in).useLocale(Locale.US);

        System.out.print("Amount in euros: ");
        double euros = input.nextDouble();

        long e = Math.round(100 * euros);

        int n500 = (int)(e / 50000);
        e = e % 50000;
        int n200 = (int)(e / 20000);
        e = e % 20000;
        int n100 = (int)(e / 10000);
        e = e % 10000;
        int n050 = (int)(e / 5000);
        e = e % 5000;
        int n020 = (int)(e / 2000);
        e = e % 2000;
        int n010 = (int)(e / 1000);
        e = e % 1000;
        int n005 = (int)(e / 500);
        e = e % 500;
        int n002 = (int)(e / 200);
        e = e % 200;
        int n001 = (int)(e / 100);
        e = e % 100;

        int c050 = (int)(e / 50);
        e = e % 50;
        int c020 = (int)(e / 20);
        e = e % 20;
        int c010 = (int)(e / 10);
        e = e % 10;
        int c005 = (int)(e / 5);
        e = e % 5;
        int c002 = (int)(e / 2);
        e = e % 2;
        int c001 = (int)e;

        String result = " " + euros + " is equal to ";
        if (n500 > 1) result += n500 + " bills of 500€ ";
        else if (n500 == 1) result += "one bill of 500€ ";
        if (n200 > 1) result += n200 + " bills of 200€ ";
        else if (n200 == 1) result += "one bill of 200€ ";
        if (n100 > 1) result += n100 + " bills of 100€ ";
        else if (n100 == 1) result += "one bill of 100€ ";
        if (n050 > 1) result += n050 + " bills of 50€ ";
        else if (n050 == 1) result += "one bill of 50€ ";
        if (n020 > 1) result += n020 + " bills of 20€ ";
        else if (n020 == 1) result += "one bill of 20€ ";
        if (n010 > 1) result += n010 + " bills of 10€ ";
        else if (n010 == 1) result += "one bill of 10€ ";
        if (n005 > 1) result += n005 + " bills of 5€ ";
        else if (n005 == 1) result += "one bill of 5€ ";

        if (n002 > 0) result += n002 + " coins of 2€ ";
        else if (n002 == 1) result += "one coin of 2€ ";
        if (n001 > 0) result += n001 + " coin of 1€ ";

        if (c050 > 1) result += c050 + " coins of 50 cents ";
        else if (c050 == 1) result += "one coin of 50 cents ";
        if (c020 > 1) result += c020 + " coins of 20 cents ";
        else if (c020 == 1) result += "one coin of 20 cents ";
        if (c010 > 1) result += c010 + " coins of 10 cents ";
        else if (c010 == 1) result += "one coin of 10 cents ";
        if (c005 > 1) result += c005 + " coins of 5 cents ";
        else if (c005== 1) result += "one coin of 5 cents ";
        if (c002 > 1) result += c002 + " coins of 2 cents ";
        else if (c002 == 1) result += "one coin of 2 cents ";
        if (c001 > 1) result += c001 + " coin of 1 cent ";
        else if (c001 == 1) result += "one coin of 1 cent ";

        System.out.println(result);
    }
}
