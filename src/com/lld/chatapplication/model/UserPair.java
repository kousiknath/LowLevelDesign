package com.lld.chatapplication.model;

import java.util.UUID;

public class UserPair {
    private String id;
    private User sender;
    private User receiver;

    public UserPair(User one, User another) {
        this.id = UUID.randomUUID().toString();
        this.sender = one;
        this.receiver = another;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        if (this == o) {
            return true;
        }

        UserPair other = (UserPair) o;

        return (this.sender.equals(other.sender) && this.receiver.equals(other.receiver))
                || (this.sender.equals(other.receiver) && this.receiver.equals(other.sender));
    }
}
