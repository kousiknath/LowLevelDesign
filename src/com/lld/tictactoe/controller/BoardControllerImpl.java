package com.lld.tictactoe.controller;

import com.lld.tictactoe.model.Board;
import com.lld.tictactoe.model.Player;

import java.util.ArrayList;
import java.util.List;

public class BoardControllerImpl implements BoardController {
    private Board board;
    private List<WinningStrategy> winningStrategies;

    public BoardControllerImpl(int dimension, List<WinningStrategy> strategies) {
        this.board = new Board(dimension);
        this.winningStrategies = new ArrayList<>();
        this.winningStrategies.addAll(strategies);
    }

    @Override
    public boolean move(int row, int col, Player player) {
        if (this.board.mark(row, col, player.getMarker())) {
            return true;
        }

        return false;
    }

    @Override
    public void addWinningStrategy(WinningStrategy strategy) {
        this.winningStrategies.add(strategy);
    }

    @Override
    public boolean isWinner(Player player) {
        for (WinningStrategy winningStrategy: this.winningStrategies) {
            if (winningStrategy.isWinner(this.board, player)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean isBoardFull() {
        return this.board.isFull();
    }

    @Override
    public String displayBoard() {
        return this.board.toString();
    }
}
