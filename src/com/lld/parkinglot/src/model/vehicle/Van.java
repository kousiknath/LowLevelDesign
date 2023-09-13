package com.lld.parkinglot.src.model.vehicle;

import com.lld.parkinglot.src.constant.VehicleType;
import com.lld.parkinglot.src.model.User;

public class Van extends Vehicle {
    public Van(User owner, String registrationNo) {
        super(owner, VehicleType.PICK_UP, registrationNo);
    }
}
