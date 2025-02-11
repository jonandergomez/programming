package es.upv.etsinf.strings;

import es.upv.etsinf.fsa.PrefixAcceptor;

import java.util.*;

public class StringSearchCheck
{
    public static void main(String [] args)
    {
        Random r = new Random();

        boolean do_basic_test = false;

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--basic"))
                do_basic_test = true;
        }

        String s = null;
        String [] patterns = null;

        if (do_basic_test) {
            s = "aabaabaabaab aaaaaaaaaa verserver";
            patterns = new String [7];
            patterns[0] = "aa";
            patterns[1] = "aaa";
            patterns[2] = "aaaa";
            patterns[3] = "aab";
            patterns[4] = "aabaab";
            patterns[5] = "ver";
            patterns[6] = "server";
        } else {
            s = randomFill(r, 1000000); // 20000 + r.nextInt(20001) );
            patterns = new String [1000];

            int i = 0;
            while (i < patterns.length/2) patterns[i++] = randomFill(r, 1 + r.nextInt(20));
            while (i < patterns.length) {
                int l = 1 + r.nextInt(30);
                int p = r.nextInt(s.length() - l);
                patterns[i++] = s.substring(p, p + l);
            }
        }

        Object [] positions1 = new Object [patterns.length];
        Object [] positions2 = new Object [patterns.length];
        Object [] positions3 = new Object [patterns.length];
        Object [] positions4 = null;

        System.out.println();
        System.out.println("       |s| = " + s.length());
        System.out.println("|patterns| = " + patterns.length);
        System.out.println();

        long ms = System.currentTimeMillis();
        for (int i = 0; i < patterns.length; i++) {
            positions1[i] = StringSearch.directSearch(s, patterns[i]);
        }
        ms = System.currentTimeMillis() - ms;
        System.out.printf("Direct Search             CPU time %9d\n", ms);

        ms = System.currentTimeMillis();
        for (int i = 0; i < patterns.length; i++) {
            positions2[i] = StringSearch.boyerMooreSearchSimple(s, patterns[i]);
        }
        ms = System.currentTimeMillis() - ms;
        System.out.printf("Boyer-Moore Simple Search CPU time %9d\n", ms);

        ms = System.currentTimeMillis();
        for (int i = 0; i < patterns.length; i++) {
            positions3[i] = StringSearch.boyerMooreSearch(s, patterns[i]);
        }
        ms = System.currentTimeMillis() - ms;
        System.out.printf("Boyer-Moore Search        CPU time %9d\n", ms);

        ms = System.currentTimeMillis();
        PrefixAcceptor pa = new PrefixAcceptor();
        for (int i = 0; i < patterns.length; i++) pa.add(patterns[i]);
        pa.compile();
        pa.processText(s);
        int [] counters4 = pa.getCounters();
        positions4 = pa.getPositions();
        ms = System.currentTimeMillis() - ms;
        System.out.printf("Prefix Acceptor Search    CPU time %9d\n", ms);

        for (int i = 0; i < patterns.length; i++) {

            @SuppressWarnings({"unchecked"})
            ArrayList<Integer> pos1 = (ArrayList<Integer>) positions1[i];
            @SuppressWarnings({"unchecked"})
            ArrayList<Integer> pos2 = (ArrayList<Integer>) positions2[i];
            @SuppressWarnings({"unchecked"})
            ArrayList<Integer> pos3 = (ArrayList<Integer>) positions3[i];
            @SuppressWarnings({"unchecked"})
            ArrayList<Integer> pos4 = (ArrayList<Integer>) positions4[i];

            System.out.printf("occurrences %6d %6d %6d %6d   pattern: %s\n", pos1.size(), pos2.size(), pos3.size(), pos4.size(), patterns[i]);

            if (pos1.size() != pos2.size()) throw new RuntimeException("Different number of occurences found! 1 vs 2");
            if (pos1.size() != pos3.size()) throw new RuntimeException("Different number of occurences found! 1 vs 3");
            if (pos1.size() != pos4.size()) throw new RuntimeException("Different number of occurences found! 1 vs 4");

            for (int j = 0; j < pos1.size(); j++) {

                if ((int)pos1.get(j) != (int)pos2.get(j))
                    throw new RuntimeException(String.format("%s en pos %d != %d de \n", patterns[i], pos1.get(j), pos2.get(j))); 

                if ((int)pos1.get(j) != (int)pos3.get(j))
                    throw new RuntimeException(String.format("%s en pos %d != %d de \n", patterns[i], pos1.get(j), pos3.get(j))); 

                if ((int)pos1.get(j) != (int)pos4.get(j))
                    throw new RuntimeException(String.format("%s en pos %d != %d de \n", patterns[i], pos1.get(j), pos4.get(j))); 
            }
        }
    }

    public static String randomFill(Random r, int L)
    {
        char str[] = new char [L];

        //for (int i = 0; i < str.length; ++i) str[i] = (char)('a' + r.nextInt('z' - 'a' + 1));
        for (int i = 0; i < str.length; ++i) str[i] = (char)('a' + r.nextInt('c' - 'a' + 1));

        return new String( str );
    }
}
