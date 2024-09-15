package com.lld.tictactoe;

import com.lld.tictactoe.client.GameClient;
import com.lld.tictactoe.client.GameClientImpl;

public class Main {

    private static void test1() {
        GameClient gameClient = new GameClientImpl();
        gameClient.configureGame();
        gameClient.play();
    }

    public static void main(String[] args) {
        test1();
    }
}
