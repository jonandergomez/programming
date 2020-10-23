public class CharExample1
{
    public static void main(String [] args)
    {
        char upperCaseA = 'A';
        char lowerCaseZ = 'z';
        char digit0 = '0';
        char whiteSpace = ' ';
        char newLine = '\n';
        char symbol = '?';
        char tab = '\t';

        System.out.printf("  |%c|  %5d\n", upperCaseA, (int)upperCaseA);
        System.out.printf("  |%c|  %5d\n", lowerCaseZ, (int)lowerCaseZ);
        System.out.printf("  |%c|  %5d\n", digit0, (int)digit0);
        System.out.printf("  |%c|  %5d\n", whiteSpace, (int)whiteSpace);
        System.out.printf("  |%c|  %5d\n", newLine, (int)newLine);
        System.out.printf("  |%c|  %5d\n", symbol, (int)symbol);
        System.out.printf("  |%c|  %5d\n", tab, (int)tab);
    }
}
