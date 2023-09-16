package com.lld.chatapplication.service.impl;

import com.lld.chatapplication.exception.ListenerException;
import com.lld.chatapplication.model.*;
import com.lld.chatapplication.repository.UserRepository;
import com.lld.chatapplication.service.ChatService;
import com.lld.chatapplication.service.ConversationService;
import com.lld.uberdriverdispatch.exception.ServiceException;

import java.util.Set;

public class ChatServiceImpl implements ChatService {
    private ConversationService conversationService;
    private UserRepository userRepository;

    public ChatServiceImpl(ConversationService conversationService, UserRepository userRepository) {
        this.conversationService = conversationService;
        this.userRepository = userRepository;
    }

    @Override
    public void sendPersonalMessage(User receiver, Message message) throws ServiceException, ListenerException {
        if (receiver == null || message == null) {
            throw new ServiceException("sender, receiver and message should be non null");
        }

        Set<User> contacts = this.userRepository.getContacts(message.getSender());
        if (!contacts.contains(receiver)) {
            throw new ServiceException("Receiver does not exist in the sender contact list");
        }

        this.conversationService.addMessage(new UserPair(message.getSender(), receiver), message);
    }

    @Override
    public void sendGroupMessage(Group group, Message message) throws ServiceException, ListenerException {
        if (group == null || message == null) {
            throw new ServiceException("sender, group and message should be non null");
        }

        if (!group.getParticipants().contains(message.getSender())) {
            throw new ServiceException("The sender " + message.getSender().getName()
                    + " is not a member of the group: " + group.getName());
        }

        this.conversationService.addMessage(group, message);
    }
}
