package com.lld.connectfourgame.model;

import java.util.Scanner;

public class HumanPlayer extends Player {

    public HumanPlayer(String name, String marker) {
        super(name, marker);
    }

    @Override
    public int nextColumn(int maxColumns) {
        Scanner reader = new Scanner(System.in); // Reading from System.in
        System.out.println("Enter a column number less than " + (maxColumns + 1) + ":");
        int number = reader.nextInt();
        return number - 1;
    }
}
