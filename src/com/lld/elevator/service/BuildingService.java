package com.lld.elevator.service;

import com.lld.elevator.constant.ElevatorFacing;
import com.lld.elevator.model.Building;

public interface BuildingService {
    Building create(String no);
    Building addFloor(Building building, ElevatorFacing elevatorFacing, String floorLevel);
}
