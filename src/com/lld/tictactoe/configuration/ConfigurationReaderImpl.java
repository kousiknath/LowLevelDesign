package com.lld.tictactoe.configuration;

import java.util.Scanner;

public class ConfigurationReaderImpl implements ConfigurationReader {
    private GameConfiguration configuration;
    public ConfigurationReaderImpl() {
        this.configuration = new GameConfiguration();
    }

    @Override
    public GameConfiguration readConfig() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the dimension (number of rows or columns): " );
        String dimension = sc.nextLine();
        configuration.setDimension(Integer.parseInt(dimension));
        System.out.println();

        System.out.print("How many machine players you want? [ 0 / 1 / 2 ]: ");
        String machinePlayers = sc.nextLine();
        configuration.setNumberOfMachinePlayers(Math.min(2, Math.max(0, Integer.parseInt(machinePlayers))));
        System.out.println();

        return this.configuration;
    }
}
