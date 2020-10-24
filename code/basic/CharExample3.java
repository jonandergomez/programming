public class CharExample3
{
    public static void main(String [] args)
    {
        char letterA = 'A';
        char letterB = (char)(letterA + 1);
        char letterC = letterB;
        letterC++;
        char letterN = 'n';
        letterN = (char)(letterN + ('A' - 'a'));

        System.out.println(letterA + "\t" + letterB + "\t" + 
                           letterC + "\t" + letterN);
    }
}
