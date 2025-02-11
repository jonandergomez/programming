
package es.upv.etsinf.graphs;

public class Edge
{
    protected Vertex      origin;
    protected Vertex      destination;

    protected String      label;

    protected double      weight;

    protected double      capacity;
    protected double      flow;
    protected double      residual;

    public Edge(Vertex origin, Vertex destination)
    {
        this.origin = origin;
        this.destination = destination;
        this.label = null;
        reset();
    }
    public Edge(Vertex origin, Vertex destination, String label)
    {
        this.origin = origin;
        this.destination = destination;
        this.label = label;
        reset();
    }

    public void reset()
    {
        weight   = 1.0;
        capacity = 1.0;
        flow     = 0.0;
        residual = 0.0;
    }

    public Vertex getOrigin() { return origin; }
    public Vertex getDestination() { return destination; }
    public String getLabel() { return label; }

    public double getWeight() { return weight; }

    public void setWeight(double w) { weight=w; }

    public double getCapacity() { return capacity; }
    public double getFlow()     { return flow; }
    public double getResidual() { return residual; }


    public void setCapacity(double c) { capacity = c; }
    public void setFlow(double f) { flow = f; }
    public void setResidual(double r) { residual = r; }


    public String toString()
    {
        return origin.toString() + " --> " + destination.toString();
    }
}
