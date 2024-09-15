package com.lld.connectfourgame.client;

import com.lld.connectfourgame.configuration.GameConfiguration;
import com.lld.connectfourgame.model.HumanPlayer;
import com.lld.connectfourgame.model.MachinePlayer;
import com.lld.connectfourgame.model.Player;
import com.lld.connectfourgame.controller.DiagonalWinningStrategy;
import com.lld.connectfourgame.controller.GameBoardServerImpl;
import com.lld.connectfourgame.controller.HorizontalWinningStrategy;
import com.lld.connectfourgame.controller.VerticalWinningStrategy;

import java.util.*;

public class GameClientImpl implements GameClient {
    private GameBoardServerImpl gameBoardServer;
    private Player player1;
    private Player player2;
    private Queue<String> markers;
    private boolean gameOver;
    private GameConfiguration gameConfiguration;

    public GameClientImpl() {
        this.gameConfiguration = new GameConfiguration();

        this.markers = new LinkedList<>();
        this.markers.add("X");
        this.markers.add("O");
    }

    private Player switchPlayer(Player player) {
        if (player == this.player1) {
            return this.player2;
        }

        return this.player1;
    }

    private Player tossAndChooseFirstPlayer() {
        if (new Random().nextBoolean()) {
            return player1;
        }

        return player2;
    }

    private void takeInputs() {
        Scanner sc = new Scanner(System.in);
        int inputs = 0;
        int maxInputs = 5;

        do {
            System.out.print("Enter number of rows: ");
            String rows = sc.nextLine();
            gameConfiguration.setRows(Integer.parseInt(rows));
            inputs++;
            System.out.println();

            System.out.print("Enter number of columns: ");
            String cols = sc.nextLine();
            gameConfiguration.setCols(Integer.parseInt(cols));
            inputs++;
            System.out.println();

            System.out.print("How many machine players you want? [ 0 / 1 / 2 ]: ");
            String machinePlayers = sc.nextLine();
            gameConfiguration.setMachinePlayers(Math.min(2, Math.max(0, Integer.parseInt(machinePlayers))));
            inputs++;
            System.out.println();

            int temp = gameConfiguration.getMachinePlayers();
            inputs += temp; // skip for required number of human players
            temp = 2 - temp;

            if (temp > 0) {
                System.out.print("Enter human player 1 name: ");
                String humanPlayer1 = sc.nextLine();
                gameConfiguration.setHumanPlayer1Name(humanPlayer1);
                temp--;
                inputs++;
                System.out.println();
            }

            if (temp > 0) {
                System.out.print("Enter human player 2 name: ");
                String humanPlayer2 = sc.nextLine();
                gameConfiguration.setHumanPlayer2Name(humanPlayer2);
                inputs++;
                temp--;
                System.out.println();
            }
        } while (inputs < maxInputs);
    }

    private void initialize() {
        takeInputs();

        int machinePlayers = gameConfiguration.getMachinePlayers();
        if (machinePlayers == 0) {
            this.player1 = new HumanPlayer(gameConfiguration.getHumanPlayer1Name(), this.markers.poll());
            this.player2 = new HumanPlayer(gameConfiguration.getHumanPlayer2Name(), this.markers.poll());
        } else if (machinePlayers == 1) {
            this.player1 = new HumanPlayer(gameConfiguration.getHumanPlayer1Name(), this.markers.poll());
            this.player2 = new MachinePlayer(this.markers.poll());
        } else {
            this.player1 = new MachinePlayer(this.markers.poll());
            this.player2 = new MachinePlayer(this.markers.poll());
        }

        this.gameBoardServer = new GameBoardServerImpl(gameConfiguration.getRows(),
                gameConfiguration.getCols());
        this.gameBoardServer.addWinningStrategy(new HorizontalWinningStrategy());
        this.gameBoardServer.addWinningStrategy(new VerticalWinningStrategy());
        this.gameBoardServer.addWinningStrategy(new DiagonalWinningStrategy());
    }

    @Override
    public void play() {
        initialize();

        Player currentPlayer = tossAndChooseFirstPlayer();
        System.out.println("First player = " + currentPlayer);

        while (!this.gameBoardServer.isBoardFull() && !gameOver) {
            int col = currentPlayer.nextColumn(this.gameConfiguration.getCols());
            System.out.println(currentPlayer + " moving a disk to column " + (col + 1));

            if (!this.gameBoardServer.move(col, currentPlayer)) {
                System.out.println("Could not move the disk as the column is full!");
            }

            System.out.println(gameBoardServer.displayBoard());

            if (this.gameBoardServer.isWinner(currentPlayer)) {
                gameOver = true;
                System.out.println("Winner found = " + currentPlayer);
            }

            currentPlayer = switchPlayer(currentPlayer);
        }
    }
}
