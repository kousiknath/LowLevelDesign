package com.lld.tictactoe.controller;

import com.lld.tictactoe.model.Board;
import com.lld.tictactoe.model.Player;

public class DiagonalWinningStrategy implements WinningStrategy {
    @Override
    public boolean isWinner(Board board, Player player) {
        if (checkDiagonally(board, player)) {
            return true;
        }

        return checkReverseDiagonally(board, player);
    }

    private boolean checkDiagonally(Board board, Player player) {
        int row = 0, col = 0;

        for (int dim = 0; dim < board.getRows(); dim++) {
            if (board.get(row + dim, col + dim) != player.getMarker()) {
                return false;
            }
        }

        return true;
    }

    private boolean checkReverseDiagonally(Board board, Player player) {
        int row = 0, col = board.getCols() - 1;

        for (int dim = 0; dim < board.getRows(); dim++) {
            if (board.get(row + dim, col - dim) != player.getMarker()) {
                return false;
            }
        }

        return true;
    }
}
