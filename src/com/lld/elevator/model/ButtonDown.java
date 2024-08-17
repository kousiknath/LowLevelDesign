package com.lld.elevator.model;

import com.lld.elevator.constant.Direction;

public class ButtonDown extends DirectionButton {
    @Override
    public Direction press() {
        return Direction.DOWN;
    }
}
