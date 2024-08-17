package com.lld.elevator.model;

import com.lld.elevator.constant.Direction;

public class ButtonUp extends DirectionButton {
    @Override
    public Direction press() {
        return Direction.UP;
    }
}
