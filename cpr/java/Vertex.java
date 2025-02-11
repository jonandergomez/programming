
package es.upv.etsinf.graphs;

import java.util.Vector;

public class Vertex
{
    protected int             id; // Used as index inside the graph.
    protected String          label;
    protected Vector<Edge>    edges;
    protected int             inDegree;

    public Vertex(int id, String label)
    {
        this.id = id;
        this.label = label;
        this.edges = new Vector<Edge>();
        this.inDegree = 0;
    }

    public int getId() { return id; }
    public String getLabel() { return label; }

    public void setId(int id) { this.id = id; }

    public Edge getEdge(int i) { return edges.get(i); }

    public Vector<Edge> getEdges() { return edges; }

    public void add(Edge e)
    {
        edges.add(e);
        e.getDestination().incrInDegree();
    }

    public void incrInDegree() { inDegree++; }

    public int getOutDegree() { return edges.size(); }
    public int getInDegree() { return inDegree; }

    public String toString()
    {
        return String.format("(%d) <%s>", id, label != null ? label : "" );
    }
}