
package es.upv.etsinf.strings;

import java.util.*;

public class StringSearch
{
    private final static int ALPHABET_SIZE = 128;
    private final static int ALPHABET_MASK = 255;

    private static int charToInt(char c)
    {
        return (int)c & ALPHABET_MASK;
    }
    public static int directSearchFirst(String s, String p)
    {
        int ls = s.length(),
            lp = p.length();

        int upper_limit = ls - lp;

        for (int i = 0; i <= upper_limit; i++) {
            int j = 0;
            while (j < lp && s.charAt(i+j) == p.charAt(j)) j++;
            if (j == lp) return i;
        }
        return -1;
    }
    public static ArrayList<Integer> directSearch(String s, String p)
    {
        int ls = s.length(),
            lp = p.length();

        int upper_limit = ls - lp;

        ArrayList<Integer> l = new ArrayList<Integer>();

        for (int i = 0; i <= upper_limit; i++) {
            int j = 0;
            while(j < lp && s.charAt(i+j) == p.charAt(j)) j++;
            if (j == lp) l.add(i);
        }
        return l;
    }
    public static int boyerMooreSearchSimpleFirst(String s, String p)
    {
        int [] d = new int [ALPHABET_SIZE];
        int ls = s.length(),
            lp = p.length();

        for (int i = 0; i < d.length; i++) d[i] = lp;

        for (int i = 0; i < lp - 1; i++) d[charToInt(p.charAt(i))] = lp - i - 1;

        int i = lp - 1;
        do {
            int j = lp - 1, k = i;
            while (j >= 0 && s.charAt(k) == p.charAt(j)) { --k; --j; }
            if (j < 0) return k + 1;
            i = i + d[charToInt(s.charAt(i))]; // The key is to use s[i] instead of s[k]
        } while (i < ls);

        return -1;
    }
    private static int min(int a, int b) { return (a <= b) ? a : b; }
    private static int max(int a, int b) { return (a >= b) ? a : b; }
    public static ArrayList<Integer> boyerMooreSearchSimple(String s, String p)
    {
        int [] L = new int [ALPHABET_SIZE];
        int [] R = new int [ALPHABET_SIZE];
        int ls = s.length(),
            lp = p.length();

        for (int i = 0; i < L.length; i++) L[i] = R[i] = lp;

        //for (int i = 0; i < lp - 1; i++) d[charToInt(p.charAt(i))] = lp - i - 1;
        for (int i = 0; i < lp; i++) R[charToInt(p.charAt(i))] = i;
        for (int i = lp - 1; i >= 0; i--) L[charToInt(p.charAt(i))] = i;
        /*
              0 1 2 3 4 5
              a b c a b c

           lp = 6

              L  R
           a  0  3
           b  1  4
           c  2  5
           *  6  6

            a b c z a b e d a b c a b c e d
            a b c a b c
              a b c a b c
                          a b c a b c
                            a b c a b c
                              a b c a b c
                                          a b c a b c
        */

        ArrayList<Integer> l = new ArrayList<Integer>();

        int i = lp - 1;
        do {
            int j = lp - 1, k = i;
            while (j >= 0 && s.charAt(k) == p.charAt(j)) { --k; --j; }
            if (j < 0) {
                l.add(k + 1); // pattern found at position i-lp+1 = k+1 because k = i-lp
                i++;
            } else {

                char ck = s.charAt(k);

                int shift = j;
                if (j <= L[ck]) {
                    shift = 1 + min(j, L[ck]);
                } else if (j >= R[ck]) {
                    shift = max(1, j - R[ck]);
                } else {
                    shift = 1;
                }
                i = i + shift;
            }
        } while(i < ls);

        return l;
    }
    public static ArrayList<Integer> boyerMooreSearchSimple_ol(String s, String p)
    {
        int [] d = new int [ALPHABET_SIZE];
        int ls = s.length(),
            lp = p.length();

        for (int i = 0; i < d.length; i++) d[i] = lp;

        //for (int i = 0; i < lp - 1; i++) d[charToInt(p.charAt(i))] = lp - i - 1;
        for (int i = 0; i < lp; i++) d[charToInt(p.charAt(i))] = min(i + 1, d[charToInt(p.charAt(i))]);
        /*
              0 1 2 3 4 5
              a b c a b c

           lp = 6

           a  6, 5, 2  -> 2
           b  6, 4, 1  -> 1
           c  6, 3     -> 3
           *  6        -> 6

            a b c z a b e d a b c a b c e d
            a b c a b c
              a b c a b c
                          a b c a b c
                            a b c a b c
                              a b c a b c
                                          a b c a b c
        */

        ArrayList<Integer> l = new ArrayList<Integer>();

        System.out.printf("\n\n %s\n", p);

        int i = lp - 1;
        do {
            int j = lp - 1, k = i;
            while (j >= 0 && s.charAt(k) == p.charAt(j)) { --k; --j; }
            if (j < 0) {
                l.add(k + 1); // pattern found at position i-lp+1 = k+1 because k = i-lp
                i++;
            } else {

            /* */
                char ck = s.charAt(k);
                char ci = s.charAt(i);
                System.out.printf("j %3d   k %6d %c %3d    i %6d %c %3d\n", j, k, ck, d[ck], i, ci, d[ci]);
            /* */

                //i = i + d[charToInt(s.charAt(i))]; // The key is to use s[i] instead of s[k]
                i = i + max(1, min(j, d[charToInt(s.charAt(k))]));
                //i = i + d[charToInt(s.charAt(k))];
            }
        } while(i < ls);

        return l;
    }
    public static int [][] badCharShiftRule(String p)
    {
        int [][] d = new int [ALPHABET_SIZE][p.length()];

        for (int i = 0; i < d.length; i++) {
            int k = 1; // a non-matching symbol at first position in the pattern must shift the pattern on the text one position
            for (int j = 0; j < p.length(); ++j) {
                if (charToInt(p.charAt(j)) == i) {
                    // if a non-matching symbol at position j in the pattern exists in another position of the pattern then it must move 
                    d[i][j] = 0;
                    k = 1;
                } else {
                    // a non-matching symbol at position j in the pattern must shift the pattern on the text one position additional to the one so far
                    d[i][j] = k++;
                }
            }
        }
        /*
               a b c a b c
            a  0 1 2 0 1 2
            b  1 0 1 2 0 1
            c  1 2 0 1 2 0

               a b c d e
            a  0 1 2 3 4
            b  1 0 1 2 3
            c  1 2 0 1 2
            d  1 2 3 0 1  
            e  1 2 3 4 0

            a b c z a b e d c a b c d e
            a b c d e
                    a b c d e
                        a b c d e
                              a b c d e
        */

        return d;
    }
    public static int boyerMooreSearchFirst(String s, String p)
    {
        int [][] d = badCharShiftRule(p);
        int ls = s.length(),
            lp = p.length();

        int i = lp - 1;
        do {
            int j = lp - 1, k = i;
            while (j >= 0 && s.charAt(k) == p.charAt(j)) { --k; --j; }
            if (j < 0) return k + 1; // pattern found at position i-lp+1 = k+1 because k = i-lp
            i = i + d[charToInt(s.charAt(k))][j];
        } while (i < ls);

        return -1;
    }
    public static ArrayList<Integer> boyerMooreSearch(String s, String p)
    {
        int [][] d = badCharShiftRule(p);
        int ls = s.length(),
            lp = p.length();

        ArrayList<Integer> l = new ArrayList<Integer>();

        // starting at position lp-1 means the pattern is aligned with the
        // first lp symbols of the text at the beggining of the search
        int i = lp - 1;
        do {
            int j = lp - 1, k = i;
            while (j >= 0 && s.charAt(k) == p.charAt(j)) { --k; --j; }
            if (j < 0) {
                l.add(k + 1); // pattern found at position i-lp+1 = k+1 because k = i-lp
                i++;
            } else {
                // move forward the maximum number of positions without loosing any occurrence of the pattern
                i = i + d[charToInt(s.charAt(k))][j];
            }
        } while (i < ls);

        return l;
    }
    /*
        s: aebbb
        p: ebb

        This example must run
    */

    public static void main(String [] args)
    {
        for (int i = 1; i < args.length; i++) {
            System.out.printf("%5d %5d %5d\n",  directSearchFirst(args[i], args[0]),
                                                boyerMooreSearchSimpleFirst(args[i], args[0]),
                                                boyerMooreSearchFirst(args[i], args[0]));
        }
    }
}
