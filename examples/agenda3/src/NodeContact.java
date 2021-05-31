package etsinf.prg.agenda3;

import java.util.*;

public class NodeContact
{
    private Contact     contact;
    private NodeContact next;
    private NodeContact previous;

    public NodeContact(NodeContact previous, Contact contact, NodeContact next)
    {
        this.previous = previous;
        this.contact = contact;
        this.next = next;
    }
    public NodeContact(Contact contact, NodeContact next)
    {
        this(null, contact, next);
    }
    public NodeContact(Contact contact)
    {
        this(null, contact, null);
    }
    public Contact getContact() { return contact; }
    public void setContact(Contact c) { contact = c; }

    public NodeContact getNext() { return next; }
    public void setNext(NodeContact next) { this.next = next; }

    public NodeContact getPrevious() { return previous; }
    public void setPrevious(NodeContact previous) { this.previous = previous; }

    @Override
    public String toString()
    {
        return "<" + contact.toString() + ">";
    }

    @Override
    public boolean equals(Object o)
    {
        if (o instanceof NodeContact) {

            NodeContact other = (NodeContact)o;

            return other.contact.equals(this.contact);
        }
        return false;
    }
}
