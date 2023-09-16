package com.lld.chatapplication.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Conversation {
    protected String id;
    protected List<Message> messages;

    public Conversation() {
        this.id = UUID.randomUUID().toString();
        this.messages = new ArrayList<>();
    }

    public String getId() {
        return this.id;
    }

    public void addMessage(Message message) {
        this.messages.add(message);
    }
}
