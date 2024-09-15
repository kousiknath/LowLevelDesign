package com.lld.tictactoe.client;

import com.lld.tictactoe.configuration.ConfigurationReader;
import com.lld.tictactoe.configuration.ConfigurationReaderImpl;
import com.lld.tictactoe.configuration.GameConfiguration;
import com.lld.tictactoe.controller.*;
import com.lld.tictactoe.factory.PlayerFactory;
import com.lld.tictactoe.factory.PlayerFactoryImpl;
import com.lld.tictactoe.model.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameClientImpl implements GameClient {
    private GameConfiguration configuration;
    private ConfigurationReader configurationReader;
    private BoardController boardController;
    private PlayerFactory playerFactory;
    private Player player1;
    private Player player2;
    private boolean isGameOver; // Set to true when the winner is found

    public GameClientImpl() {
        this.playerFactory = new PlayerFactoryImpl();
        this.configurationReader = new ConfigurationReaderImpl();
    }

    @Override
    public void configureGame() {
        this.configuration = this.configurationReader.readConfig();
        this.boardController = new BoardControllerImpl(this.configuration.getDimension(),
                List.of(
                        new HorizontalWinningStrategy(),
                        new VerticalWinningStrategy(),
                        new DiagonalWinningStrategy()
                ));

        int numMachinePlayers = this.configuration.getNumberOfMachinePlayers();
        if (numMachinePlayers == 0) {
            this.player1 = playerFactory.createHumanPlayer();
            this.player2 = playerFactory.createHumanPlayer();
        } else if (numMachinePlayers == 1) {
            this.player1 = playerFactory.createHumanPlayer();
            this.player2 = playerFactory.createMachinePlayer();
        } else {
            this.player1 = playerFactory.createMachinePlayer();
            this.player2 = playerFactory.createMachinePlayer();
        }
    }

    @Override
    public void play() {
        Player currentPlayer = toss();

        while (!isGameOver && !this.boardController.isBoardFull()) {
            int[] cell = currentPlayer.move(this.configuration.getDimension(), this.configuration.getDimension());
            System.out.println(currentPlayer.getName() + " moving to = " + Arrays.toString(cell));

            if (this.boardController.move(cell[0], cell[1], currentPlayer)) {
                System.out.println("Move successful");
            } else {
                System.out.println("Invalid move"); // Not retrying on invalid move
            }

            System.out.println(this.boardController.displayBoard());

            if (this.boardController.isWinner(currentPlayer)) {
                isGameOver = true;
                System.out.println("Winner found = " + currentPlayer.getName());
            }

            currentPlayer = switchPlayer(currentPlayer);
        }

        if (!isGameOver) {
            System.out.println("Game Draw!!!");
        }
    }

    private Player toss() {
        if (new Random().nextBoolean()) {
            return player1;
        }

        return player2;
    }

    private Player switchPlayer(Player player) {
        if (player == player1) {
            return player2;
        }

        return player1;
    }
}
