package com.lld.tictactoe.controller;

import com.lld.tictactoe.model.Board;
import com.lld.tictactoe.model.Player;

public interface WinningStrategy {
    public boolean isWinner(Board board, Player player);
}
