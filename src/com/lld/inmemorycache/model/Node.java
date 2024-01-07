package com.lld.inmemorycache.model;

public class Node {
    public String data;
    public Node next;
    public Node prev;

    public Node(String data, Node next, Node prev)
    {
        this.data = data;
        this.next = next;
        this.prev = prev;
    }
}
