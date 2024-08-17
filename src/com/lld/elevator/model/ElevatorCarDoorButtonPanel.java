package com.lld.elevator.model;

public class ElevatorCarDoorButtonPanel {
    private ButtonDoorOpen open;
    private ButtonDoorClose close;

    public ElevatorCarDoorButtonPanel() {
        this.open = new ButtonDoorOpen();
        this.close = new ButtonDoorClose();
    }
}
