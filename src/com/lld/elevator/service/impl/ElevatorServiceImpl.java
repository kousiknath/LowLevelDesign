package com.lld.elevator.service.impl;

import com.lld.elevator.algorithm.ElevatorSelectionStrategy;
import com.lld.elevator.constant.Direction;
import com.lld.elevator.constant.ElevatorFacing;
import com.lld.elevator.model.Building;
import com.lld.elevator.model.ElevatorCar;
import com.lld.elevator.model.Floor;
import com.lld.elevator.model.ElevatorPointOfRequestPanel;
import com.lld.elevator.repository.ElevatorCarCurrentLocationRepository;
import com.lld.elevator.repository.ElevatorRepository;
import com.lld.elevator.service.ElevatorService;


public class ElevatorServiceImpl implements ElevatorService {
    private ElevatorRepository elevatorRepository;
    private ElevatorCarCurrentLocationRepository currentLocationRepository;

    public ElevatorServiceImpl(ElevatorRepository elevatorRepository, ElevatorCarCurrentLocationRepository currentLocationRepository) {
        this.elevatorRepository = elevatorRepository;
        this.currentLocationRepository = currentLocationRepository;
    }

    @Override
    public ElevatorCar register(ElevatorFacing position, Building building) {
        ElevatorCar car = new ElevatorCar(building, position);
        this.elevatorRepository.add(building, position, car);
        // Add the first floor just to seed the elevator location to the system
        this.currentLocationRepository.addCurrentLocation(car, building.getFloors().get(0));
        return car;
    }

    @Override
    public ElevatorCar callElevator(ElevatorPointOfRequestPanel elevatorPointOfRequestPanel, Direction direction, ElevatorSelectionStrategy selectionStrategy) {
        return selectionStrategy.select(elevatorPointOfRequestPanel, direction);
    }

    @Override
    public void sendElevatorCarToRequester(ElevatorCar car, Floor currentFloor, Floor requesterFloor) {
        // Add some logic to simulate that the elevator is moving.
        this.currentLocationRepository.addCurrentLocation(car, requesterFloor);
    }

    @Override
    public void moveToDestination(ElevatorCar car, Floor from, Floor to) {

    }
}
