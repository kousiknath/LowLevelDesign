package com.lld.tictactoe.model;

import java.util.Scanner;

public class HumanPlayer extends Player {
    public HumanPlayer(String name, char marker) {
        super(name, marker);
    }

    @Override
    public int[] move(int rows, int cols) {
        Scanner reader = new Scanner(System.in); // Reading from System.in
        System.out.println("[ Player -" + this.name + " ]" + "Enter a row number less than " + (rows + 1) + ":");
        int rowNo = reader.nextInt();

        System.out.println("[ Player -" + this.name + " ]" + "Enter a column number less than " + (cols + 1) + ":");
        int colNo = reader.nextInt();

        return new int[] { rowNo - 1, colNo - 1 };
    }
}
