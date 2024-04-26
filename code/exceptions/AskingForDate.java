import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Date;

/**
 * Example of class for asking the user for the three components a date.
 * Objects of this class will be used reading values from standard input
 * and checking if the complete date is correct.
 *
 * @author Jon Ander Gómez (jon@dsic.upv.es)
 * @version 1.0 (March 2012)
 */
public class AskingForDate
{
    /**
     * Class variable for maintaining the number of days per month in a year.
     */
    private static final int daysPerMonth[] = {0,
                                              31, 28, 31, 30, 31, 30,
                                              31, 31, 30, 31, 30, 31};

    /**
     * Instance variable for storing the reference to the object of the class
     * Scanner related to standard input.
     */
    private Scanner input;

    /**
     * Constructor for preparing objects of this class.
     *
     * @param input Object of the Scanner class.
     */
    public AskingForDate(Scanner input)
    {
        if (input != null)
            this.input = input;
        else
            this.input = new Scanner(System.in);
    }

    /**
     * Method for reading a date from standard input and checking if it is a legal date.
     *
     * @param prompt <b>String</b> object for prompting to the user.
     *
     * @return An object of class <b>Date</b> representing the date typed by the user.
     */
    public Date readDate(String prompt)
    {
        boolean dateOK;
        int     dayOfMonth = 0, month = 0, year = 0;

        do {
            // we are optimistic by default ;-)
            dateOK = true;

            System.out.printf("%s (dd mm yyyy): ", prompt);

            try {
                dayOfMonth = readInt(1, 31);
                month      = readInt(1, 12);
                year       = readInt(1, 3000);

                checkDate(dayOfMonth, month, year);
            }
            catch (InputMismatchException ime)
            {
                dateOK = false;
                System.out.printf("ERROR: %s\n", ime.getMessage());
                input.nextLine(); // Clean input buffer
            }
            catch (IllegalDateException ide)
            {
                dateOK = false;
                System.out.printf("%s\n", ide.getMessage());
                input.nextLine(); // Clean input buffer
            }
        } while (! dateOK);


        Calendar cal = Calendar.getInstance();
        cal.set(year, month - 1, dayOfMonth);

        return cal.getTime();
    }

    /**
     * Method for reading an integer value from standard input and checking if it is
     * in the specified range.
     *
     * @param lowerBound Integer value representing the minimum accepted value.
     * @param upperBound Integer value representing the maximum accepted value.
     *
     * @return The integer value type by the user.
     */
    private int readInt(int lowerBound, int upperBound)
        throws InputMismatchException
    {
        int value = input.nextInt();

        if (value < lowerBound || value > upperBound) {
            String errorMsg = String.format("%d is not in [%d,%d]",
                                            value, lowerBound, upperBound);
            throw new InputMismatchException(errorMsg);
        }

        return value;
    }

    /**
     * Private method for checking if a given date is a legal date.
     * This method throws an exception of the user-defined class <b>IllegalDateException</b>.
     *
     * @param d  Day of month.
     * @param m  Month of the year in the range [1,12].
     * @param y  Year.
     */
    private void checkDate(int d, int m, int y)
        throws IllegalDateException
    {
        if (m < 1 || m > 12)
            throw new IllegalDateException("Incorrect value for the month!");

        boolean leapYear = ((y % 4) == 0 && (y % 100) != 0) || (y % 400) == 0;

        int maxD = daysPerMonth[m];
        if (leapYear && m == 2) maxD++;

        if (d < 1 || d > maxD)
            throw new IllegalDateException("Incorrect value for the day of the month!");
    }
}