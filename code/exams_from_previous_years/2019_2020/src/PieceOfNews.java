package etsinf.prg.exam2;
/**
 * Class PieceOfNews.
 * @author IIP - Parcial 1
 * @version Academic year 2019-20
 */
public class PieceOfNews
{
    /** News type AUDIO with value 0. */
    public static final int AUDIO = 0;
    /** News type VIDEO with value 1. */
    public static final int VIDEO = 1;
    /** News type TEXT with value 2. */
    public static final int TEXT = 2;

    private TimeInstant instant;
    private String link;
    private int echoedBy;
    private int type;

    /** Creates a <code>PieceOfNews</code>
     *  generated at time <code>i</code>,
     *  stored in the file referenced by the link <code>l</code>,
     *  echoed by a total of <code>n</code> media,
     *  and with news type <code>t</code>
     *  (<code>AUDIO</code>, <code>VIDEO</code> or <code>TEXT</code>).
     */
    public PieceOfNews(TimeInstant i, String l, int n, int t)
    {
        instant = i;
        link = l;
        echoedBy = n;
        type = t;
    }

    /**
     *  Returns the time instant when the news was generated.
     */
    public TimeInstant getInstant() { return instant; }

    /**
     *  Returns the link where the news is stored.
     */
    public String getLink() { return link; }

    /**
     *  Returns the news type.
     */
    public int getType() { return type; }

    public boolean equals(Object o)
    {
        return o instanceof PieceOfNews
            && this.instant.equals(((PieceOfNews) o).instant)
            && this.echoedBy == ((PieceOfNews) o).echoedBy
            && this.type == ((PieceOfNews) o).type;
    }

    /**
     *  Returns a negative integer if the news stored in <code>this</code>,
     *  the current object,
     *  is less popular than the news stored in <code>other</code>,
     *  returns a positive integer if the news stored in <code>this</code>
     *  is more popular than the one stored in <code>other</code>,
     *  otherwise returns <code>0</code> for indicating that both
     *  news are equally popular.
     */
    public int compareTo(PieceOfNews other)
    {
        int res = this.instant.compareTo(other.instant);
        if (res == 0) {
            res = this.echoedBy - other.echoedBy;
            if (res == 0) {
                res = this.type - other.type;
            }
        }
        return res;
    }

    public String toString()
    {
        String res = "";
        res += instant + " " + link + " " + echoedBy + " (";
        switch (type) {
            case TEXT : res += "text)"; break;
            case VIDEO: res += "video)"; break;
            default   : res += "audio)";
        }
        return res;
    }
}
