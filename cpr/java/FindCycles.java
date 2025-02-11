package es.upv.etsinf.graphs;

import java.util.*;

public class FindCycles
        extends GraphOperations
{
    public FindCycles(Graph g)
    {
        super(g);
    }

    public void processVertex(Vertex v)
    {
    }

    public void processEdge(Edge e)
    {
        if (parent[e.getDestination().getId()] != e.getOrigin()) {

            System.err.println("Cycle from " + e.getDestination() + " to " + e.getOrigin());
            LinkedList<Vertex> path = findPath(e.getDestination().getId(), e.getOrigin().getId());
            for (Vertex v : path)
                System.out.println("     --> " + v);
            finished = true;
        }
    }

    public void run() {
        initializeSearch();

        dfs(g.getVertex(0));
    }
}