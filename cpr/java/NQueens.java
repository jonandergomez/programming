
package es.upv.etsinf.backtraking;

class NQueens
{
	private boolean	completed; // not used here because the goal is to count valid solutions
    private int     n;
	private int		numSolutions;
    private int []  solution;

	public NQueens()
	{
		numSolutions = 0;
	}

	private boolean isSolution(int k)
	{
		return (k == n);
	}
    /*
        This method performs the bound implicitly by checking which cells are
        free to set a queen.

        This method returns the valid positions in the k-th row.
    */
	private void buildCandidates(int k, boolean [] possibleNextSteps)
	{
		for (int i = 0; i < n; i++) {

			boolean legal = true;

			for (int j = 0; j < k && legal; j++) {
				if (i == solution[j]) legal = false;
				if (Math.abs(k - j) == Math.abs(i - solution[j])) legal = false;
			}
			possibleNextSteps[i] = legal;
		}
	}
	private void processSolution()
	{
		numSolutions++;
	}

	private void backtrack(int k)
	{
		if (isSolution(k)) {

			processSolution();

		} else {

			boolean [] possibleNextSteps = new boolean [n];

			buildCandidates(k, possibleNextSteps);
			for (int i = 0; i < possibleNextSteps.length; i++) {
				if (possibleNextSteps[i]) {
					solution[k] = i;
					backtrack(k + 1);
                    solution[k] = -1;
				}
				if (completed) return;
			}
		}
	}

	public void computeSolutions(int n)
	{
        this.n = n;
		this.completed = false;
		this.numSolutions = 0;
        this.solution = new int [n];

		backtrack(0);
	}
	public int numSolutions()
	{
		return numSolutions;
	}

	public static void main(String [] args)
	{
		NQueens	chessBoard = new NQueens();

		int n = 8;

		if (args.length > 0) n = Integer.parseInt(args[0]);

		chessBoard.computeSolutions(n);

		System.out.println(chessBoard.numSolutions());
	}
}
