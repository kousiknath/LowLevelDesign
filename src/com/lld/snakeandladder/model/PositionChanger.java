package com.lld.snakeandladder.model;

public abstract class PositionChanger {
    protected Coordinate source;
    protected Coordinate destination;

    public PositionChanger(Coordinate source) {
        this.source = source;
        this.destination = source;
    }

    public PositionChanger(Coordinate source, Coordinate destination) {
        this.source = source;
        this.destination = destination;
    }
    public abstract Coordinate getDestination();
}
