package com.lld.connectfourgame.configuration;

public class GameConfiguration {
    private int rows;
    private int cols;
    private int machinePlayers;
    private String humanPlayer1Name;
    private String humanPlayer2Name;

    public GameConfiguration() {

    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getMachinePlayers() {
        return machinePlayers;
    }

    public void setMachinePlayers(int machinePlayers) {
        this.machinePlayers = machinePlayers;
    }

    public String getHumanPlayer1Name() {
        return humanPlayer1Name;
    }

    public void setHumanPlayer1Name(String humanPlayer1Name) {
        this.humanPlayer1Name = humanPlayer1Name;
    }

    public String getHumanPlayer2Name() {
        return humanPlayer2Name;
    }

    public void setHumanPlayer2Name(String humanPlayer2Name) {
        this.humanPlayer2Name = humanPlayer2Name;
    }
}
