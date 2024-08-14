package com.lld.meetingroomscheduler.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Meeting {
    private String id;
    private String title;
    private String description;
    private Duration duration;
    private int noOfParticipants;
    private User host;
    private List<User> participants;
    private Room room;

    public Meeting(String title, Duration duration, User host, List<User> participants) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.duration = duration;
        this.host = host;
        this.participants = participants;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Duration getDuration() {
        return duration;
    }

    public int getNoOfParticipants() {
        return noOfParticipants;
    }

    public User getHost() {
        return host;
    }

    public List<User> getParticipants() {
        return Collections.unmodifiableList(participants);
    }

    public Room getRoom() {
        return room;
    }

    public synchronized void assignRoom(Room room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meeting meeting)) return false;
        return Objects.equals(id, meeting.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "title='" + title + '\'' +
                ", duration=" + duration +
                ", room=" + room +
                '}';
    }
}
