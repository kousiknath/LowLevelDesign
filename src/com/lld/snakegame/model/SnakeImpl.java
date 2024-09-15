package com.lld.snakegame.model;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

class MoveResultImpl implements MoveResult {
    private boolean foodEaten;
    private boolean bodyCrashed;

    public MoveResultImpl(boolean eaten, boolean bodyCrashed) {
        this.foodEaten = eaten;
        this.bodyCrashed = bodyCrashed;
    }

    @Override
    public boolean foodEaten() {
        return this.foodEaten;
    }

    @Override
    public boolean bodyCrashed() {
        return this.bodyCrashed;
    }
}

public class SnakeImpl implements Snake {
    private int size;
    private Deque<Cell> body;
    private Cell headPosition;
    private int totalFoodConsumed;

    public SnakeImpl(final Cell head) {
        this.size = 1; // The initial size is 1 as the snake is at the starting point
        this.body = new LinkedList<>();
        this.body.addFirst(head);
        this.headPosition = head;
    }

    @Override
    public MoveResult move(Cell nextPosition) {
        if (this.body.contains(nextPosition)) {
            return new MoveResultImpl(false, true);
        }

        this.body.addFirst(nextPosition);

        if (nextPosition.isHasFood()) {
            this.size++;
            this.totalFoodConsumed++;
        }

        while (this.body.size() > this.size) {
            this.body.pollLast();
        }

        this.headPosition = this.body.peekFirst();
        return new MoveResultImpl(nextPosition.isHasFood(), false);
    }

    @Override
    public List<Cell> getBody() {
        return this.body.stream().toList();
    }

    @Override
    public Cell head() {
        return this.headPosition;
    }

    @Override
    public int totalFoodConsumed() {
        return this.totalFoodConsumed;
    }
}
