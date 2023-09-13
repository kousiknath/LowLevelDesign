package com.lld.parkinglot.src.model.vehicle;

import com.lld.parkinglot.src.constant.VehicleType;
import com.lld.parkinglot.src.model.User;

public class Truck extends Vehicle {
    public Truck(User owner, String registrationNo) {
        super(owner, VehicleType.TRUCK_MOTOR_VEHICLE, registrationNo);
    }
}
