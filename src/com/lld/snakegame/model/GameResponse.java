package com.lld.snakegame.model;

public interface GameResponse {
    boolean moved();
    String message();
    boolean isGameOver();
}
