package es.upv.etsinf.graphs;

public class ConnectedComponents
        extends GraphOperations
{
    public ConnectedComponents(Graph g)
    {
        super(g);
    }

    public void processVertex(Vertex v)
    {
        System.out.printf(" %d", v.getId());
    }

    public void processEdge(Edge e)
    {
    }

    public void showConnectedComponents()
    {
        initializeSearch();

        int c = 0;

        for (int i = 0; i < g.getNumVertices(); i++) {

            Vertex v = g.getVertex(i);

            if (!discovered[v.getId()]) {
                c = c + 1;
                System.out.printf("Component %d:", c);
                dfs(v);
                System.out.println();
            }
        }
    }
}
