package com.lld.snakeandladder.model;


public class Board {
    private int len;
    private PositionChanger[] cells;

    public Board(int length) {
        this.len = length;
        this.cells = new PositionChanger[length + 1];
    }

    public void addPosition(int cell, PositionChanger position) {
        validateCell(cell);

        if (position == null) {
            throw new IllegalArgumentException("Invalid position");
        }

        this.cells[cell] = position;
    }

    public PositionChanger getPositionAtCell(int cell) {
        validateCell(cell);
        return this.cells[cell];
    }

    private void validateCell(int cell) {
        if (cell < 0 || cell >= this.cells.length) {
            throw new IllegalArgumentException("Invalid cell");
        }
    }

    public Coordinate getEndOfBoard() {
        return new Coordinate(this.len);
    }
}
