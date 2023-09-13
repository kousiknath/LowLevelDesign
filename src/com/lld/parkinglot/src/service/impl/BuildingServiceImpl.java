package com.lld.parkinglot.src.service.impl;

import com.lld.parkinglot.exception.ServiceException;
import com.lld.parkinglot.src.config.DefaultSlotMapping;
import com.lld.parkinglot.src.config.DefaultVehicleToSlotMapping;
import com.lld.parkinglot.src.constant.SlotType;
import com.lld.parkinglot.src.model.Building;
import com.lld.parkinglot.src.model.Entry;
import com.lld.parkinglot.src.model.Exit;
import com.lld.parkinglot.src.model.Level;
import com.lld.parkinglot.src.model.slot.Slot;
import com.lld.parkinglot.src.model.vehicle.Vehicle;
import com.lld.parkinglot.src.service.BuildingService;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class BuildingServiceImpl implements BuildingService {
    private DefaultSlotMapping defaultSlotMapping;
    private DefaultVehicleToSlotMapping vehicleTypeToSlotTypeMapping;

    public BuildingServiceImpl() {
        this.defaultSlotMapping = new DefaultSlotMapping();
        this.vehicleTypeToSlotTypeMapping = new DefaultVehicleToSlotMapping();
    }

    @Override
    public Building create(String name, int totalLevels) throws ServiceException {
        if (name == null) {
            throw new ServiceException("Building name can not be null");
        }

        if (totalLevels <= 0) {
            throw new ServiceException("Total number of levels should be more than zero");
        }

        // Generate random entry and exits
        Entry entry1 = new Entry("entry-1");
        Exit exit1 = new Exit("exit-1");

        Building building = new Building(name, totalLevels, entry1, exit1);
        for (int l = 1; l <= totalLevels; l++) {
            // We create levels with minimum 100 XSmallSlots and maximum 200 XSmallSlots.
            building.addLevel(new Level(l, 100 + new Random().nextInt(100)));
        }

        return building;
    }

    @Override
    public List<Slot> allocateSlots(Building building, Vehicle vehicle) throws ServiceException {
        int requiredXSmallSlots = findRequiredXSmallSlotCountFor(vehicle);
        if (requiredXSmallSlots < 0) {
            return Collections.emptyList();
        }

        for (Level level: building.getLevels()) {
            List<Slot> allocatedSlots = level.allocateConsecutiveSlots(requiredXSmallSlots);
            if (allocatedSlots != null) {
                return allocatedSlots;
            }
        }

        return Collections.emptyList();
    }

    @Override
    public boolean canPark(Building building, Vehicle vehicle) {
        int requiredXSmallSlots = findRequiredXSmallSlotCountFor(vehicle);
        if (requiredXSmallSlots < 0) {
            return false;
        }

        for (Level level: building.getLevels()) {
            if (level.findConsecutiveAvailableSlots(requiredXSmallSlots) != -1) {
                return true;
            }
        }

        return false;
    }

    private int findRequiredXSmallSlotCountFor(Vehicle vehicle) {
        SlotType slotType = this.vehicleTypeToSlotTypeMapping.getSlotTypeFor(vehicle.getVehicleType());
        List<DefaultSlotMapping.SlotInfo> slotInfoList = this.defaultSlotMapping.getSlotMappingFor(slotType)
                .stream().filter(s -> s.getMappedSlotType() == SlotType.X_SMALL).toList();
        if (slotInfoList.isEmpty()) {
            return -1;
        }

        DefaultSlotMapping.SlotInfo slotInfo = slotInfoList.get(0);
        return slotInfo.getCount();
    }
}
