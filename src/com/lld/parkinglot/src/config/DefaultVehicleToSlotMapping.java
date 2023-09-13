package com.lld.parkinglot.src.config;

import com.lld.parkinglot.src.constant.SlotType;
import com.lld.parkinglot.src.constant.VehicleType;

import java.util.HashMap;
import java.util.Map;

/*
    Vehicle type to slot type mapping could vary from one parking lot to another.
    But, for simplicity, we are working with a default mapping.
 */
public class DefaultVehicleToSlotMapping {
    private Map<VehicleType, SlotType> vehicleTypeSlotTypeMap;

    public DefaultVehicleToSlotMapping() {
        this.vehicleTypeSlotTypeMap = new HashMap<>();
        putMapping();
    }

    private void putMapping() {
        this.vehicleTypeSlotTypeMap.put(VehicleType.TWO_WHEELER, SlotType.X_SMALL);
        this.vehicleTypeSlotTypeMap.put(VehicleType.THREE_WHEELER, SlotType.SMALL);
        this.vehicleTypeSlotTypeMap.put(VehicleType.PASSENGER_CAR, SlotType.MEDIUM);
        this.vehicleTypeSlotTypeMap.put(VehicleType.PICK_UP, SlotType.MEDIUM);
        this.vehicleTypeSlotTypeMap.put(VehicleType.BUS_ROAD_VEHICLE, SlotType.LARGE);
        this.vehicleTypeSlotTypeMap.put(VehicleType.TRUCK_MOTOR_VEHICLE, SlotType.LARGE);
        this.vehicleTypeSlotTypeMap.put(VehicleType.AGRICULTURE_VEHICLE, SlotType.LARGE);
        this.vehicleTypeSlotTypeMap.put(VehicleType.EQUIPMENT_VEHICLE, SlotType.X_LARGE);
        this.vehicleTypeSlotTypeMap.put(VehicleType.HEAVY_DUTY_VEHICLE, SlotType.X_LARGE);
    }

    public SlotType getSlotTypeFor(VehicleType vehicleType) {
        return this.vehicleTypeSlotTypeMap.get(vehicleType);
    }
}
