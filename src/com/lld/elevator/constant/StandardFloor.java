package com.lld.elevator.constant;

public enum StandardFloor {
    GROUND_FLOOR("G"),
    ROOFTOP("ROOFTOP"),
    BASEMENT_1("BASEMENT_1"),
    BASEMENT_2("BASEMENT_2"),
    BASEMENT_3("BASEMENT_3");

    private String floor;
    StandardFloor(String floor) {
        this.floor = floor;
    }

    public String getFloor() {
        return floor;
    }
}
