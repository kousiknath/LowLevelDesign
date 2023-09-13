package com.lld.parkinglot.src.service;

import com.lld.parkinglot.src.exception.ServiceException;
import com.lld.parkinglot.src.model.*;
import com.lld.parkinglot.src.model.vehicle.Vehicle;

import java.util.List;

public interface ParkingLotService {
    ParkingLot create(List<Building> buildings) throws ServiceException;
    Building assignBuilding(ParkingLot parkingLot, Vehicle vehicle) throws ServiceException;
    Ticket checkIn(Building building, Entry entry, Vehicle vehicle) throws ServiceException;
    Payment pay(Ticket ticket);
    Ticket checkOut(Building building, Exit exit, Ticket ticket, Payment payment) throws ServiceException;
}
