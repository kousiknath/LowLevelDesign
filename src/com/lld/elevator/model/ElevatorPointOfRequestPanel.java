package com.lld.elevator.model;

import com.lld.elevator.constant.ElevatorFacing;

/*
    There can be multiple point of requests for an elevator on a
    single floor in a given `ElevatorFacing` direction.

    A user can request for an elevator car by pressing up or down
    key on a `ElevatorPointOfRequestPanel`.
 */
public class ElevatorPointOfRequestPanel {
    private Floor floor;
    private ElevatorFacing elevatorFacing;
    private Display display;
    private ButtonUp up;
    private ButtonDown down;

    public ElevatorPointOfRequestPanel(ElevatorFacing elevatorFacing, Display display, ButtonUp up, ButtonDown down) {
        this.elevatorFacing = elevatorFacing;
        this.display = display;
        this.up = up;
        this.down = down;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public Floor getFloor() {
        return floor;
    }

    public ElevatorFacing getPanelPosition() {
        return elevatorFacing;
    }

    public Display getDisplay() {
        return display;
    }

    public ButtonUp getUp() {
        return up;
    }

    public ButtonDown getDown() {
        return down;
    }
}
