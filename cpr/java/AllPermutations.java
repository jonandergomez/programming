
package es.upv.etsinf.backtraking;

import java.util.Vector;

class AllPermutations
{
    private boolean     completed; // In this example is not used because all permutations should be generated.
    private boolean []  used;
    private int         n;
    private int []      solution;

    private boolean isSolution(int k)
    {
        return (k == n);
    }
    private Vector<Integer> buildCandidates(int k)
    {
        Vector<Integer> candidates = new Vector<Integer>();

        for (int i = 1; i <= n; i++) {
            if (! used[i]) candidates.add(i);
        }

        return candidates;
    }
    private void processSolution(int k)
    {
        System.out.print("{");
        for (int i = 1; i <= k; i++) {
            System.out.print(" " + solution[i]);
        }
        System.out.println(" }");
    }

    private void backtrack(int k)
    {
        if (isSolution(k)) {

            processSolution(k);

        } else {

            k++;
            Vector<Integer> candidates = buildCandidates(k);
            for(int value : candidates) {
                solution[k] = value;
                used[value] = true;
                backtrack(k);
                used[value] = false;
                if (completed) return;
            }
            solution[k] = 0;
        }
    }

    public void generatePermutations(int n)
    {
        this.n = n;
        this.completed = false;
        this.used = new boolean [n + 1];
        this.solution = new int [n + 1];

        backtrack(0);
    }

    public static void main(String [] args)
    {
        AllPermutations    ap = new AllPermutations();

        int n = 3;

        if (args.length > 0) n = Integer.parseInt(args[0]);

        ap.generatePermutations(n);
    }
}
