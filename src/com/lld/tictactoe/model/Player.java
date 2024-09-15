package com.lld.tictactoe.model;

public abstract class Player {
    protected String name;
    protected char marker;

    public Player(String name, char marker) {
        this.name = name;
        this.marker = marker;
    }

    public abstract int[] move(int rows, int cols);

    public String getName() {
        return name;
    }

    public char getMarker() {
        return marker;
    }
}
