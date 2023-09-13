package com.lld.parkinglot.src.service.impl;

import com.lld.parkinglot.src.exception.ServiceException;
import com.lld.parkinglot.src.constant.TicketStatus;
import com.lld.parkinglot.src.model.*;
import com.lld.parkinglot.src.model.slot.Slot;
import com.lld.parkinglot.src.model.vehicle.Vehicle;
import com.lld.parkinglot.src.service.BuildingService;
import com.lld.parkinglot.src.service.ParkingFeeCalculatorService;
import com.lld.parkinglot.src.service.ParkingLotService;

import java.util.List;

public class ParkingLotServiceImpl implements ParkingLotService {
    private BuildingService buildingService;
    private ParkingFeeCalculatorService feeCalculatorService;

    public ParkingLotServiceImpl() {
        this.buildingService = new BuildingServiceImpl();
        this.feeCalculatorService = new ParkingFeeCalculatorServiceImpl();
    }

    @Override
    public ParkingLot create(List<Building> buildings) throws ServiceException {
        if (buildings == null || buildings.isEmpty()) {
            throw new ServiceException("At least one building is required to create a parking lot");
        }

        ParkingLot parkingLot = new ParkingLot(buildings.size());
        for (Building building: buildings) {
            parkingLot.addBuilding(building);
        }

        return parkingLot;
    }

    @Override
    public Building assignBuilding(ParkingLot parkingLot, Vehicle vehicle) throws ServiceException {
        if (parkingLot == null || vehicle == null) {
            throw new ServiceException("Invalid parameters");
        }

        for (Building building: parkingLot.getBuildings()) {
            if (buildingService.canPark(building, vehicle)) {
                return building;
            }
        }

        return null;
    }

    @Override
    public Ticket checkIn(Building building, Entry entry, Vehicle vehicle) throws ServiceException {
        if (building == null || entry == null || vehicle == null) {
            throw new ServiceException("Invalid parameters");
        }

        List<Slot> allocatedSlots = this.buildingService.allocateSlots(building, vehicle);
        if (allocatedSlots.isEmpty()) {
            throw new ServiceException("Could not park the vehicle");
        }

        Ticket ticket = new Ticket();
        ticket.setBuilding(building);
        ticket.setTicketStatus(TicketStatus.ISSUED);
        ticket.setCheckInTimeMillis(System.currentTimeMillis());
        ticket.setUser(vehicle.getOwner());
        ticket.setSlots(allocatedSlots);
        ticket.setVehicle(vehicle);
        ticket.setEntry(entry);

        return ticket;
    }

    @Override
    public Payment pay(Ticket ticket) {
        // Generate the fee using feeCalculatorService
        Fee fee = this.feeCalculatorService.calculate(ticket);

        // Call transaction service to pay the fee

        // Call payment service to facilitate the payment. For now, return a dummy payment object
        Payment payment = new Payment();
        payment.setUser(ticket.getUser());
        payment.setFee(fee);

        return payment;
    }

    @Override
    public Ticket checkOut(Building building, Exit exit, Ticket ticket, Payment payment) throws ServiceException {
        List<Slot> allocatedSlots = ticket.getSlots();
        if (allocatedSlots == null) {
            throw new ServiceException("Invalid slots in ticket");
        }

        Level level = allocatedSlots.get(0).getLevel();
        level.deallocateConsecutiveSlots(allocatedSlots.get(0).getIndex(), allocatedSlots.size());

        ticket.setPayment(payment);
        ticket.setTicketStatus(TicketStatus.PAID);
        ticket.setExit(exit);
        ticket.setCheckOutTimeMillis(System.currentTimeMillis());

        return ticket;
    }
}
