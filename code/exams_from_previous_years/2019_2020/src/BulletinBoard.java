package etsinf.prg.exam2;
/**
 * Class BulletinBoard.
 * @version Academic year 2019-20
 */
public class BulletinBoard
{
    public static final int MINUTES = 1440; // 24 * 60
    private PieceOfNews[] bBoard;
    private int[] numPerType;

    public BulletinBoard()
    {
        bBoard = new PieceOfNews[MINUTES];
        numPerType = new int[3];
    }

    public boolean add(PieceOfNews p)
    {
        boolean added = false;
        int position = p.getInstant().toMinutes();
        if (bBoard[position] == null || p.compareTo(bBoard[position]) > 0) {
            // subtract 1 to the counter of news of a given type if it existed
            if (bBoard[position] != null) { numPerType[bBoard[position].getType()]--; }
            // sets the new piece of news, replacing the existing one if there is one
            bBoard[position] = p;
            // adds 1 to the counter of news of a the type the news belongs to
            numPerType[p.getType()]++;
            added = true;
        }
        return added;
    }

    public PieceOfNews isPosted(String link)
    {
        // search over an sparse array using the linear search

        PieceOfNews result = null;
        boolean found = false;
        // searches if the a piece of news with the provided link exists
        int i = 0;
        while (i < bBoard.length && !found) {
            // elements set to null are avoided
            if (bBoard[i] != null && link.equals(bBoard[i].getLink())) {
                found = true;
                result = bBoard[i];
            }
            i++;
        }
        return result;
    }

    /** Precondition: 0 <= type < 3 */
    public PieceOfNews[] filterByType(int type)
    {
        // creates the array for the result with the exact size according to
        // the counter of news of the specified type
        PieceOfNews[] result = new PieceOfNews[numPerType[type]];
        // visit the elements of the sparse array avoiding the ones set to null
        // and stopping when the result array is full
        for (int i = 0, j = 0; i < bBoard.length && j < numPerType[type]; i++) {
            // elements set to null are avoided
            if (bBoard[i] != null && bBoard[i].getType() == type) {
                result[j++] = bBoard[i];
            }
        }
        return result;
    }
}
