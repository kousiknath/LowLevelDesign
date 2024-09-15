package com.lld.snakeandladder;

import com.lld.snakeandladder.exception.GameException;
import com.lld.snakeandladder.handler.GameHandler;
import com.lld.snakeandladder.handler.impl.GameHandlerImpl;
import com.lld.snakeandladder.model.*;

public class Main {

    private static void test1() throws GameException {
        int len = 100;
        Board board = new Board(len);

        for (int cell = 1; cell <= len; cell++) {
            board.addPosition(cell, new GeneralPosition(new Coordinate(cell)));
        }

        // Add ladders
        board.addPosition(1, new Ladder(new Coordinate(1), new Coordinate(38)));
        board.addPosition(4, new Ladder(new Coordinate(4), new Coordinate(14)));
        board.addPosition(9, new Ladder(new Coordinate(9), new Coordinate(31)));
        board.addPosition(21, new Ladder(new Coordinate(21), new Coordinate(42)));
        board.addPosition(28, new Ladder(new Coordinate(28), new Coordinate(84)));
        board.addPosition(51, new Ladder(new Coordinate(51), new Coordinate(67)));
        board.addPosition(72, new Ladder(new Coordinate(72), new Coordinate(91)));
        board.addPosition(80, new Ladder(new Coordinate(80), new Coordinate(99)));

        // Add snakes
        board.addPosition(17, new Snake(new Coordinate(17), new Coordinate(7)));
        board.addPosition(54, new Snake(new Coordinate(54), new Coordinate(34)));
        board.addPosition(62, new Snake(new Coordinate(62), new Coordinate(19)));
        board.addPosition(64, new Snake(new Coordinate(64), new Coordinate(60)));
        board.addPosition(87, new Snake(new Coordinate(87), new Coordinate(36)));
        board.addPosition(93, new Snake(new Coordinate(93), new Coordinate(73)));
        board.addPosition(94, new Snake(new Coordinate(94), new Coordinate(75)));
        board.addPosition(98, new Snake(new Coordinate(98), new Coordinate(79)));

        GameHandler gameHandler = new GameHandlerImpl(board);
        Player playerA = new Player("A");
        gameHandler.addPlayer(playerA);

        gameHandler.startGame();

        do {
            try {
                int diceValue = gameHandler.throwDice();
                Coordinate currentCoordinate = gameHandler.move(playerA, diceValue);
                System.out.println("Dice = " + diceValue);
                System.out.println("Player's new position = " + currentCoordinate);
                if (currentCoordinate.getCell() == len) {
                    System.out.println("Game over!");
                    break;
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } while (true);
    }

    public static void main(String[] args) throws GameException {
        test1();
    }
}
