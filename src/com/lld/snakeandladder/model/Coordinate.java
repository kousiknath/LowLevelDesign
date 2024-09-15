package com.lld.snakeandladder.model;

import java.util.Objects;

public class Coordinate implements Comparable<Coordinate> {
    int cell;

    public Coordinate(int cell) {
        this.cell = cell;
    }

    public int getCell() {
        return cell;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate that)) return false;
        return getCell() == that.getCell();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCell());
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "cell=" + cell +
                '}';
    }

    @Override
    public int compareTo(Coordinate o) {
        return this.cell - o.cell;
    }
}
