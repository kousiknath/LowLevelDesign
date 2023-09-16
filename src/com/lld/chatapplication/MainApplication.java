package com.lld.chatapplication;

import com.lld.chatapplication.exception.ListenerException;
import com.lld.chatapplication.model.Group;
import com.lld.chatapplication.service.GroupService;
import com.lld.chatapplication.service.impl.GroupServiceImpl;
import com.lld.chatapplication.service.listener.MessageListener;
import com.lld.chatapplication.service.listener.impl.MessageListenerImpl;
import com.lld.chatapplication.model.Message;
import com.lld.chatapplication.model.UniqueUserContact;
import com.lld.chatapplication.model.User;
import com.lld.chatapplication.repository.ConversationRepository;
import com.lld.chatapplication.repository.UserRepository;
import com.lld.chatapplication.service.ChatService;
import com.lld.chatapplication.service.ConversationService;
import com.lld.chatapplication.service.UserService;
import com.lld.chatapplication.service.impl.ChatServiceImpl;
import com.lld.chatapplication.service.impl.ConversationServiceImpl;
import com.lld.chatapplication.service.impl.UserServiceImpl;
import com.lld.uberdriverdispatch.exception.ServiceException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainApplication {
    private static void testPrivateMessageFlow() throws ListenerException, ServiceException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        UserRepository userRepository = new UserRepository();
        ConversationRepository conversationRepository = new ConversationRepository();

        MessageListener messageListener = new MessageListenerImpl(executorService);
        UserService userService = new UserServiceImpl(userRepository, messageListener);
        ConversationService conversationService = new ConversationServiceImpl(conversationRepository, messageListener);

        User user1 = userService.create("test1", new UniqueUserContact("9090909090", "test1@test.com"));
        User user2 = userService.create("test2", new UniqueUserContact("9090909091", "test2@test.com"));
        userService.addContact(user1, user2);
        userService.addContact(user2, user1);

        ChatService chatService = new ChatServiceImpl(conversationService, userRepository);
        chatService.sendPersonalMessage(user2, new Message(user1, "message - 1"));
        chatService.sendPersonalMessage(user1, new Message(user2, "message - 2"));
    }

    private static void testGroupMessageFlow() throws ListenerException, ServiceException, com.lld.chatapplication.exception.ServiceException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        UserRepository userRepository = new UserRepository();
        ConversationRepository conversationRepository = new ConversationRepository();

        MessageListener messageListener = new MessageListenerImpl(executorService);
        UserService userService = new UserServiceImpl(userRepository, messageListener);
        ConversationService conversationService = new ConversationServiceImpl(conversationRepository, messageListener);

        User user1 = userService.create("test1", new UniqueUserContact("9090909090", "test1@test.com"));
        User user2 = userService.create("test2", new UniqueUserContact("9090909091", "test2@test.com"));
        User user3 = userService.create("test3", new UniqueUserContact("9090909092", "test3@test.com"));
        User user4 = userService.create("test4", new UniqueUserContact("9090909093", "test4@test.com"));
        User user5 = userService.create("test5", new UniqueUserContact("9090909094", "test5@test.com"));

        GroupService groupService = new GroupServiceImpl();
        Group group = groupService.create("group-1", "a test group");
        groupService.joinGroup(group, user1);
        groupService.joinGroup(group, user2);
        groupService.joinGroup(group, user3);
        groupService.joinGroup(group, user4);
        groupService.joinGroup(group, user5);

        System.out.println("Sending a group message ...");
        ChatService chatService = new ChatServiceImpl(conversationService, userRepository);
        chatService.sendGroupMessage(group, new Message(user1, "user 1 sending message to all group members")); // All 5 group
        // members should receive the message

        System.out.println();
        System.out.println("Sending a group message ...");
        groupService.leaveGroup(group, user3);
        groupService.leaveGroup(group, user5);

        // Except user 3 & user 5, all group members should receive the following message
        chatService.sendGroupMessage(group, new Message(user2, "user 2 sending message to all group members"));
    }

    public static void main(String[] args) throws ListenerException, ServiceException, com.lld.chatapplication.exception.ServiceException {
//        testPrivateMessageFlow();
        testGroupMessageFlow();
    }
}
