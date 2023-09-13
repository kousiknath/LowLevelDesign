package com.lld.parkinglot.src.model.vehicle;

import com.lld.parkinglot.src.constant.VehicleType;
import com.lld.parkinglot.src.model.User;

public class Bus extends Vehicle {
    public Bus(User owner, String registrationNo) {
        super(owner, VehicleType.BUS_ROAD_VEHICLE, registrationNo);
    }
}
