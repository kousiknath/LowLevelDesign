package com.lld.tictactoe.model;

import java.util.Arrays;

public class Board {
    private int rows;
    private int cols;
    private char[][] board;
    private int marked;

    public Board(int dimension) {
        this.rows = dimension;
        this.cols = dimension;
        this.board = new char[dimension][dimension];

        initializeBoard();
    }

    private void initializeBoard() {
        for (char[] row: this.board) {
            Arrays.fill(row, '-');
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public boolean mark(int i, int j, char marker) {
        if (!isValidMove(i, j)) {
            return false;
        }

        if (this.board[i][j] != '-') {
            return false;
        }

        this.board[i][j] = marker;
        this.marked++;
        return true;
    }

    private boolean isValidMove(int row, int col) {
        return row >= 0 && row < this.getRows() && col >= 0 && col < this.getCols();
    }

    public char get(int i, int j) {
        return this.board[i][j];
    }

    public boolean isFull() {
        return this.marked == this.rows * this.cols;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (char[] row: this.board) {
            builder.append(Arrays.toString(row));
            builder.append("\n");
        }

        return builder.toString();
    }
}
