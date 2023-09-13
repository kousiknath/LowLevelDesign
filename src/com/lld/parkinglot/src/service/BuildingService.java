package com.lld.parkinglot.src.service;


import com.lld.parkinglot.src.exception.ServiceException;
import com.lld.parkinglot.src.model.Building;
import com.lld.parkinglot.src.model.slot.Slot;
import com.lld.parkinglot.src.model.vehicle.Vehicle;

import java.util.List;


public interface BuildingService {
    Building create(String name, int totalLevels) throws ServiceException;
    List<Slot> allocateSlots(Building building, Vehicle vehicle) throws ServiceException;
    boolean canPark(Building building, Vehicle vehicle);
}
