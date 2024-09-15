package com.lld.connectfourgame.model;

import java.util.Random;
import java.util.UUID;

public class MachinePlayer extends Player {

    public MachinePlayer(String marker) {
        super("Machine - " + UUID.randomUUID().toString().substring(1, 4), marker);
    }

    @Override
    public int nextColumn(int maxColumns) {
        return new Random().nextInt(maxColumns);
    }
}
