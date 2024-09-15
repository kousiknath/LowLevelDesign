package com.lld.tictactoe.controller;

import com.lld.tictactoe.model.Player;

public interface BoardController {
    boolean move(int row, int col, Player player);
    void addWinningStrategy(WinningStrategy strategy);
    boolean isWinner(Player player);
    boolean isBoardFull();
    String displayBoard();
}
