package com.lld.parkinglot.src.model.vehicle;

import com.lld.parkinglot.src.constant.VehicleType;
import com.lld.parkinglot.src.model.User;

public class TwoWheeler extends Vehicle {
    public TwoWheeler(User owner, String registrationNo) {
        super(owner, VehicleType.TWO_WHEELER, registrationNo);
    }
}
