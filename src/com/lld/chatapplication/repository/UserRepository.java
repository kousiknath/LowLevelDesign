package com.lld.chatapplication.repository;

import com.lld.chatapplication.model.UniqueUserContact;
import com.lld.chatapplication.model.User;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserRepository {
    private final Map<UniqueUserContact, User> users;
    private final Map<User, Set<User>> contacts;

    public UserRepository() {
        this.users = new HashMap<>();
        this.contacts = new HashMap<>();
    }

    public User getUser(UniqueUserContact userContact) {
        return users.get(userContact);
    }

    public User createUser(User user) {
        this.users.putIfAbsent(user.getUniqueUserContact(), user);
        return this.users.get(user.getUniqueUserContact());
    }

    public void addContact(User user, User contact) {
        this.contacts.putIfAbsent(user, new HashSet<>());
        this.contacts.get(user).add(contact);
    }

    public void removeContact(User user, User contact) {
        if (this.contacts.containsKey(user)) {
            this.contacts.get(user).remove(contact);
        }
    }

    public Set<User> getContacts(User user) {
        return this.contacts.getOrDefault(user, new HashSet<>());
    }
}
