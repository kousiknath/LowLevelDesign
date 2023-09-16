package com.lld.chatapplication.service;

import com.lld.chatapplication.exception.ListenerException;
import com.lld.chatapplication.model.Group;
import com.lld.chatapplication.model.Message;
import com.lld.chatapplication.model.User;
import com.lld.uberdriverdispatch.exception.ServiceException;

public interface ChatService {
    void sendPersonalMessage(User receiver, Message message) throws ServiceException, ListenerException; // Any individual user or business user can send a personal
    // message to another individual or business user
    void sendGroupMessage(Group group, Message message) throws ServiceException, ListenerException;
}
