package es.upv.etsinf.graphs;

import java.util.*;

public class NetworkFlow
        extends GraphOperations
{
    public NetworkFlow(Graph g)
    {
        super(g);
    }

    public void processVertex(Vertex v)
    {
    }

    public void processEdge(Edge e)
    {
    }

    // Net Flow
    public void run(Vertex source, Vertex sink)
    {
        addResidualEdges();

        initializeSearch();
        bfs(source);

        if (g.adjacency == null)
            g.generateAdjacencyMatrix();

        double volume = pathVolume(source, sink);

        while (volume > 0.0) {
            augmentPath(source, sink, volume);
            initializeSearch();
            bfs(source);
            volume = pathVolume(source, sink);
        }

        // For the problem of robots, a bipartite matching problem,
        // the result should be the sum of the flow of each edge from source
    }

    public void addResidualEdges()
    {
    }

    public boolean validEdge(Edge e)
    {
        return e != null && e.getResidual() > 0.0;
    }

    public double pathVolume(Vertex start, Vertex end)
    {
        if (parent[end.getId()] == null)
            return 0.0;

        Edge e = g.findEdge(parent[end.getId()], end);

        if (start == parent[end.getId()])
            return e.getResidual();
        else
            return Math.min(pathVolume(start, parent[end.getId()]), e.getResidual());
    }

    public void augmentPath(Vertex start, Vertex end, double volume)
    {
        if (start != end) {

            Edge e = g.findEdge(parent[end.getId()], end);

            e.setFlow(e.getFlow() + volume);
            e.setResidual(e.getResidual() - volume);

            e = g.findEdge(end, parent[end.getId()]);
            e.setResidual(e.getResidual() + volume);

            augmentPath(start, parent[end.getId()], volume);
        }
    }
}