public class Methods1 {

    public static void change(int j, int k)
    {
        int aux = j;
        j = k;
        k = aux;
        System.out.println("Inside: " + j + " " + k);
    } // end of change()

    public static void inc(int j, int k)
    {
        j++;
        k++;
        System.out.println("Inside: " + j + " " + k);
    } // end of inc()

    public static void main(String[] args)
    {
        int j = 1335;
        int k = 3672;

        System.out.println("Before: " + j + " " + k);
        change(j, k);
        inc(k, j);
        System.out.println("After: " + j + " " + k);
    } // end of main()
} // end of class
