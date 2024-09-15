package com.lld.snakegame.client;

import com.lld.snakegame.constant.Direction;
import com.lld.snakegame.controller.GameController;
import com.lld.snakegame.controller.GameControllerImpl;
import com.lld.snakegame.model.Cell;
import com.lld.snakegame.model.GameResponse;

import java.util.Scanner;

public class GameClientImpl implements GameClient {
    private GameController gameController;

    public GameClientImpl(final Cell[][] board) {
        this.gameController = new GameControllerImpl(board);
    }
    @Override
    public void startGame() {
        this.gameController.start();
    }

    @Override
    public void endGame() {
        this.gameController.end();
    }

    @Override
    public GameResponse play() {
        this.gameController.start();
        GameResponse gameResponse = null;

        while (!this.gameController.isGameOver()) {
            String directionStr  = getDirectionFromUser();
            Direction direction = Direction.valueOf(directionStr);
            gameResponse = this.gameController.move(direction);
            System.out.println(gameResponse);
            System.out.println(this.gameController.displayBoard());
        }

        return gameResponse;
    }

    private String getDirectionFromUser() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the direction [U / D / L / R]: " );
        String direction = sc.nextLine();
        System.out.println();
        return direction;
    }
}
