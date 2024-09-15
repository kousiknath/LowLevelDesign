package com.lld.snakeandladder.handler.impl;

import com.lld.snakeandladder.constant.GameStatus;
import com.lld.snakeandladder.exception.GameException;
import com.lld.snakeandladder.handler.GameHandler;
import com.lld.snakeandladder.model.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameHandlerImpl implements GameHandler {
    private Board board;
    private Dice dice;
    private Set<Player> players;
    private List<Player> playersWhoReachedDestination;
    private GameStatus status;

    public GameHandlerImpl(Board board) {
        this.board = board;
        this.dice = new Dice();
        this.players = new HashSet<>();
        this.status = GameStatus.NEW;
        this.playersWhoReachedDestination = new ArrayList<>();
    }

    @Override
    public void addPlayer(Player player) throws GameException {
        if (status != GameStatus.NEW) {
            throw new GameException("Can't add a new player when the game is in progress!");
        }

        if (player == null) {
            throw new IllegalArgumentException("Invalid player");
        }

        this.players.add(player);
    }

    @Override
    public void startGame() throws GameException {
        if (this.status != GameStatus.NEW) {
            throw new GameException("Can not start the game as it's not in NEW state!");
        }

        this.status = GameStatus.STARTED;
    }

    @Override
    public void endGame() {
        this.status = GameStatus.ENDED;
    }

    @Override
    public int throwDice() {
        return this.dice.throwAndGet();
    }

    @Override
    public Coordinate move(Player player, int steps) throws GameException {
        this.validateMovement(player, steps);

        Coordinate currentPosition = player.currentPosition();
        try {
            PositionChanger expectedDestination = this.board.getPositionAtCell(
                    currentPosition.getCell() + steps);
            Coordinate actualDestination = expectedDestination.getDestination();
            player.updateCurrentPosition(actualDestination);

            if (actualDestination.equals(this.board.getEndOfBoard())) {
                this.playersWhoReachedDestination.add(player);
            }

            return actualDestination;

        } catch (Exception ex) {
            throw new GameException("Invalid movement");
        }
    }

    private void validateMovement(Player player, int steps) throws GameException {
        if (status != GameStatus.STARTED) {
            throw new GameException("Game is not started yet");
        }

        if (this.playersWhoReachedDestination.contains(player)) {
            throw new GameException("Player has already completed the game");
        }

        if (!this.players.contains(player)) {
            throw new GameException("Player does not exist");
        }

        if (steps <= 0) {
            throw new GameException("Steps must be positive and less than or equals to 6");
        }
    }

    @Override
    public Player winner() {
        if (this.playersWhoReachedDestination.isEmpty()) {
            return null;
        }

        // Return the first player of the following list assuming
        // this player reached destination
        return this.playersWhoReachedDestination.get(0);
    }
}
