package com.lld.elevator.service;

import com.lld.elevator.algorithm.ElevatorSelectionStrategy;
import com.lld.elevator.constant.Direction;
import com.lld.elevator.constant.ElevatorFacing;
import com.lld.elevator.model.Building;
import com.lld.elevator.model.ElevatorCar;
import com.lld.elevator.model.Floor;
import com.lld.elevator.model.ElevatorPointOfRequestPanel;

public interface ElevatorService {
    ElevatorCar register(ElevatorFacing position, Building building);
    ElevatorCar callElevator(ElevatorPointOfRequestPanel elevatorPointOfRequestPanel, Direction direction,
                             ElevatorSelectionStrategy selectionStrategy);
    void sendElevatorCarToRequester(ElevatorCar car, Floor currentFloor, Floor requesterFloor);
    void moveToDestination(ElevatorCar car, Floor from, Floor to);
}
