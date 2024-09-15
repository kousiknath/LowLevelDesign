package com.lld.tictactoe.controller;

import com.lld.tictactoe.model.Board;
import com.lld.tictactoe.model.Player;

public class VerticalWinningStrategy implements WinningStrategy {
    @Override
    public boolean isWinner(Board board, Player player) {
        for (int col = 0; col < board.getCols(); col++) {
            int count = 0;

            for (int row = 0; row < board.getRows(); row++) {
                if (board.get(row, col) == player.getMarker()) {
                    count++;
                }
            }

            if (count == board.getRows()) {
                return true;
            }
        }

        return false;
    }
}
