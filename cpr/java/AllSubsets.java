
package es.upv.etsinf.backtraking;

class AllSubsets
{
    private boolean     completed; // In this example is not used because all permutations should be generated.
    private boolean []  solution;
    private int         n;

    private boolean isSolution(int k)
    {
        return (k == n);
    }
    private boolean [] buildCandidates()
    {
        boolean [] c = new boolean [2];

        c[0] = false;
        c[1] = true;

        return c;
    }
    private void processSolution()
    {
        System.out.print("{");
        for (int i = 1; i <= n; i++) {
            if (solution[i]) System.out.print(" " + i);
        }
        System.out.println(" }");
    }

    private void backtrack(int k)
    {
        if (isSolution(k)) {

            processSolution();

        } else {

            k++;
            boolean [] candidates = buildCandidates();
            for (int i = 0; i < candidates.length; i++) {
                solution[k] = candidates[i];
                backtrack(k);
                solution[k] = false;

                if (completed) return;
            }
        }
    }

    public void generateSubsets(int n)
    {
        this.completed = false;
        this.n = n;
        this.solution = new boolean [n+1];

        backtrack(0);
    }

    public static void main(String [] args)
    {
        AllSubsets    as = new AllSubsets();

        int n = 3;

        if (args.length > 0) n = Integer.parseInt(args[0]);

        as.generateSubsets(n);
    }
}
