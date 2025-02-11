package es.upv.etsinf.fsa;

import java.util.*; 

public class Vertex
{
    /** Subsequence of symbols to reach this vertex from the root.
      * It should be null for root vertex of the tree.
      */
    private String w;

    /** Linked list of completed patterns when this vertex is reached. */
    private LinkedList<Object>  reached;

    /** Hash table with the reachable vertices from the current one. */
    private Hashtable<Character, Vertex>  edges;

    /** Creates a new vertex given an string.
      * 
      * @param w The subsequence of symbols reaching this vertex.
      */
    public Vertex(String w)
    {
        this.w = w;
        this.edges = new Hashtable<Character, Vertex>();
        this.reached = new LinkedList<Object>();
    }

    /** Returns the subsequence of the current vertex.
      *
      * @return An object of the class <code>String</code>.
      */
    public String getSubsequence()
    {
        return w;
    }

    public LinkedList<Object> getReached()
    {
        return this.reached;
    }

    public boolean isTerminal() { return reached.size() > 0; }
    public void add(Object p) { reached.add(p); }

    /** Connects the current vertex with another one using the symbol.
      *
      * @param symbol The symbol used to reach the next vertex from this one.
      * @param next The vertex reached from this one after consuming the symbol.
      */
    public void connect(char symbol, Vertex next)
    {
        if (this.edges.containsKey(symbol))
            throw new RuntimeException("Trying to add a new edge using an existing symbol " + symbol);

        this.edges.put(symbol, next);
    }

    /** Returns the vertex reached from the current one consuming the symbol.
      * If the symbol is not a valid key in edges <code>null</code> is returned.
      *
      * @return An object of the class <code>Vertex</code>.
      */
    public Vertex getNext(char symbol)
    {
        return this.edges.get(symbol);
    }
}
