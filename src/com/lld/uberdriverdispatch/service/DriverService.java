package com.lld.uberdriverdispatch.service;

import com.lld.uberdriverdispatch.model.Driver;
import com.lld.uberdriverdispatch.model.Location;

import java.util.List;

public interface DriverService {
    List<Driver> fetchNearbyDrivers(Location location);
}
