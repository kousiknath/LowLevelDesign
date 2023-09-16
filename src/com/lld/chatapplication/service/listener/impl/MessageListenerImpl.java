package com.lld.chatapplication.service.listener.impl;

import com.lld.chatapplication.exception.ListenerException;
import com.lld.chatapplication.model.Group;
import com.lld.chatapplication.model.Message;
import com.lld.chatapplication.model.User;
import com.lld.chatapplication.service.listener.MessageListener;
import com.lld.chatapplication.service.listener.UserListener;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;


public class MessageListenerImpl implements MessageListener {
    private Map<User, BlockingQueue<Message>> userQueues;
    private Map<User, UserListener> userListeners;
    private ExecutorService executorService;

    public MessageListenerImpl(ExecutorService executorService) {
        this.userQueues = new HashMap<>();
        this.userListeners = new HashMap<>();
        this.executorService = executorService;
    }

    public void registerForUser(User user) {
        this.userQueues.putIfAbsent(user, new LinkedBlockingQueue<>());
        UserListener userListener = new UserListener(user, this.userQueues.get(user));
        this.userListeners.putIfAbsent(user, userListener);

        this.executorService.execute(userListener);
    }

    public boolean addMessageForUser(User user, Message message) throws ListenerException {
        if (!userQueues.containsKey(user)) {
            throw new ListenerException("No listener found for the user");
        }

        return this.userQueues.get(user).add(message);
    }

    @Override
    public boolean addMessageForGroup(Group group, Message message) throws ListenerException {
        Set<User> participants = group.getParticipants();
        for (User participant: participants) {
            if (!userQueues.containsKey(participant)) {
                throw new ListenerException("No listener found for the user");
            }

            this.userQueues.get(participant).add(message);
        }

        return true;
    }

}
