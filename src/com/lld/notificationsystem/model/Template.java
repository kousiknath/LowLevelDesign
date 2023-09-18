package com.lld.notificationsystem.model;

import com.lld.notificationsystem.constant.MessageType;

import java.util.UUID;

public class Template {
    private String id;
    private MessageType messageType;
    private String template;

    public Template(MessageType messageType, String template) {
        this.id = UUID.randomUUID().toString();
        this.messageType = messageType;
        this.template = template;
    }

    public String getId() {
        return id;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public String getTemplate() {
        return template;
    }
}
