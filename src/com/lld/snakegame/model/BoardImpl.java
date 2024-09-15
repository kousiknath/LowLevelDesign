package com.lld.snakegame.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BoardImpl implements Board {
    private final Cell[][] board;
    private final int rows;
    private final int cols;
    private int totalFood;

    public BoardImpl(final Cell[][] board) {
        this.board = board;
        this.rows = board.length;
        this.cols = board[0].length;

        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
                if (board[row][col].isHasFood()) {
                    this.totalFood++;
                }
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    @Override
    public String display(List<Cell> externalMarkers) {
        Set<Cell> markers = new HashSet<>(externalMarkers);
        StringBuilder result = new StringBuilder();

        for (Cell[] row: board) {
            StringBuilder builder = new StringBuilder();
            for (Cell cell : row) {
                if (markers.contains(cell)) {
                    builder.append("x  ");
                } else if (cell.isHasFood()) {
                    builder.append("f  ");
                } else {
                    builder.append("o  ");
                }
            }
            result.append(builder.toString().trim());
            result.append("\n");
        }

        return result.toString();
    }

    @Override
    public boolean hasFood(int row, int col) {
        return this.board[row][col].isHasFood();
    }

    @Override
    public int totalFoodOnTheBoard() {
        return this.totalFood;
    }

    @Override
    public void eraseFood(Cell cell) {
        this.board[cell.getRow()][cell.getCol()] = new Cell(cell.getRow(), cell.getCol());
    }
}
