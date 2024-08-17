package com.lld.elevator.repository;

import com.lld.elevator.constant.ElevatorFacing;
import com.lld.elevator.model.Building;
import com.lld.elevator.model.ElevatorCar;

import java.util.*;

public class ElevatorRepository {
    Map<Building, Map<ElevatorFacing, List<ElevatorCar>>> elevatorCars;

    public ElevatorRepository() {
        this.elevatorCars = new HashMap<>();
    }

    public ElevatorCar add(Building building, ElevatorFacing position, ElevatorCar car) {
        this.elevatorCars.putIfAbsent(building, new HashMap<>());
        this.elevatorCars.get(building).putIfAbsent(position, new ArrayList<>());
        this.elevatorCars.get(building).get(position).add(car);
        return car;
    }

    public List<ElevatorCar> getCarsFor(Building building, ElevatorFacing elevatorFacing) {
        return Collections.unmodifiableList(this.elevatorCars.getOrDefault(building, new HashMap<>())
                .getOrDefault(elevatorFacing, new ArrayList<>()));
    }
}
