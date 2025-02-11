package es.upv.etsinf.graphs;

import java.util.*;
import java.io.PrintStream;

public class Graph
{
    protected Vector<Vertex>  vertices;
    protected Vector<Edge>    edges;
    protected boolean         directed;

    protected int totalOutDegree;
    protected int totalInDegree;

    protected Edge  adjacency[][]; // Not always used. Must be generated ad-hoc for those algorithms that need it.

    public Graph(boolean directed)
    {
        vertices = new Vector<Vertex>();
           edges = new Vector<Edge>();

        totalOutDegree = 0;
        totalInDegree = 0;

        this.directed = directed;

        adjacency = null;
    }

    public int getNumVertices() { return vertices.size(); }
    public int getNumEdges()    { return edges.size(); }
    public int getDegree()      { return totalOutDegree; }
    public int getOutDegree()   { return totalOutDegree; }
    public int getInDegree()    { return totalInDegree; }

    public Vertex getVertex(int index) { return vertices.get(index); }

    public boolean isDirected() { return directed; }


    public void generateAdjacencyMatrix()
    {
        adjacency = new Edge[vertices.size()][vertices.size()];

        for (Vertex v : vertices) {
            for (Edge e : v.getEdges()) {

                adjacency[v.getId()][e.getDestination().getId()] = e;
                // adjacency[ e.getDestination().getId() ][ v.getId() ] = e;
            }
        }
        /*
         * It not necessary to add the edge from 'dest' to 'origin' because
         * if the graph was generated as non-directed the edge from 'dest'
         * to 'origin' must exist as an edge inside the destination vertex.
         * This implementation forces to allocate more memory but it give
         * us the chance of use it for different purposes.
         */
    }

    public Edge findEdge(Vertex from, Vertex to)
    {
        if (adjacency != null)
            return adjacency[from.getId()][to.getId()];

        for (Edge e : from.getEdges())
            if (e.getDestination() == to)
                return e;

        return null;
    }

    public void reset() // InitializeGraph
    {
        vertices.clear();
        edges.clear();

        totalOutDegree = 0;
        totalInDegree = 0;
    }

    public void insert(Vertex v)
    {
        v.setId(vertices.size());
        vertices.add(v);
    }

    public void insert(Edge e, boolean directed)
    {
        edges.add(e);

        if (!directed) {
            Edge other = new Edge(e.getDestination(), e.getOrigin());
            other.setCapacity(e.getCapacity());
            other.setFlow(e.getFlow());
            other.setResidual(e.getResidual());
            other.setWeight(e.getWeight());
            edges.add(other);
            e.getDestination().add(other);
        }
    }
    
    public void connect(Vertex o, Vertex d, boolean directed)
    {
        Edge e = new Edge(o, d);
        edges.add(e);
        o.add(e);

        if (!directed) {
            e = new Edge(d, o);
            edges.add(e);
            d.add(e);
        }
    }

    public void read( Scanner sf, boolean directed )
    {
    }

    public void print( PrintStream ps )
    {
    }
}