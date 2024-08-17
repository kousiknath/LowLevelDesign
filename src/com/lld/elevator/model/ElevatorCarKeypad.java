package com.lld.elevator.model;

import java.util.ArrayList;
import java.util.List;

public class ElevatorCarKeypad {
    private List<InfoButton> keyButtons;

    public ElevatorCarKeypad() {
        this.keyButtons = new ArrayList<>();
        addButtons();
    }

    private void addButtons() {
        this.keyButtons.add(new InfoButton("G"));
        this.keyButtons.add(new InfoButton("1"));
        this.keyButtons.add(new InfoButton("2"));
        this.keyButtons.add(new InfoButton("3"));
        this.keyButtons.add(new InfoButton("4"));
        this.keyButtons.add(new InfoButton("5"));
        this.keyButtons.add(new InfoButton("6"));
        this.keyButtons.add(new InfoButton("7"));
        this.keyButtons.add(new InfoButton("8"));
        this.keyButtons.add(new InfoButton("9"));
        this.keyButtons.add(new InfoButton("R"));
    }
}
