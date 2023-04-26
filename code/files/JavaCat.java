import java.util.Scanner;

public class JavaCat
{
    public static void main(String [] args)
    {
        Scanner input = new Scanner(System.in);

        while (input.hasNext()) {
            String line = input.nextLine();
            System.out.println(line);
        }
    }
}
