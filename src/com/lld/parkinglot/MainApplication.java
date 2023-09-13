package com.lld.parkinglot;

import com.lld.parkinglot.exception.ServiceException;
import com.lld.parkinglot.src.model.*;
import com.lld.parkinglot.src.model.vehicle.LargeFamilyCar;
import com.lld.parkinglot.src.model.vehicle.SUV;
import com.lld.parkinglot.src.model.vehicle.Truck;
import com.lld.parkinglot.src.model.vehicle.Vehicle;
import com.lld.parkinglot.src.service.BuildingService;
import com.lld.parkinglot.src.service.ParkingLotService;
import com.lld.parkinglot.src.service.impl.BuildingServiceImpl;
import com.lld.parkinglot.src.service.impl.ParkingLotServiceImpl;

import java.util.Arrays;

public class MainApplication {

    private static void test1() throws ServiceException {
        BuildingService buildingService = new BuildingServiceImpl();
        Building building1 = buildingService.create("building-1", 5);
        Building building2 = buildingService.create("building-2", 4);

        ParkingLotService parkingLotService = new ParkingLotServiceImpl();
        ParkingLot parkingLot = parkingLotService.create(Arrays.asList(building1, building2));

        User owner = new User("test", "test@test.com");
        Vehicle myVehicle1 = new LargeFamilyCar(owner, "KA893J5366");
        Vehicle myVehicle2 = new Truck(owner, "KA893J5361");

        // concurrent parking scenario
        System.out.println("Parking the first vehicle...");
        park(parkingLotService, parkingLot, myVehicle1); // slots should start from 0 for the first vehicle

        System.out.println("Parking the second vehicle...");
        park(parkingLotService, parkingLot, myVehicle2); // slots should start from 4 for the second vehicle
    }

    private static void park(ParkingLotService parkingLotService, ParkingLot parkingLot, Vehicle myVehicle) throws ServiceException {
        Building assignedBuilding = parkingLotService.assignBuilding(parkingLot, myVehicle);

        Ticket ticket = parkingLotService.checkIn(assignedBuilding, assignedBuilding.getEntries().get(0), myVehicle);
        System.out.println("Parking check-in details: " + ticket);
    }

    private static void test2() throws ServiceException {
        BuildingService buildingService = new BuildingServiceImpl();
        Building building1 = buildingService.create("building-1", 5);
        Building building2 = buildingService.create("building-2", 4);

        ParkingLotService parkingLotService = new ParkingLotServiceImpl();
        ParkingLot parkingLot = parkingLotService.create(Arrays.asList(building1, building2));

        User owner = new User("test", "test@test.com");
        Vehicle myVehicle1 = new LargeFamilyCar(owner, "KA893J5366");
        Vehicle myVehicle2 = new Truck(owner, "KA893J5361");

        // non-concurrent parking scenario i.e; one vehicle leaves, another parks
        System.out.println("Parking the first vehicle...");
        parkAndCheckout(parkingLotService, parkingLot, myVehicle1); // slots should start from 0 for the first vehicle

        System.out.println("Parking the second vehicle...");
        parkAndCheckout(parkingLotService, parkingLot, myVehicle2); // slots should start from 0 for the second vehicle as well
        // as the first one was checked out
    }

    private static void parkAndCheckout(ParkingLotService parkingLotService, ParkingLot parkingLot, Vehicle myVehicle) throws ServiceException {
        Building assignedBuilding = parkingLotService.assignBuilding(parkingLot, myVehicle);

        Ticket ticket = parkingLotService.checkIn(assignedBuilding, assignedBuilding.getEntries().get(0), myVehicle);
        System.out.println("Parking check-in details: " + ticket);

        Payment payment = parkingLotService.pay(ticket);
        ticket = parkingLotService.checkOut(assignedBuilding, assignedBuilding.getExits().get(0), ticket, payment);
        System.out.println("Parking check-out details: " + ticket);
    }

    public static void main(String[] args) throws ServiceException {
        test1();
        test2();
    }
}
