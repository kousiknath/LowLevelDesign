package com.lld.chatapplication.service;

import com.lld.chatapplication.exception.ListenerException;
import com.lld.chatapplication.model.Group;
import com.lld.chatapplication.model.Message;
import com.lld.chatapplication.model.UserPair;

public interface ConversationService {
    void addMessage(UserPair userPair, Message message) throws ListenerException;
    void addMessage(Group group, Message message) throws ListenerException;
}
