package linear;

public class LinkedSequences
{
    public static NodeInt insert(int value, NodeInt first, NodeInt previous)
    {
        if (first == null) {
            first = new NodeInt(value);
        } else if (previous != null) {
            previous.setNext(new NodeInt(value, previous.getNext()));    
        } else {
            return new NodeInt(value, first);
        }
        return first;
    }
    public static NodeInt append(int value, NodeInt first)
    {
        if (first == null)
            return new NodeInt(value);
        else {
            NodeInt previous = first;
            NodeInt current = previous.getNext();
            while (current != null) {
                previous = current;
                current = current.getNext();
            }
            previous.setNext(new NodeInt(value));
            return first;
        }
    }

    public static NodeInt remove(NodeInt first, NodeInt previous)
    {
        if (first == null) throw new Error("Patan, eres un patan");

        if (previous != null) {
            if (previous.getNext() != null)
                previous.setNext(previous.getNext().getNext());
        } else {
            first = first.getNext();
        }

        return first;
    }

    public static NodeInt removeLastOne(NodeInt first)
    {
        if (first == null) throw new Error("Patan, eres un patan");

        NodeInt previous = null;
        NodeInt current = first;
        while (current != null && current.getNext() != null) {
            previous = current;
            current = current.getNext();
        }
        if (previous != null) previous.setNext(null);
        else first = null;

        return first;
    }

    public static void main(String [] args)
    {
        NodeInt first = null;

        first = LinkedSequences.insert(5, first, null);
        first = LinkedSequences.append(5, first);
        first = LinkedSequences.insert(5, null, first);
    }
}
