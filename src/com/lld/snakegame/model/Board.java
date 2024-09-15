package com.lld.snakegame.model;


import java.util.List;

public interface Board {
    int getRows();
    int getCols();
    String display(List<Cell> externalMarkers);
    boolean hasFood(int row, int col);
    int totalFoodOnTheBoard();
    void eraseFood(Cell cell);
}
