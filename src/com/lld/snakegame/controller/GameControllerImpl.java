package com.lld.snakegame.controller;

import com.lld.snakegame.constant.Direction;
import com.lld.snakegame.constant.GameState;
import com.lld.snakegame.model.*;

class GameResponseImpl implements GameResponse {
    private boolean moved;
    private boolean gameOver;
    private String message;

    public GameResponseImpl(boolean moved, boolean gameOver, String message) {
        this.moved = moved;
        this.gameOver = gameOver;
        this.message = message;
    }

    @Override
    public boolean moved() {
        return this.moved;
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public String message() {
        return this.message;
    }

    @Override
    public String toString() {
        return "GameResponseImpl{" +
                "moved=" + moved +
                ", gameOver=" + gameOver +
                ", message='" + message + '\'' +
                '}';
    }
}

public class GameControllerImpl implements GameController {
    private Board board;
    private Snake snake;
    private int score;
    private boolean gameOver;
    private GameState state;

    public GameControllerImpl(final Cell[][] board) {
        this.board = new BoardImpl(board);
        this.snake = new SnakeImpl(new Cell(0, 0));
        this.state = GameState.NEW;
    }


    @Override
    public void start() {
        this.state = GameState.STARTED;
    }

    @Override
    public void end() {
        this.gameOver = true;
        this.state = GameState.ENDED;
    }

    @Override
    public GameResponse move(Direction direction) {
        if (gameOver) {
            return new GameResponseImpl(false, true, "Game is already over!");
        }

        if (this.state != GameState.STARTED) {
            return new GameResponseImpl(false, false, "Game is not started yet!");
        }

        Cell nextPosition = nextPosition(this.snake.head(), direction);
        MoveResult moveResult = this.snake.move(nextPosition);

        if (moveResult.bodyCrashed()) {
            this.gameOver = true;
            this.state = GameState.ENDED;
            return new GameResponseImpl(false, true, "Oh No! Snake has crashed on its body.");
        }

        if (moveResult.foodEaten()) {
            this.score++;
            // Since the snake can wrap around the board, to prevent it from eating
            // the same food again and again, we are removing the already eaten food.
            this.board.eraseFood(nextPosition);
        }

        if (this.snake.totalFoodConsumed() == this.board.totalFoodOnTheBoard()) {
            this.gameOver = true;
            this.state = GameState.ENDED;
            return new GameResponseImpl(true, true,
                    "The current player has own the match. Score = " + this.getScore());
        }

        return new GameResponseImpl(true, this.gameOver, "Current score = " + this.getScore());
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public boolean isGameOver() {
        return this.gameOver;
    }

    @Override
    public String displayBoard() {
        return this.board.display(this.snake.getBody());
    }

    private Cell nextPosition(Cell currentPosition, Direction direction) {
        int row = currentPosition.getRow();
        int col = currentPosition.getCol();

        switch (direction) {
            case U -> row--;
            case D -> row++;
            case L -> col--;
            case R -> col++;
        }

        if (isRowUnderFlow(row)) {
            row = this.board.getRows() - 1;
        }

        if (isRowOverFlow(row)) {
            row = 0;
        }

        if (isColumnUnderFlow(col)) {
            col = this.board.getCols() - 1;
        }

        if (isColumnOverFlow(col)) {
            col = 0;
        }

        return new Cell(row, col, this.board.hasFood(row, col));
    }

    private boolean isRowUnderFlow(int row) {
        return row < 0;
    }

    private boolean isRowOverFlow(int row) {
        return row >= this.board.getRows();
    }

    private boolean isColumnUnderFlow(int col) {
        return col < 0;
    }

    private boolean isColumnOverFlow(int col) {
        return col >= this.board.getCols();
    }
}
