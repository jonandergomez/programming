import java.util.*;
import java.io.*;


public class TestRainfall
{
    private static Scanner input = new Scanner(System.in).useLocale(Locale.US);

    private static final String monthName [] = {"*",
                                                "January", "February", "March",
                                                "April",   "May",      "June",
                                                "July",    "August",   "September",
                                                "October", "November", "December" };

    public static void main(String [] args)
        throws Exception
    {
        File f = new File("bobo.txt");
        System.out.println("The current working directory is " + f.getAbsoluteFile().getParent());


        Rainfall rf = null;
        int    option = 0;
        int    threshold;
        double litres_per_square_meter, lowerBound, upperBound;
        String res;
        String [] result2;

        do {
            option = menu();
            switch (option) {
                case 1:
                    int year = 0;
                    System.out.print("\n Which year? ");
                    year = input.nextInt();
                    rf = new Rainfall(year);
                    rf.randomFill();
                    break;
                case 2:
                    System.out.println(rf.toString());
                    System.out.print("\n Press ENTER to continue ... ");
                    input.nextLine();
                    break;

                case 3:
                    double [][] result = rf.computeAverages();
                    reportAveragesAndAccumulated(result);
                    break;

                case 4:
                    System.out.print("\n\t Please, enter the threshold in litres/m2: ");
                    threshold = input.nextInt();
                    result2 = rf.daysItRainedMoreThan(threshold);
                    for (int i = 0; i < result2.length; i++) {
                        System.out.println(result2[i]);
                    }
                    System.out.print("\n Press ENTER to continue ... ");
                    input.nextLine();
                    break;

                case 5:
                    double maxRainfall = rf.maxRainfall();
                    String [] result3 = rf.daysItRainedMoreThan(maxRainfall - 1.0e-5);
                    for (int i = 0; i < result3.length; i++) {
                        System.out.println(result3[i]);
                    }
                    System.out.print("\n Press ENTER to continue ... ");
                    input.nextLine();
                    break;

                case 6:
                    System.out.print("\n\t Please, enter the threshold in litres/m2: ");
                    threshold = input.nextInt();
                    res = rf.findSubsquenceOfAtLeastThreeDaysItRainedMoreThan(threshold);
                    System.out.println("\n\n" + res + "\n");
                    break;

                case 7:
                    System.out.print("\n\t Please, enter the value in litres/m2 you want to find: ");
                    litres_per_square_meter = input.nextDouble();
                    res = rf.findMeasurement(litres_per_square_meter);
                    System.out.println("\n\n" + res + "\n");
                    break;

                case 8:
                case 9:
                    System.out.print("\n\t Please, enter the lower and upper bounds litres/m2: ");
                    lowerBound = input.nextDouble();
                    upperBound = input.nextDouble();
                    if (option == 8) {
                        res = rf.dayItRainedBetween(lowerBound, upperBound);
                        System.out.println("\n\n" + res + "\n");
                    } else if (option == 9) {
                        result2 = rf.daysItRainedBetween(lowerBound, upperBound);
                        for (int i = 0; i < result2.length; i++) {
                            System.out.println(result2[i]);
                        }
                        System.out.print("\n Press ENTER to continue ... ");
                        input.nextLine();
                    }
                    break;

                case 11:
                    System.out.print("\n\t Enter the filename: ");
                    String filename = input.next();
                    rf = Rainfall.loadFromFile(filename);
                    break;

                case 12:
                    PrintWriter pw = new PrintWriter(String.format("rainfall-%d.txt", rf.getYear()));
                    pw.println(rf.toString());
                    pw.close();
                    break;

                default:
            }
        } while (option != 0);
    }

    private static void reportAveragesAndAccumulated(double [][] result)
    {
        System.out.println("                      Accumulated -       Average ");
        System.out.println("  Month           -     rainfall  -       rainfall ");
        System.out.println("-----------------------------------------------------");
        for (int month = 1; month <= 12; month++) {
            System.out.printf(" %-15s  -  %10.2f   -   %10.2f\n",
                    monthName[month], result[month][0], result[month][1]);
        }
        System.out.println("-----------------------------------------------------");
        System.out.printf(" %-15s  -  %10.2f   -   %10.2f\n",
                "All the year", result[0][0], result[0][1]);
    }
    private static int menu()
    {
        int option = -1;
        boolean valid_option = false;
        do {
            System.out.println("\n\n ----- MENU -----\n");
            System.out.println("\t 1. Create the data structure.");
            System.out.println("\t 2. Show the contents.");
            System.out.println("\t 3. Average all months and global average.");
            System.out.println("\t 4. Days it rained more than ... ");
            System.out.println("\t 5. Dates it rained the most. ");
            System.out.println("\t 6. Subsequence of days it rained more than ... ");
            System.out.println("\t 7. Search for a specific value of litres per square meter. ");
            System.out.println("\t 8. Day it rained in a given interval.");
            System.out.println("\t 9. Days it rained in a given interval.");
            System.out.println();
            System.out.println("\t 11. Load from a file. ");
            System.out.println("\t 12. Store to a file. ");
            System.out.println();
            System.out.println("\t 0. STOP ");

            System.out.print("\n\n\t\t Choose an option: ");
            option = input.nextInt();
            input.nextLine();

            if (0 <= option && option <= 12) {
                valid_option = true;
            } else {
                valid_option = false;
                System.err.println("\n\n SORRY! You entered and invalid option!");
            }

        } while(! valid_option);

        return option;
    }
}
