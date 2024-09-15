package com.lld.snakeandladder.handler;

import com.lld.snakeandladder.exception.GameException;
import com.lld.snakeandladder.model.Coordinate;
import com.lld.snakeandladder.model.Player;

public interface GameHandler {
    void addPlayer(Player player) throws GameException;
    void startGame() throws GameException;
    void endGame();
    int throwDice();
    Coordinate move(Player player, int steps) throws GameException;
    Player winner();
}
