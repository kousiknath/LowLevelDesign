package com.lld.elevator.model;

import java.util.List;

public class Floor {
    private String level;
    private Building building;
    private List<ElevatorPointOfRequestPanel> panels;

    public Floor(String  level, List<ElevatorPointOfRequestPanel> panels, Building building) {
        this.level = level;
        this.panels = panels;
        this.building = building;
    }

    public String getLevel() {
        return level;
    }

    public Building getBuilding() {
        return building;
    }

    public List<ElevatorPointOfRequestPanel> getPanels() {
        return panels;
    }
}
