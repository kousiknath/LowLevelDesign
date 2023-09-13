package com.lld.parkinglot.src.model;

import com.lld.parkinglot.src.config.Config;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private int numBuildings;
    private List<Building> buildings;

    public ParkingLot(int numBuildings) {
        this.numBuildings = numBuildings;
        this.buildings = new ArrayList<>();
    }

    public boolean addBuilding(Building building) {
        if (this.buildings.size() < Math.min(numBuildings, Config.MAXIMUM_BUILDINGS)) {
            this.buildings.add(building);
            return true;
        }

        return false;
    }

    public int getNumBuildings() {
        return this.numBuildings;
    }

    public List<Building> getBuildings() {
        return this.buildings;
    }
}
