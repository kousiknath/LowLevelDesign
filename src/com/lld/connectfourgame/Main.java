package com.lld.connectfourgame;

import com.lld.connectfourgame.client.GameClient;
import com.lld.connectfourgame.client.GameClientImpl;

import java.io.IOException;

public class Main {

    private static void test1() throws IOException {
        GameClient gameClient = new GameClientImpl();
        gameClient.play();
    }

    public static void main(String[] args) throws IOException {
        test1();
    }
}
