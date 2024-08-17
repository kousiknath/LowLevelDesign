package com.lld.elevator;


import com.lld.elevator.algorithm.ElevatorSelectionBruteForceStrategy;
import com.lld.elevator.algorithm.ElevatorSelectionStrategy;
import com.lld.elevator.constant.ElevatorFacing;
import com.lld.elevator.model.*;
import com.lld.elevator.repository.ElevatorCarCurrentLocationRepository;
import com.lld.elevator.repository.ElevatorRepository;
import com.lld.elevator.service.BuildingService;
import com.lld.elevator.service.ElevatorService;
import com.lld.elevator.service.impl.BuildingServiceImpl;
import com.lld.elevator.service.impl.ElevatorServiceImpl;

import java.util.List;
import java.util.Random;


public class Main {
    private static void test1() {
        BuildingService buildingService = new BuildingServiceImpl();
        Building building1 = buildingService.create("Building - 1");

        for (int i = 1; i <= 10; i++) {
            building1 = buildingService.addFloor(building1, ElevatorFacing.NORTH, String.valueOf(i));
        }

        List<Floor> floors = building1.getFloors();
        Floor randomFloor = floors.get(new Random().nextInt(floors.size()));

        ElevatorRepository elevatorRepository = new ElevatorRepository();
        ElevatorCarCurrentLocationRepository currentLocationRepository = new ElevatorCarCurrentLocationRepository();

        ElevatorService elevatorService = new ElevatorServiceImpl(elevatorRepository, currentLocationRepository);

        elevatorService.register(ElevatorFacing.NORTH, building1);

        ElevatorSelectionStrategy selectionStrategy = new ElevatorSelectionBruteForceStrategy(elevatorRepository, currentLocationRepository);

        ElevatorCar car = elevatorService.callElevator(randomFloor.getPanels().get(0),
                randomFloor.getPanels().get(0).getUp().press(), selectionStrategy);
        System.out.println("Car Assigned = " + car);

    }

    public static void main(String[] args) {
        test1();
    }
}
