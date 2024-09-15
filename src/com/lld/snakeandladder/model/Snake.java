package com.lld.snakeandladder.model;

public class Snake extends PositionChanger {
    /*
        Snake downgrades the player's position from the given source
        to the given destination
     */

    public Snake(Coordinate source, Coordinate destination) {
        super(source, destination);
        int cmp = source.compareTo(destination);
        if (cmp <= 0) {
            throw new IllegalArgumentException("Invalid Snake. Destination must be lesser than source");
        }
    }

    @Override
    public Coordinate getDestination() {
        return this.destination;
    }
}
