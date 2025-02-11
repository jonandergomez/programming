package es.upv.etsinf.strings;

public class StringHash
{
    // hash function extracted from:
    // http://stackoverflow.com/questions/114085/fast-string-hashing-algorithm-with-low-collision-rates-with-32-bit-integer
    public static long hashCode( String s )
    {
        long hash = 0;

        for( int i=0; i < s.length(); i++ ) {

            hash += s.charAt(i);
            hash += (hash << 10);
            hash ^= (hash >>  6);
        }

        hash += (hash <<  3);
        hash ^= (hash >> 11);
        hash += (hash << 15);

        return hash;
    }
    public static long hashCode( String s, int hashSize )
    {
        return hashCode( s ) % hashSize;
    }
}
