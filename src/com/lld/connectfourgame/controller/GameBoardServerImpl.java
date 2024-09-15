package com.lld.connectfourgame.controller;

import com.lld.connectfourgame.model.GameBoard;
import com.lld.connectfourgame.model.Player;

import java.util.ArrayList;
import java.util.List;

public class GameBoardServerImpl implements GameBoardServer {
    public GameBoard gameBoard;
    private List<WinningStrategy> winningStrategies;

    public GameBoardServerImpl(int rows, int cols) {
        this.gameBoard = new GameBoard(rows, cols);
        this.winningStrategies = new ArrayList<>();
    }

    @Override
    public boolean isBoardFull() {
        return this.gameBoard.isBoardFull();
    }

    @Override
    public void addWinningStrategy(WinningStrategy strategy) {
        this.winningStrategies.add(strategy);
    }

    @Override
    public boolean isWinner(Player player) {
        for (WinningStrategy strategy: winningStrategies) {
            if (strategy.isWinner(gameBoard, player.getMarker().charAt(0))) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean move(int col, Player player) {
        for (int row = gameBoard.getRows() - 1; row >= 0; row--) {
            if (gameBoard.get(row, col) == '-') {
                gameBoard.mark(row, col, player.getMarker().charAt(0));
                return true;
            }
        }

        return false;
    }

    @Override
    public String displayBoard() {
        return this.gameBoard.toString();
    }
}
