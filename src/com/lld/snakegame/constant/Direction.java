package com.lld.snakegame.constant;

public enum Direction {
    U ("UP"),
    D ("DOWN"),
    L ("LEFT"),
    R ("RIGHT");
    private final String value;

    Direction(String val) {
        this.value = val;
    }
}
