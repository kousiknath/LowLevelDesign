package com.lld.chatapplication.service.listener;

import com.lld.chatapplication.exception.ListenerException;
import com.lld.chatapplication.model.Group;
import com.lld.chatapplication.model.Message;
import com.lld.chatapplication.model.User;

public interface MessageListener {
    void registerForUser(User user);
    boolean addMessageForUser(User user, Message message) throws ListenerException;
    boolean addMessageForGroup(Group group, Message message) throws ListenerException;
}
