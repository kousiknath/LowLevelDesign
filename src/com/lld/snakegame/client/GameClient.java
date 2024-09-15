package com.lld.snakegame.client;


import com.lld.snakegame.model.GameResponse;

public interface GameClient {
    void startGame();
    void endGame();
    GameResponse play();
}
