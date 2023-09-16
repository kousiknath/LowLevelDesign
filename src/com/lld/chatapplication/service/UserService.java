package com.lld.chatapplication.service;

import com.lld.chatapplication.model.*;

public interface UserService {
    IndividualUser create(String name, UniqueUserContact uniqueUserContact);
    BusinessUser createBusinessUser(String name, UniqueUserContact uniqueUserContact, Business business);
    void addContact(User user, User contact);
    void removeContact(User user, User contact);
}
