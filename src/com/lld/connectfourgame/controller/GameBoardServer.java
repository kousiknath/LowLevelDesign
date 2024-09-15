package com.lld.connectfourgame.controller;

import com.lld.connectfourgame.model.Player;

public interface GameBoardServer {
    boolean isBoardFull();
    void addWinningStrategy(WinningStrategy strategy);
    boolean isWinner(Player player);
    boolean move(int col, Player player);
    String displayBoard();

}
