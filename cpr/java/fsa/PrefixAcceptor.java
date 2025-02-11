package es.upv.etsinf.fsa;

import java.util.*; 

public class PrefixAcceptor
{
    private class MyPattern 
    {
        private String              pattern;
        private int                 counter;
        private ArrayList<Integer>  positions;

        public MyPattern(String pattern)
        {
            this.pattern = pattern;
            this.counter = 0;
            this.positions = new ArrayList<Integer>();
        }

        public String getPattern() { return pattern; }
        public int getCounter() { return counter; }
        public ArrayList<Integer> getPositions() { return positions; }
        public void resetCounter() { counter = 0; }
        public void increaseCounter() { ++counter; }
        public void cleanPositions()
        {
            this.counter = 0;
            this.positions = new ArrayList<Integer>();
        }
        public void addNewPosition(int pos)
        {
            this.positions.add(pos);
            ++counter;
        }
    }

    private Vertex                      root;
    private Hashtable<String, Vertex>   vertices;
    private LinkedList<MyPattern>       patterns;

    /** Constructor
      * 
      */
    public PrefixAcceptor()
    {
        this.root = new Vertex(null);
        this.vertices = new Hashtable<String, Vertex>();
        this.patterns = new LinkedList<MyPattern>();
    }

    /** Adds a new pattern to be accepted.
      *
      * @param pattern An string with a new pattern to be accepted.
      */
    public void add(String pattern)
    {
        if (pattern == null || pattern.length() == 0)
            throw new RuntimeException("Invalid pattern provided!");

        this.patterns.add(new MyPattern(pattern));
    }

    private void add(MyPattern p)
    {
        String pattern = p.getPattern();

        StringBuffer sb = new StringBuffer();

        Vertex current = root;

        for (int i = 0; i < pattern.length(); i++) {

            char symbol = pattern.charAt(i);

            sb.append(symbol);

            Vertex next = current.getNext(symbol);

            if (next == null) {
                next = new Vertex(sb.toString());
                current.connect(symbol, next);
                this.vertices.put(sb.toString(), next);
            }

            current = next;
        }
    }

    public void compile()
    {
        Hashtable<Character, Integer> symbols = new Hashtable<Character, Integer>();

        // creation of the trie
        for (MyPattern p : this.patterns) {
            this.add(p);

            for (int i = 0; i < p.getPattern().length(); i++) {
                symbols.put(p.getPattern().charAt(i), 0);
            }
        }

        // add each pattern to all the vertices it is completed
        for (Vertex v : this.vertices.values()) {
            for (MyPattern p : this.patterns) {
                if (v.getSubsequence().endsWith(p.getPattern())) {
                    v.add((Object)p);
                    //System.out.println(v.getSubsequence() + " --> " + p.getPattern());
                }
            }
        }

        // connects vertices with previous ones when necessary
        for (Vertex v : this.vertices.values()) {
            for (char symbol : symbols.keySet()) {
                if (v.getNext(symbol) == null) {
                    String s = v.getSubsequence(), t = null;
                    Vertex w = null;
                    for (int i = 1; i <= s.length() && w == null; i++) {
                        t = s.substring(i) + symbol;
                        w = this.vertices.get(t);
                    }
                    if (w != null) {
                        v.connect(symbol, w);
                    }
                }
            }
        }
    }

    public void resetCounters()
    {
        for (MyPattern p : this.patterns) p.resetCounter();
    }

    public Vertex resetSearch() { return this.root; }
    public Vertex parseSymbol(char symbol, Vertex current)
    {
        Vertex next = current.getNext(symbol);

        return (next != null) ? next : this.root;
    }

    public void processText(String text)
    {
        Vertex current = this.resetSearch();

        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);

            Vertex next = current.getNext(symbol);
            if (next != null) {
                if (next.isTerminal()) {
                    for (Object o : next.getReached()) {
                        MyPattern p = (MyPattern)o;
                        //p.increaseCounter();
                        p.addNewPosition(i - p.getPattern().length() + 1);
                    }
                }
                current = next;
            } else {
                current = this.root;
            }
        }
    }

    public int [] getCounters()
    {
        int [] counters = new int [this.patterns.size()];

        int i = 0;
        for (MyPattern p : this.patterns) {
            counters[i++] = p.getCounter();
        }

        return counters;
    }
    public Object [] getPositions()
    {
        @SuppressWarnings({"unchecked"})
        ArrayList<Integer> [] positions = new ArrayList [this.patterns.size()];

        int i = 0;
        for (MyPattern p : this.patterns) {
            positions[i++] = p.getPositions();
        }

        return positions;
    }

    public void printResults()
    {
        for (MyPattern p : this.patterns) {
            System.out.printf(" %10d  %s\n", p.getCounter(), p.getPattern());
        }
    }
}

/*
    pattern 1: "aab"
    pattern 2: "aabaab"

    text: "aabaabaabaab"

       "aab" appears 4 times
    "aabaab" appears 3 times

    pattern 1: "aa"
    pattern 2: "aaa"
    pattern 3: "aaaa"

    text: "aaaaaaaaaa"

      "aa" appears 9 times
     "aaa" appears 8 times
    "aaaa" appears 7 times

    pattern 1: "ver"
    pattern 2: "server"

    text: "verserver"

       "ver" appears 2 times
    "server" appears 1 time
*/
