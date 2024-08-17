package com.lld.elevator.model;

public class ElevatorCarPanel {
    private Display display;
    private ElevatorCarKeypad keypad;
    private ElevatorCarDoorButtonPanel doorButtonPanel;
    private ElevatorCarMiscButtonPanel miscButtonPanel;

    public ElevatorCarPanel() {
        this.display = new Display();
        this.keypad = new ElevatorCarKeypad();
        this.doorButtonPanel = new ElevatorCarDoorButtonPanel();
        this.miscButtonPanel = new ElevatorCarMiscButtonPanel();
    }
}
