package com.lld.tictactoe.configuration;

public class GameConfiguration {
    private int dimension;
    private int numberOfMachinePlayers;
    private static final int MAXIMUM_PLAYERS = 2;

    public GameConfiguration() {

    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public int getNumberOfMachinePlayers() {
        return numberOfMachinePlayers;
    }

    public void setNumberOfMachinePlayers(int numberOfMachinePlayers) {
        this.numberOfMachinePlayers = numberOfMachinePlayers;
    }
}
