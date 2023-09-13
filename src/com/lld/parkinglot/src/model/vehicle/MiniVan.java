package com.lld.parkinglot.src.model.vehicle;

import com.lld.parkinglot.src.constant.VehicleType;
import com.lld.parkinglot.src.model.User;

public class MiniVan extends Vehicle {
    public MiniVan(User owner, String registrationNo) {
        super(owner, VehicleType.PICK_UP, registrationNo);
    }
}
