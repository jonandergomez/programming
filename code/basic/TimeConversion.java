public class TimeConversion
{
    public static void main(String [] args)
    {
        final int SECONDS_PER_DAY = 24 * 60 * 60;
        long seconds = 765432;
        long days = seconds / SECONDS_PER_DAY;
        System.out.printf(" %d seconds are: \n %5d days\n", seconds, days);
        seconds = seconds % SECONDS_PER_DAY;
        long hours = seconds / (60 * 60);
        System.out.printf(" %5d hours\n", hours);
        seconds %= (60 * 60);
        long minutes = seconds / 60;
        System.out.printf(" %5d minutes\n", hours);
        seconds %= 60;
        System.out.printf(" %5d seconds\n", seconds);
    }
}
