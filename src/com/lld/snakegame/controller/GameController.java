package com.lld.snakegame.controller;

import com.lld.snakegame.constant.Direction;
import com.lld.snakegame.model.GameResponse;

public interface GameController {
    void start();
    void end();
    GameResponse move(Direction direction);
    int getScore();
    boolean isGameOver();
    String displayBoard();
}
