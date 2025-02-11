package es.upv.etsinf.graphs;

import java.util.*;

public class GraphOperations
{
    protected boolean []    processed;
    protected boolean []    discovered;
    protected Vertex  []    parent;
    protected boolean       finished;

    protected Graph         g;

    public GraphOperations(Graph g)
    {
        this.g = g;
         processed = new boolean [g.getNumVertices()];
        discovered = new boolean [g.getNumVertices()];
            parent = new Vertex  [g.getNumVertices()];
    }

    public void initializeSearch()
    {
        for (int i = 0; i < processed.length; i++) {
            processed[i] = discovered[i] = false;
            parent[i] = null;
        }
        finished = false;
    }

    // The 'findPath()' method is the tool to be used after other
    // algorithms, as the ones for constructing the minimum spanning tree.
    public LinkedList<Vertex> findPath(int start, int end)
    {
        LinkedList<Vertex> path = new LinkedList<Vertex>();

        findPath(path, start, end);

        return path;
    }
    private void findPath(LinkedList<Vertex> path, int start, int end)
    {
        if (start == -1) return;

        if (start == end || end == -1)
            path.add(g.getVertex(start));
        else {
            findPath(path, start, parent[end].getId());
            path.add(g.getVertex(end));
        }
    }

    public boolean validEdge(Edge e)
    {
        return e != null;
    }

    // Basic schema for Breath First Search
    public void bfs(Vertex start)
    {
        // It is convinient to call initializeSearch() before calling this method.
        Queue<Vertex>  q = new LinkedList<Vertex>();

        q.add(start);
        discovered[start.getId()] = true;

        while (q.size() > 0) {

            Vertex currentVertex = q.remove();
            processVertex(currentVertex);
            processed[currentVertex.getId()] = true;

            for (Edge e : currentVertex.getEdges()) {

                if (! validEdge(e)) continue;

                Vertex nextVertex = e.getDestination();

                if (! discovered[nextVertex.getId()]) {
                    q.add(nextVertex);
                    discovered[nextVertex.getId()] = true;
                    parent[nextVertex.getId()] = currentVertex;
                }

                if (! processed[nextVertex.getId()]) processEdge(e);
            }
        }
    }

    // Basic schema for Deep First Search
    protected void dfs(Vertex currentVertex)
    {
        // It is convinient to call initializeSearch() before calling this method.

        if (finished) return;

        discovered[currentVertex.getId()] = true;
        processVertex(currentVertex);

        for (Edge e : currentVertex.getEdges()) {

            if (! validEdge(e)) continue;

            Vertex nextVertex = e.getDestination();

            if (! discovered[nextVertex.getId()]) {

                parent[nextVertex.getId()] = currentVertex;
                dfs(nextVertex);

            } else if (! processed[nextVertex.getId()]) {

                processEdge(e);
            }
            if (finished) return;
        }

        processed[currentVertex.getId()] = true;
    }


    // Rewrite these methods depending on the problem to solve.
    public void processVertex(Vertex v)
    {
        System.out.println("processed vertex: " + v);
    }
    public void processEdge(Edge e)
    {
        System.out.println("processed edge: " + e);
    }


    public LinkedList<Vertex> topsort()
    {
        int inDegree[] = new int [g.getNumVertices()];

        Queue<Vertex> q = new LinkedList<Vertex>();

        // Initially the 'inDegree' array contains the input degree of each node.
        // And the queue 'q' contains the nodes of the graph with no input edges.
        for (int i = 0; i < inDegree.length; i++) {
            inDegree[i] = g.getVertex(i).getInDegree();
            if (0 == inDegree[i]) q.add(g.getVertex(i));
        }

        // The queue 'q' will be empty if no nodes have zero input degree.

        LinkedList<Vertex>  sorted = new LinkedList<Vertex>();

        while (q.size() > 0) {
            Vertex currentVertex = q.remove();
            sorted.add(currentVertex);

            // The input degree of each node connected with the current node
            // is decreased. Those nodes whose input degree get equal to zero
            // is added to the queue.
            for (Edge e : currentVertex.getEdges()) {
                Vertex nextVertex = e.getDestination();
                if (--inDegree[ nextVertex.getId()] == 0) q.add(nextVertex);
            }
        }

        if (sorted.size() != g.getNumVertices())
            System.err.printf("Not a DAG -- only %d vertices found\n", sorted.size());

        return sorted;
    }

