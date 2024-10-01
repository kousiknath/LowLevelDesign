package com.lld.elevator.algorithm;

import com.lld.elevator.constant.Direction;
import com.lld.elevator.model.ElevatorCar;
import com.lld.elevator.model.ElevatorPointOfRequestPanel;

public class WeightBasedElevatorSelectionStrategy implements ElevatorSelectionStrategy {
    @Override
    public ElevatorCar select(ElevatorPointOfRequestPanel requesterElevatorPointOfRequestPanel, Direction direction) {
        // Assign an elevator based on the current weight on the car.
        // If there is already one elevator that is going in the
        //  user's desired direction, but it's already near the
        //  maximum capacity, assign another elevator not in full
        //  capacity, nearby and can be assigned in the same direction.
        return null;
    }
}
