package com.lld.snakeandladder.model;

import java.util.Objects;

public class Player {
    private String name;
    private Coordinate currentPosition;
    public Player(String name) {
        this.name = name;
        // Every player starts the game from the 0-coordinate
        this.currentPosition = new Coordinate(0);
    }

    public Coordinate currentPosition() {
        return this.currentPosition;
    }

    public void updateCurrentPosition(Coordinate currentPosition) {
        this.currentPosition = currentPosition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player player)) return false;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                '}';
    }
}
