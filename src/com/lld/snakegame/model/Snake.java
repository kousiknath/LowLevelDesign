package com.lld.snakegame.model;

import java.util.List;

public interface Snake {
    MoveResult move(Cell nextPosition);
    List<Cell> getBody();
    Cell head();
    int totalFoodConsumed();
}
