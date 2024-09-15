package com.lld.snakeandladder.model;

public class GeneralPosition extends PositionChanger {
    /*
        It's a normal position where a source and destination both
        are same. It does not upgrade or downgrade the player's
        position
     */
    public GeneralPosition(Coordinate source) {
        super(source);
    }

    @Override
    public Coordinate getDestination() {
        return this.source;
    }
}
