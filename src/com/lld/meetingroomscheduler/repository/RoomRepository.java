package com.lld.meetingroomscheduler.repository;

import com.lld.meetingroomscheduler.model.Duration;
import com.lld.meetingroomscheduler.model.Room;
import com.lld.meetingroomscheduler.model.RoomBooking;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantLock;

public class RoomRepository {
    private ConcurrentMap<Room, RoomBooking> roomBookings;
    private ReentrantLock lock;

    public RoomRepository() {
        this.roomBookings = new ConcurrentHashMap<>();
        this.lock = new ReentrantLock();
    }

    public Room register(Room room) {
        this.roomBookings.put(room, new RoomBooking(room));
        return room;
    }
    public boolean addDurationToRoom(Room room, Duration duration) {
        try {
            this.lock.lock();

            RoomBooking booking = this.roomBookings.getOrDefault(room, new RoomBooking(room));
            booking.addDuration(duration);
        } finally {
            this.lock.unlock();
        }

        return true;
    }

    public boolean removeDurationFromRoom(Room room, Duration duration) {
        try {
            this.lock.lock();

            RoomBooking booking = this.roomBookings.getOrDefault(room, new RoomBooking(room));
            booking.removeDuration(duration);
        } finally {
            this.lock.unlock();
        }

        return true;
    }

    public List<RoomBooking> getAllRoomBookings() {
        return new ArrayList<>(this.roomBookings.values());
    }
}
