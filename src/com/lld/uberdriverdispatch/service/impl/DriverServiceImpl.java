package com.lld.uberdriverdispatch.service.impl;

import com.lld.uberdriverdispatch.constant.DriverStatus;
import com.lld.uberdriverdispatch.model.Driver;
import com.lld.uberdriverdispatch.model.Location;
import com.lld.uberdriverdispatch.service.DriverService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class DriverServiceImpl implements DriverService {
    private List<Driver> drivers;
    private ReentrantLock lock;

    public DriverServiceImpl() {
        this.drivers = new ArrayList<>();
        this.lock = new ReentrantLock();

        initializeDrivers();
    }

    private void initializeDrivers() {
        for (int i = 0; i < new Random().nextInt(1000); i++) {
            drivers.add(new Driver("driver - " + i));
        }
    }

    @Override
    public List<Driver> fetchNearbyDrivers(Location location) {
        // return location specific drivers. For our implementation, just return a random list of drivers
        int count = 3 + new Random().nextInt(5);
        List<Driver> result = new ArrayList<>();

        try {
            this.lock.lock();
            for (Driver driver : drivers) {
                if (driver.getStatus() == DriverStatus.AVAILABLE) {
                    result.add(driver);
                    count--;
                }

                if (count == 0) {
                    break;
                }
            }
        } finally {
            this.lock.unlock();
        }

        return result;
    }
}
