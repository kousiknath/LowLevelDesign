package com.lld.connectfourgame.model;

import java.util.Objects;

public abstract class Player {
    protected String name;
    protected String marker;

    public Player(String name, String marker) {
        this.name = name;
        this.marker = marker;
    }

    public String getName() {
        return name;
    }

    public String getMarker() {
        return marker;
    }

    abstract public int nextColumn(int maxColumns);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player player)) return false;
        return Objects.equals(name, player.name) && Objects.equals(marker, player.marker);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, marker);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", marker='" + marker + '\'' +
                '}';
    }
}
