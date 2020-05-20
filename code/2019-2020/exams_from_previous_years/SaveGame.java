
public class SaveGame
{
    final public static int MINUTES_PER_DAY = 24*60;

    private String  region;
    private int     identifier;
    private int     position;
    private float   progress;

    public SaveGame( String region, int identifier, int position, TimeInstant t )
    {
        this.region = region;
        this.identifier = identifier;
        this.position = position;

        this.progress = (float)(100.0 * t.toMinutes() / MINUTES_PER_DAY);
    }

    public String toHHMM()
    {
        int minutes = (int)( (progress * MINUTES_PER_DAY) / 100 );

        return String.format( "%02d:%02d", minutes/60, minutes%60 );
    }

    @Override
    public boolean equals( Object o )
    {
        if ( o instanceof SaveGame ) {

            SaveGame other = (SaveGame)o;

            return this.region.equals(other.region)
                && this.identifier == other.identifier
                && this.progress == other.progress;

        } else {
            return false;
        }
    }

    @Override
    public String toString()
    {
        String format = "JAP";

        switch( this.region ) {
            case "SCES" :
            case "SLES" : format="PAL"; break;
            case "SCUS" :
            case "SLUS" : format="USA"; break;
            default     : format="JAP";
        }

        return String.format( "%s: %s_%06.2f - %d - %.1f%%", 
                               format,
                                   region,
                                      identifier/100.0,
                                               position,
                                                    progress );
    }

    /**
     * This method should return a value:
     * <ul>
     *   <li> less than 0 if <code>this</code> must go before <code>other</code> in a sorted list, </li>
     *   <li> greater than 0 if <code>this</code> must go after <code>other</code> in a sorted list, </li>
     *   <li> equal to 0 if the order of <code>this</code> and <code>other</code> is not relevant. </li>
     * </ul>
     */
    public int compareTo( SaveGame other )
    {
        int rc = this.region.compareTo( other.region );

        if ( rc != 0 ) {
            return rc;
        } else {
            rc = this.identifier - other.identifier;
            if ( rc != 0 ) {
                return rc;
            } else {
                if ( this.progress < other.progress ) {
                    return -1;
                } else if ( this.progress > other.progress ) {
                    return 1;
                } else {
                    return 0;
                }
            }
        }
    }
}
