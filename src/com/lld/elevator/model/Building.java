package com.lld.elevator.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Building {
    private String buildingNo;
    private List<Floor> floors;

    public Building(String no) {
        this.buildingNo = no;
        this.floors = new ArrayList<>();
    }

    public void addFloor(Floor floor) {
        this.floors.add(floor);
    }

    public String getBuildingNo() {
        return buildingNo;
    }

    public List<Floor> getFloors() {
        return Collections.unmodifiableList(floors);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Building building)) return false;
        return Objects.equals(getBuildingNo(), building.getBuildingNo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBuildingNo());
    }

    @Override
    public String toString() {
        return "Building{" +
                "buildingNo='" + buildingNo + '\'' +
                '}';
    }
}
