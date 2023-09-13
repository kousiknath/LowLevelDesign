package com.lld.parkinglot.src.model.slot;

import com.lld.parkinglot.src.constant.SlotStatus;
import com.lld.parkinglot.src.constant.SlotType;
import com.lld.parkinglot.src.model.Level;

public abstract class Slot {
    protected int index;
    protected Level level;
    protected SlotType slotType;
    protected SlotStatus status;

    public Slot(int index, Level level, SlotType slotType) {
        this.index = index;
        this.level = level;
        this.slotType = slotType;
        this.status = SlotStatus.AVAILABLE;
    }

    public int getIndex() {
        return this.index;
    }

    public void assign() {
        this.status = SlotStatus.ASSIGNED;
    }

    public void release() {
        this.status = SlotStatus.AVAILABLE;
    }

    public SlotType getSlotType() {
        return this.slotType;
    }

    public SlotStatus getStatus() {
        return status;
    }

    public Level getLevel() {
        return this.level;
    }
}
