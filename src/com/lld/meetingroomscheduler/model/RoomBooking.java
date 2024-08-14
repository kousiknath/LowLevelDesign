package com.lld.meetingroomscheduler.model;

import com.lld.meetingroomscheduler.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class RoomBooking {
    private Room room;
    private TreeSet<Duration> bookings;

    public RoomBooking(Room room) {
        this.room = room;
        this.bookings = new TreeSet<>(Util::compareDuration);
    }

    public synchronized void addDuration(Duration duration) {
        this.bookings.add(duration);
    }

    public synchronized void removeDuration(Duration duration) {
        this.bookings.remove(duration);
    }

    public List<Duration> getBookings() {
        return new ArrayList<>(this.bookings);
    }

    public Room getRoom() {
        return room;
    }
}
