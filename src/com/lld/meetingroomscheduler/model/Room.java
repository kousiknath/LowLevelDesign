package com.lld.meetingroomscheduler.model;

import com.lld.meetingroomscheduler.constant.RoomType;

import java.util.Objects;
import java.util.UUID;

public class Room {
    private String id;
    private String name;
    private int capacity;
    private RoomType roomType;

    public Room(String name, int capacity, RoomType type) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.capacity = capacity;
        this.roomType = type;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room room)) return false;
        return Objects.equals(id, room.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", roomType=" + roomType +
                '}';
    }
}
