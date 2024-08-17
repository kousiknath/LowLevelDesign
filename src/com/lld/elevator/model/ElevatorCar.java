package com.lld.elevator.model;

import com.lld.elevator.constant.ElevatorCarState;
import com.lld.elevator.constant.ElevatorFacing;

public class ElevatorCar {
    private Building building;
    private ElevatorFacing elevatorFacing;
    private ElevatorCarPanel panel;
    private ElevatorCarState carState;
    private WeightSensor weightSensor;

    public ElevatorCar(Building building, ElevatorFacing elevatorFacing) {
        this.building = building;
        this.elevatorFacing = elevatorFacing;
        this.panel = new ElevatorCarPanel();
        this.weightSensor = new WeightSensor();
        this.carState = ElevatorCarState.IN_SERVICE; // By default the car is active
    }

    public ElevatorCarState getCarState() {
        return carState;
    }

    public void setCarState(ElevatorCarState carState) {
        this.carState = carState;
    }
}