    /**
     * Implementation of the Prim algorithm.
     * Leaves in the attribute 'parent' the result.
     * The path in the tree between each pair of vertices
     * can be obtained by means of the method 'findPath()'.
     */
    public void minimumSpanningTreePrim(Vertex root)
    {
        boolean inTree[] = new boolean [g.getNumVertices()];
        double distance[] = new double [g.getNumVertices()];

        // The array distance maintains the distance of each node to the tree
        initializeSearch();
        for( int i=0; i < distance.length; i++ ) distance[i] = Double.MAX_VALUE;

        distance[root.getId()] = 0.0;
        parent[root.getId()] = null;

        Vertex current = root;
        while (! inTree[current.getId()]) {
            // The current node is inserted into the tree
            inTree[current.getId()] = true;
            // For each edge of the current node
            for (Edge e : current.getEdges()) {
                // Check each node connected with the current one
                Vertex next = e.getDestination();
                // The weight of the edge is the distance of the candidate node to the current one
                double weight = e.getWeight();
                // If the candidate node is not in the tree and the new distance is lower than the
                // best one up to now, then the distance is updated, the parent relation of nodes
                // in the tree is also updated
                if (weight < distance[next.getId()]  &&  false == inTree[next.getId()]) {
                    distance[next.getId()] = weight;
                    parent[next.getId()] = current;
                }
            }
            // Looking for the nearest node to the tree among those that are not yet in the tree
            current = root;
            double minDistOfOutOfTreeVertices = Double.MAX_VALUE;
            for (int i = 0; i < distance.length; i++) {
                if (! inTree[i]  &&  distance[i] < minDistOfOutOfTreeVertices) {
                    minDistOfOutOfTreeVertices = distance[i];
                    current = g.getVertex(i);
                }
            }
        }
    }
    /**
     * Implementation of the Kruskal algorithm.
     * Leaves in the attribute 'parent' the result.
     * The path in the tree between each pair of vertices
     * can be obtained by means of the method 'findPath()'.
     *
     * @return A vector containing the subset of edges that form the minimum spanning tree.
     */
    public Vector<Edge> minimumSpanningTreeKruskal()
    {
        initializeSearch();
        // 'setId' contains the id of nodes. Nodes with the same id are in the same connected component.
        int [] setId = new int [g.getNumVertices()];

        for (int i = 0; i < setId.length; i++) setId[i] = i;

        Vector<Edge>  mst = new Vector<Edge>();
        while (mst.size() < g.getNumVertices() - 1) {
            // Search for the edge with lowest weight between vertices of different sets (or groups).
            Edge bestEdge = null;
            for (Edge e : g.edges) {
                // If the edge connects nodes in the same connected component then is ignored.
                if (setId[e.getOrigin().getId()] == setId[e.getDestination().getId()]) continue;
                if (bestEdge == null  ||  e.getWeight() < bestEdge.getWeight()) bestEdge = e;
            }

            if (bestEdge != null) {
                // Marking the vertices as belonging to the same set (or group)
                int setIdMin = Math.min(setId[bestEdge.getOrigin().getId()], setId[bestEdge.getDestination().getId()]);
                int setIdMax = Math.max(setId[bestEdge.getOrigin().getId()], setId[bestEdge.getDestination().getId()]);

                for (int i = 0; i < setId.length; i++)
                    setId[i] = (setId[i] == setIdMax) ? setIdMin : setId[i];

                mst.add(bestEdge);
            }
        }

        return mst;
    }

    /**
     * Implementation of the Dijkstra algorithm.
     * Afterwards the 'findPath()' method could be used for recovering the path from 'from' to 'to'.
     */
    public void shortestPaths(int from)
    {
        shortestPath(g.getVertex(from), null);
    }
    public void shortestPath(int from, int to)
    {
        shortestPath(g.getVertex(from), g.getVertex(to));
    }
    public void shortestPath(Vertex from, Vertex to)
    {
        boolean inTree[] = new boolean [g.getNumVertices()];
        double distance[] = new double [g.getNumVertices()];

        initializeSearch();
        for (int i = 0; i < distance.length; i++) distance[i] = Double.MAX_VALUE;

        distance[from.getId()] = 0.0;
        parent[from.getId()] = null;

        Vertex current = from;

        // If 'to' or destination is not null the algorithm ends when such node is reached.
        // Otherwise the all shortest path from 'from' or origin to the other nodes are computed.
        while (! inTree[current.getId()]  &&  current != to) {

            inTree[current.getId()] = true;

            for (Edge e : current.getEdges()) {

                Vertex next = e.getDestination();

                double weight = distance[current.getId()] + e.getWeight();

                // Distances are updated even if the node was reached previously.
                // Parent node of nodes are also updated.
                if (weight < distance[next.getId()]) {
                    distance[next.getId()] = weight;
                    parent[next.getId()] = current;
                }
            }

            // The current node for the next iteration is chosen among those
            // already reached but no in the tree. Thanks to this search the
            // Dijkstra algorihtm guarantees the shortest path between two nodes
            // or for from an origin node to the remaining nodes.
            current = from;
            double minDistOfOutOfTreeVertices = Double.MAX_VALUE;
            for (int i = 0; i < distance.length; i++) {
                if (! inTree[i]  &&  distance[i] < minDistOfOutOfTreeVertices) {
                    minDistOfOutOfTreeVertices = distance[i];
                    current = g.getVertex(i);
                }
            }
        }
    }

    /**
     * Implementation of the Floyd algorithm.
     */
    public double [][] allShortestPaths()
    {
        double weight[][] = new double [g.getNumVertices() ][g.getNumVertices()];

        for (int i = 0; i < weight.length; i++)
            for (int j = 0; j < weight[i].length; j++) weight[i][j] = Double.MAX_VALUE;

        for (int i = 0; i < g.getNumVertices(); i++) {
            for (Edge e : g.getVertex(i).getEdges()) {
                weight[i][e.getDestination().getId()] = e.getWeight();
                if (! g.isDirected()) {
                    weight[e.getDestination().getId()][i] = e.getWeight();
                }
            }
        }

        for (int k = 0; k < g.getNumVertices(); k++) {
            for (int i = 0; i < g.getNumVertices(); i++) {
                for (int j = 0; j < g.getNumVertices(); j++) {
                    double through_k = weight[i][k] + weight[k][j];
                    if (through_k < weight[i][j]) {
                        weight[i][j] = through_k;
                    }
                }
            }
        }

        return weight;
    }
}