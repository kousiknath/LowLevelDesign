package com.lld.connectfourgame.controller;

import com.lld.connectfourgame.model.GameBoard;

public class VerticalWinningStrategy implements WinningStrategy {
    @Override
    public boolean isWinner(GameBoard board, char marker) {
        for (int col = 0; col < board.getCols(); col++) {
            for (int row = 0; row <= board.getRows() - 4; row++) {
                if (board.get(row, col) != marker) {
                    continue;
                }

                if (board.get(row, col) == board.get(row + 1, col)
                        && board.get(row + 1, col) == board.get(row + 2, col)
                        && board.get(row + 2, col) == board.get(row + 3, col)) {
                    return true;
                }
            }
        }

        return false;
    }
}
