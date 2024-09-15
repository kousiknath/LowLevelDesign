package com.lld.snakegame.model;

import java.util.Objects;

public class Cell {
    private final int row;
    private final int col;
    private boolean hasFood;

    public Cell(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public Cell(int row, int col, boolean hasFood) {
        this(row, col);
        this.hasFood = hasFood;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isHasFood() {
        return hasFood;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cell cell)) return false;
        return row == cell.row && col == cell.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}
