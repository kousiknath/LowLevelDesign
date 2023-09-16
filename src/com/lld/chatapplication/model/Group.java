package com.lld.chatapplication.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Group {
    private final String id;
    private final String name; // name is supposed to be unique
    private final String description;
    private Set<User> participants;

    public Group(String name, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
        this.participants = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<User> getParticipants() {
        return this.participants;
    }

    public void addParticipant(User participant) {
        this.participants.add(participant);
    }

    public void removeParticipant(User participant) {
        this.participants.remove(participant);
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        if (this == o) {
            return true;
        }

        return this.name.equals(((Group) o).name);
    }
}
