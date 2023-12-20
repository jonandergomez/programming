import java.util.*;

public class ExampleOfStandardInputOutput
{
    private static Scanner input = new Scanner(System.in).useLocale(Locale.US);

    public static void main(String [] args)
    {
        while (input.hasNext()) { // Check if more values are available in the standard input
            int value = input.nextInt(); // Read next token in the standard input and convert it to an integer

            if ((value % 2) == 0) {
                System.out.println(value);
            } else {
                System.err.println(value);
            }
        }
    }
}
