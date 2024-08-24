package com.lld.stockbroker.model;

import java.util.Objects;

public class Sector {
    private String sectorName;
    private Double sectorPE;
    private Double sectorPB;

    public Sector(String name) {
        this.sectorName = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sector sector)) return false;
        return Objects.equals(sectorName, sector.sectorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sectorName);
    }

    @Override
    public String toString() {
        return "Sector{" +
                "sectorName='" + sectorName + '\'' +
                '}';
    }
}
