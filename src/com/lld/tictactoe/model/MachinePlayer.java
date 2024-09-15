package com.lld.tictactoe.model;

import java.util.Random;
import java.util.UUID;

public class MachinePlayer extends Player {

    public MachinePlayer(char marker) {
        super("Machine Player - " + UUID.randomUUID().toString().substring(0, 4), marker);
    }

    @Override
    public int[] move(int rows, int cols) {
        Random rand = new Random();
        return new int[] { rand.nextInt(rows), rand.nextInt(cols) };
    }
}
