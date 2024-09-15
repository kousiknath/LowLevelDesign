package com.lld.connectfourgame.controller;

import com.lld.connectfourgame.model.GameBoard;

public class DiagonalWinningStrategy implements WinningStrategy {
    @Override
    public boolean isWinner(GameBoard board, char marker) {
        if (isWinnerDiagonally(board, marker)) {
            return true;
        }

        return isWinnerReverseDiagonally(board, marker);
    }

    private boolean isWinnerDiagonally(GameBoard board, char marker) {
        for (int row = 0; row <= board.getRows() - 4; row++) {
            for (int col = 0; col <= board.getCols() - 4; col++) {
                if (board.get(row, col) != marker) {
                    continue;
                }

                if (board.get(row, col) == board.get(row + 1, col + 1)
                        && board.get(row + 1, col + 1) == board.get(row + 2, col + 2)
                        && board.get(row + 2, col + 2) == board.get(row + 3, col + 3)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isWinnerReverseDiagonally(GameBoard board, char marker) {
        for (int row = 0; row <= board.getRows() - 4; row++) {
            for (int col = board.getCols() - 1; col >= board.getCols() - 4; col--) {
                if (board.get(row, col) != marker) {
                    continue;
                }

                if (board.get(row, col) == board.get(row + 1, col - 1)
                        && board.get(row + 1, col - 1) == board.get(row + 2, col - 2)
                        && board.get(row + 2, col - 2) == board.get(row + 3, col - 3)) {
                    return true;
                }
            }
        }

        return false;
    }
}
