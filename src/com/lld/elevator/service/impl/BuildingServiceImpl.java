package com.lld.elevator.service.impl;

import com.lld.elevator.constant.ElevatorFacing;
import com.lld.elevator.model.*;
import com.lld.elevator.service.BuildingService;

import java.util.List;

public class BuildingServiceImpl implements BuildingService {
    @Override
    public Building create(String no) {
        if (no == null) {
            throw new IllegalArgumentException("Invalid building number!");
        }

        Building building = new Building(no);
        return building;
    }

    @Override
    public Building addFloor(Building building, ElevatorFacing elevatorFacing, String floorLevel) {
        if (building == null) {
            throw new IllegalArgumentException("Invalid building!");
        }

        if (floorLevel == null) {
            throw new IllegalArgumentException("Invalid floor level!");
        }

        ElevatorPointOfRequestPanel panel = new ElevatorPointOfRequestPanel(elevatorFacing, new Display(), new ButtonUp(), new ButtonDown());
        Floor floor = new Floor(floorLevel, List.of(panel), building);
        panel.setFloor(floor);
        building.addFloor(floor);
        return building;
    }
}
