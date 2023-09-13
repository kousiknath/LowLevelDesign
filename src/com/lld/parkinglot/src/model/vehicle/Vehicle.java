package com.lld.parkinglot.src.model.vehicle;

import com.lld.parkinglot.src.constant.VehicleType;
import com.lld.parkinglot.src.model.User;

public abstract class Vehicle {
    protected User owner;

    protected VehicleType vehicleType;
    protected String registrationNo;

    public Vehicle(User owner, VehicleType vehicleType, String registrationNo) {
        this.owner = owner;
        this.vehicleType = vehicleType;
        this.registrationNo = registrationNo;
    }

    public User getOwner() {
        return this.owner;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }
}
