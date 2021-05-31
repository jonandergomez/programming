package etsinf.prg.agenda3;

import java.util.*;

public class NodeEvent
{
    private Event     event;
    private NodeEvent next;
    private NodeEvent previous;

    public NodeEvent(NodeEvent previous, Event event, NodeEvent next)
    {
        this.previous = previous;
        this.event = event;
        this.next = next;
    }
    public NodeEvent(Event event, NodeEvent next)
    {
        this(null, event, next);
    }
    public NodeEvent(Event event)
    {
        this(null, event, null);
    }
    public Event getEvent() { return event; }
    public void setEvent(Event e) { event = e; }

    public NodeEvent getNext() { return next; }
    public void setNext(NodeEvent next) { this.next = next; }

    public NodeEvent getPrevious() { return previous; }
    public void setPrevious(NodeEvent previous) { this.previous = previous; }

    @Override
    public String toString()
    {
        return "<" + event.toString() + ">";
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof NodeEvent) {

            NodeEvent other = (NodeEvent)o;

            return other.event.equals(this.event);
        }
        return false;
    }
}
