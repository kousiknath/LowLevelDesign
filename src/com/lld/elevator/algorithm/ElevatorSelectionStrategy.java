package com.lld.elevator.algorithm;

import com.lld.elevator.constant.Direction;
import com.lld.elevator.model.ElevatorCar;
import com.lld.elevator.model.ElevatorPointOfRequestPanel;

public interface ElevatorSelectionStrategy {

    ElevatorCar select(ElevatorPointOfRequestPanel requesterElevatorPointOfRequestPanel, Direction direction);
}
