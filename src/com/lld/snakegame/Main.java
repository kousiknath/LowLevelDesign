package com.lld.snakegame;


import com.lld.snakegame.client.GameClient;
import com.lld.snakegame.client.GameClientImpl;
import com.lld.snakegame.model.Cell;
import com.lld.snakegame.model.GameResponse;

public class Main {

    private static void test1() {
        Cell[][] board = {
                {new Cell(0,0, false), new Cell(0, 1, true),
                        new Cell(0, 2, false), new Cell(0, 3, true)},
                {new Cell(1, 0, false), new Cell(1, 1, false),
                        new Cell(1, 2, true), new Cell(1, 3, false)},
                {new Cell(2, 0, true), new Cell(2, 1, false),
                        new Cell(2, 2, false), new Cell(2, 3, false)},
                {new Cell(3, 0, false), new Cell(3, 1, false),
                        new Cell(3, 2, false), new Cell(3, 3, true)},
        };

        GameClient gameClient = new GameClientImpl(board);
        GameResponse response = gameClient.play();
        assert response != null;
    }

    public static void main(String[] args) {
        test1();
    }
}
