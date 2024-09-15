package com.lld.tictactoe.controller;

import com.lld.tictactoe.model.Board;
import com.lld.tictactoe.model.Player;

public class HorizontalWinningStrategy implements WinningStrategy {
    @Override
    public boolean isWinner(Board board, Player player) {
        for (int row = 0; row < board.getRows(); row++) {
            int count = 0;

            for (int col = 0; col < board.getCols(); col++) {
                if (board.get(row, col) == player.getMarker()) {
                    count++;
                }
            }

            if (count == board.getCols()) {
                return true;
            }
        }

        return false;
    }
}
