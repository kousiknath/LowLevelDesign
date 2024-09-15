package com.lld.snakeandladder.model;

public class Ladder extends PositionChanger {
    /*
        Ladder upgrades the player's position from the given source
        to the given destination
     */
    public Ladder(Coordinate source, Coordinate destination) {
        super(source, destination);
        int cmp = source.compareTo(destination);
        if (cmp >= 0) {
            throw new IllegalArgumentException("Invalid Ladder. Destination must be greater than source");
        }
    }

    @Override
    public Coordinate getDestination() {
        return this.destination;
    }
}
