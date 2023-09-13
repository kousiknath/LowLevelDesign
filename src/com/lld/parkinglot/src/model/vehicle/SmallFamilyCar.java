package com.lld.parkinglot.src.model.vehicle;

import com.lld.parkinglot.src.constant.VehicleType;
import com.lld.parkinglot.src.model.User;

public class SmallFamilyCar extends Vehicle {
    public SmallFamilyCar(User owner, String registrationNo) {
        super(owner, VehicleType.PASSENGER_CAR, registrationNo);
    }
}
