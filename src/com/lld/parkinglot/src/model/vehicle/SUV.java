package com.lld.parkinglot.src.model.vehicle;

import com.lld.parkinglot.src.constant.VehicleType;
import com.lld.parkinglot.src.model.User;

public class SUV extends Vehicle {
    public SUV(User owner, String registrationNo) {
        super(owner, VehicleType.PASSENGER_CAR, registrationNo);
    }
}
