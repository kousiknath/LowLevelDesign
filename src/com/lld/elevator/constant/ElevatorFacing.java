package com.lld.elevator.constant;

/*
    A building can have many elevators facing any direction.
    Depending on where it's facing and where from the user
    requested, we will assign an elevator car to the requester.
 */
public enum ElevatorFacing {
    SOUTH("SOUTH"),
    NORTH("NORTH"),
    EAST("EAST"),
    WEST("WEST"),
    CENTER("CENTER");

    private String pos;
    ElevatorFacing(String pos) {
        this.pos = pos;
    }

    public String getPos() {
        return pos;
    }
}
