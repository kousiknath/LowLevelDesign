package com.lld.stockbroker.model;

public class Sector {
    private String sectorName;
    private Double sectorPE;
    private Double sectorPB;

    public Sector(String name) {
        this.sectorName = name;
    }

    @Override
    public String toString() {
        return "Sector{" +
                "sectorName='" + sectorName + '\'' +
                '}';
    }
}
