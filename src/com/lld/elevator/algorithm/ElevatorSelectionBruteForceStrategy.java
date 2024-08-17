package com.lld.elevator.algorithm;

import com.lld.elevator.constant.Direction;
import com.lld.elevator.model.ElevatorCar;
import com.lld.elevator.model.Floor;
import com.lld.elevator.model.ElevatorPointOfRequestPanel;
import com.lld.elevator.repository.ElevatorCarCurrentLocationRepository;
import com.lld.elevator.repository.ElevatorRepository;

import java.util.List;

public class ElevatorSelectionBruteForceStrategy implements ElevatorSelectionStrategy {
    private ElevatorRepository elevatorRepository;
    private ElevatorCarCurrentLocationRepository currentLocationRepository;

    public ElevatorSelectionBruteForceStrategy(ElevatorRepository elevatorRepository,
                                               ElevatorCarCurrentLocationRepository currentLocationRepository) {
        this.elevatorRepository = elevatorRepository;
        this.currentLocationRepository = currentLocationRepository;
    }

    @Override
    public ElevatorCar select(ElevatorPointOfRequestPanel requesterElevatorPointOfRequestPanel, Direction direction) {
        Floor requesterFloor = requesterElevatorPointOfRequestPanel.getFloor();
        List<ElevatorCar> cars = this.elevatorRepository.getCarsFor(requesterFloor.getBuilding(),
                requesterElevatorPointOfRequestPanel.getPanelPosition());

        for (ElevatorCar car: cars) {
            // For an elevator check, if the current location is "G",
            // If the direction is up, it can move up.
            // If "R" and the direction is "Down", it can move down
            // Else, depending on requesting on requested from floor and direction,
            // assign the elevator
            Floor elevatorCurrentLocation = currentLocationRepository.fetchCurrentLocation(car);
            // ........
            // Based on the algorithm, decide a car
            return car;
        }

        return null;
    }
}
