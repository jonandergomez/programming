public class Exponential2
{
    public static void main(String [] args)
    {
        double x = 1.0;
        int n = 10;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--x")) {
                x = Double.parseDouble(args[i+1]);
            } else if (args[i].equals("--n")) {
                n = Integer.parseInt(args[i+1]);
            }
        }

        //double e_x = exp_1(x, n);
        //double e_x = exp_2(x, n);
        double e_x = exp_3(x, 1.0e-17);

        System.out.printf("our solution = %.17f\n", e_x);
        System.out.printf(" Math.exp(x) = %.17f\n", Math.exp(x));
    }

    public static double exp_1(double x, int n)
    {
        // Loop initialization begins
        double sum = 1.0, num = 1.0, den = 1.0;
        boolean sign = (x < 0.0);
        x = Math.abs(x);
        // Loop initialization ends

        for (int i = 1; i <= n; i++) { // Loop guard
            // Loop body begins
            num *= x;
            den *= i;
            sum += num / den;
            System.out.printf("%d %.17f %.17f %.17f\n", i, num, den, sum);
            // Loop body ends
        }

        return (sign) ? 1.0 / sum : sum;
    }

    public static double exp_2(double x, int n)
    {
        // Loop initialization begins
        double sum = 1.0, term = 1.0;
        // Loop initialization ends

        for (int i = 1; i <= n; i++) { // Loop guard
            // Loop body begins
            term *= x;
            term /= i;
            sum += term;
            //System.out.printf("%d %.17f %.17f\n", i, term, sum);
            // Loop body ends
        }

        return sum;
    }
    
    public static double exp_3(double x, double epsilon)
    {
        // Loop initialization begins
        double sum = 1.0, term = 1.0;
        int i = 0;
        boolean sign = (x < 0.0);
        x = Math.abs(x);
        // Loop initialization ends

        while (Math.abs(term) > epsilon) { // Loop guard
            // Loop body begins
            term *= x;
            term /= ++i;
            sum += term;
            System.out.printf("%d %.17f %.17f\n", i, term, sum);
            // Loop body ends
        }

        return (sign) ? 1.0 / sum : sum;
    }
}
