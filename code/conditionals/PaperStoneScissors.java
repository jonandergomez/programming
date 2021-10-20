import java.util.*;

public class PaperStoneScissors
{
    public static void main(String [] args)
    {
        Scanner input = new Scanner(System.in);

        System.out.print("Please, provide your option: ");
        String userOption = input.nextLine().trim().toLowerCase();
        String computerOption = generateComputerOption().toLowerCase();

        String result = null;
        switch (userOption) {
            case "paper":
                switch (computerOption) {
                    case "paper": result = "tied!!!"; break;
                    case "stone": result = "User wins!!!"; break;
                    case "scissors": result = "Computer wins!"; break;
                }
                break;
            case "stone":
                switch (computerOption) {
                    case "paper": result = "Computer wins!"; break;
                    case "stone": result = "tied!!!"; break;
                    case "scissors": result = "User wins!!!"; break;
                }
                break;
            case "scissors":
                switch (computerOption) {
                    case "paper": result = "User wins!!!"; break;
                    case "stone": result = "Computer wins!"; break;
                    case "scissors": result = "tied!!!"; break;
                }
                break;
            default:
                System.out.println("You entered an invalid option!");
        }
        if (result != null) System.out.println(result);
    }

    public static String generateComputerOption()
    {
        String option = null;

        switch ((int)(3 * Math.random())) {
            case 0: option = "Scissors"; break;
            case 1: option = "Paper"; break;
            case 2: option = "Stone"; break;
            default: System.out.println("IMPOSSIBLE!");
                     System.exit(1);
        }
        return option;
    }
}
