package com.lld.inmemorycache.model;

public class DoublyLinkedList {
    private Node head;
    private Node tail;
    private int count;
    public DoublyLinkedList()
    {
        head = null;
        tail = null;
        count = 0;
    }

    public Node last()
    {
        return tail;
    }

    public Node addFront(String data)
    {
        Node temp = new Node(data, head, null);
        if(head != null)
        {
            head.prev = temp;
        }
        // We have a new head.
        head = temp;

        if(tail == null)
        {
            tail = temp;
        }
        count++;
        return head;
    }

    public void delete(Node item)
    {
        if(item == null)
            return;
        if(head == null)
            return;
        // deleting the top.
        if(item == head)
        {
            // update the head.
            head = head.next;
            if(head != null)
                head.prev = null;
            else {
                // if head is null, then tail is null as well.
                tail = null;
            }
        }
        else if(item == tail)
        {
            // go back.
            tail = tail.prev;
            tail.next = null;
        }
        else {
            // some mid node we need to delete.
            Node next = item.next;
            Node prev = item.prev;
            prev.next = next;
            next.prev = prev;
        }
        count--;
        item.next = null;
        item.prev = null;
    }

    public int count()
    {
        return count;
    }
}
