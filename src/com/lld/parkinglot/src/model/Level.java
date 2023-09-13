package com.lld.parkinglot.src.model;

import com.lld.parkinglot.src.config.Config;
import com.lld.parkinglot.src.constant.SlotStatus;
import com.lld.parkinglot.src.constant.SlotType;
import com.lld.parkinglot.src.model.slot.Slot;
import com.lld.parkinglot.src.model.slot.XSmallSlot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Level {
    private int floor;
    private int numOfXSmallSlots; // We represent total number of slots available in a level by the number of smallest
    // slots i.e; XSmallSlot. If we have to add say a medium slot, we have to calculate the slots accordingly.
    private List<Slot> slots; // A level consists of multiple XSmallSlots. Hence, when a vehicle is parked, instead of
    // giving a single slot, we provide a range of slots to park that vehicle.

    public Level(int floor, int numSlots) {
        this.floor = floor;
        this.numOfXSmallSlots = Math.min(numSlots, Config.MAXIMUM_XSMALL_SLOTS_PER_LEVEL);
        this.slots = new ArrayList<>();
        initializeSlots();
    }

    private void initializeSlots() {
        for (int idx = 0; idx < this.numOfXSmallSlots; idx++) {
            this.slots.add(new XSmallSlot(idx, this, SlotType.X_SMALL));
        }
    }

    public int findConsecutiveAvailableSlots(int count) {
        for (int i = 0; i < this.slots.size(); i++) {
            if (this.slots.get(i).getStatus() == SlotStatus.AVAILABLE) {
                if (findConsecutiveAvailableSlots(i, count)) {
                    return i;
                }
            }
        }

        return -1;
    }

    private boolean findConsecutiveAvailableSlots(int index, int count) {
        int idx = index;
        while (idx < this.slots.size() && idx < index + count) {
            if (this.slots.get(idx).getStatus() != SlotStatus.AVAILABLE) {
                return false;
            }

            idx++;
        }

        return true;
    }

    public List<Slot> allocateConsecutiveSlots(int count) {
        int startingIdx = findConsecutiveAvailableSlots(count);
        if (startingIdx < 0) {
            return Collections.emptyList();
        }

        List<Slot> result = new ArrayList<>();
        for (int idx = startingIdx; idx < startingIdx + count; idx++) {
            this.slots.get(idx).assign();
            result.add(this.slots.get(idx));
        }

        return result;
    }

    public void deallocateConsecutiveSlots(int index, int count) {
        for (int idx = index; idx < index + count; idx++) {
            if (idx >= this.slots.size()) {
                return;
            }

            this.slots.get(idx).release();
        }

    }

    public int getFloor() {
        return this.floor;
    }
}
