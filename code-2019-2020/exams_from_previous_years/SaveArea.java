
public class SaveArea
{
    public static final int MAX_STORED = 100;

    private int         size;
    private SaveGame [] storedGames;


    public SaveArea()
    {
        size=0;
        storedGames = new SaveGame[ MAX_STORED ];
    }

    public void removeTheOldestOne()
    {
        storedGames[0].setPosition( -1 );

        for( int i=1; i < size; i++ ) {
            
            storedGames[i-1] = storedGames[i];
            storedGames[i-1].setPosition( i-1 );
        }
        //storedGames[--size] = null;
        size--;
        storedGames[size] = null;
    }

    public boolean withAProgressGreaterThanOrEqualTo( SaveGame sg )
    {
        boolean found=false;

        for( int i=0; i < size && !found; i++ ) {
            if ( storedGames[i].getIdentifier == sg.getIdentifier() ) {
                if ( storedGames[i].getProgress() > sg.getProgress() ) {
                    found=true;
                }
            }
        }
        
        return found;
    }

    public boolean save( SaveGame sg )
    {
        if ( ! withAProgressGreaterThanOrEqualTo( sg ) ) {

            if ( size == storedGames.length ) removeTheOldestOne();

            sg.setPosition(size);

            storedGames[size++] = sg;

            return true;

        } else {

            return false;
        }
    }

    public SaveGame [] filterByRegion( String region )
    {
        int counter=0;
        for( int i=0; i < size; i++ ) {

            if ( storedGames[i].getRegion().equals( region ) ) counter++;
        }

        SaveGame [] result = new SaveGame [counter];
        int k=0;

        for( int i=0; i < size; i++ ) {

            if ( storedGames[i].getRegion().equals( region ) )
                result[k++] = storedGames[i];
        }

        return result;
    }
}
