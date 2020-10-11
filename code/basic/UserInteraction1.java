import java.util.*;

public class UserInteraction1
{
    // This is to create a wrapper to interact with the standard input
    // by means of an object of the class Scanner we will study later.
    static Scanner input = new Scanner(System.in).useLocale(Locale.US);

    public static void main(String [] args)
    {
        String name, lastName, email;

        System.out.print("\n Please enter your name: ");
        name = input.nextLine();

        System.out.print("\n Please enter your last name: ");
        lastName = input.nextLine();

        System.out.print("\n Please enter your email address: ");
        email = input.nextLine();


        System.out.println();
        System.out.println();
        System.out.println("Hello  Mr/Ms/Mrs " + name
                           + " " + lastName + " your email is " + email);
        System.out.println();
    }
}
