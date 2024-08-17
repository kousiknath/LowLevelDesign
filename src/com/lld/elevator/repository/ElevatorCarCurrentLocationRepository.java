package com.lld.elevator.repository;

import com.lld.elevator.model.ElevatorCar;
import com.lld.elevator.model.Floor;

import java.util.HashMap;
import java.util.Map;

public class ElevatorCarCurrentLocationRepository {
    private Map<ElevatorCar, Floor> currentLocation;

    public ElevatorCarCurrentLocationRepository() {
        this.currentLocation = new HashMap<>();
    }

    public void addCurrentLocation(ElevatorCar car, Floor floor) {
        this.currentLocation.put(car, floor);
    }

    public Floor fetchCurrentLocation(ElevatorCar car) {
        return this.currentLocation.get(car);
    }
}
