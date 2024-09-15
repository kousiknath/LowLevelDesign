package com.lld.connectfourgame.controller;

import com.lld.connectfourgame.model.GameBoard;

public class HorizontalWinningStrategy implements WinningStrategy {
    @Override
    public boolean isWinner(GameBoard board, char marker) {
        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col <= board.getCols() - 4; col++) {
                if (board.get(row, col) != marker) {
                    continue;
                }

                if (board.get(row, col) == board.get(row, col + 1)
                        && board.get(row, col + 1) == board.get(row, col + 2)
                        && board.get(row, col + 2) == board.get(row, col + 3)) {
                    return true;
                }
            }
        }
        return false;
    }
}
