package com.lld.chatapplication.service.impl;

import com.lld.chatapplication.exception.ListenerException;
import com.lld.chatapplication.service.listener.MessageListener;
import com.lld.chatapplication.model.*;
import com.lld.chatapplication.repository.ConversationRepository;
import com.lld.chatapplication.service.ConversationService;

public class ConversationServiceImpl implements ConversationService {
    private ConversationRepository conversationRepository;
    private MessageListener messageListener;

    public ConversationServiceImpl(ConversationRepository conversationRepository, MessageListener messageListener) {
        this.conversationRepository = conversationRepository;
        this.messageListener = messageListener;
    }

    @Override
    public void addMessage(UserPair userPair, Message message) throws ListenerException {
        PrivateConversation conversation = this.conversationRepository.retrieveConversation(userPair);
        conversation.addMessage(message);

        System.out.println("Message sent by: " + userPair.getSender().getName()
                + ", received by: " + userPair.getReceiver().getName() + ", message: " + message.getMessage());

        this.messageListener.addMessageForUser(userPair.getReceiver(), message);
    }

    @Override
    public void addMessage(Group group, Message message) throws ListenerException {
        GroupConversation groupConversation = this.conversationRepository.retrieveConversation(group);
        groupConversation.addMessage(message);

        System.out.println("Message sent to group: " + group.getName() + ", message: " + message.getMessage());

        this.messageListener.addMessageForGroup(group, message);
    }
}
