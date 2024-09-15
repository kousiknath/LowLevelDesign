package com.lld.connectfourgame.controller;

import com.lld.connectfourgame.model.GameBoard;

public interface WinningStrategy {
    boolean isWinner(GameBoard board, char marker);
}
