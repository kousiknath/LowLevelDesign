package com.lld.connectfourgame.model;

import java.util.Arrays;

public class GameBoard {
    private int rows;
    private int cols;
    public char[][] board;
    private int markedCells;

    public GameBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new char[rows][cols];

        for (int i = 0; i < rows; i++) {
            Arrays.fill(board[i], '-');
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean mark(int row, int col, char marker) {
        if (this.board[row][col] != '-') {
            return false;
        }

        this.board[row][col] = marker;
        this.markedCells++;
        return true;
    }

    public boolean isBoardFull() {
        return this.markedCells == this.rows * this.cols;
    }

    public char get(int i, int j) {
        return this.board[i][j];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (char[] row: this.board) {
            builder.append(Arrays.toString(row));
            builder.append("\n");
        }

        return builder.toString();
    }

}
