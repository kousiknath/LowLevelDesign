package com.lld.snakeandladder.model;

import java.util.Random;

public class Dice {
    public int throwAndGet() {
        return 1 + new Random().nextInt(6);
    }
}
