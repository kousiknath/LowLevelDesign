package com.lld.chatapplication.service.impl;

import com.lld.chatapplication.service.listener.MessageListener;
import com.lld.chatapplication.model.*;
import com.lld.chatapplication.repository.UserRepository;
import com.lld.chatapplication.service.UserService;

public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private MessageListener messageListener;

    public UserServiceImpl(UserRepository userRepository, MessageListener messageListener) {
        this.userRepository = userRepository;
        this.messageListener = messageListener;
    }

    @Override
    public IndividualUser create(String name, UniqueUserContact userContact) {
        if (this.userRepository.getUser(userContact) != null) {
            return (IndividualUser) this.userRepository.getUser(userContact);
        }

        IndividualUser user = new IndividualUser(name, userContact);
        user = (IndividualUser) this.userRepository.createUser(user);

        // register listener for the user
        this.messageListener.registerForUser(user);
        return user;
    }

    @Override
    public BusinessUser createBusinessUser(String name, UniqueUserContact userContact, Business business) {
        if (this.userRepository.getUser(userContact) != null) {
            return (BusinessUser) this.userRepository.getUser(userContact);
        }

        BusinessUser user = new BusinessUser(name, userContact, business);
        user = (BusinessUser) this.userRepository.createUser(user);

        // register listener for the user
        this.messageListener.registerForUser(user);
        return user;
    }

    @Override
    public void addContact(User user, User contact) {
        this.userRepository.addContact(user, contact);
    }

    @Override
    public void removeContact(User user, User contact) {
        this.userRepository.removeContact(user, contact);
    }
}
