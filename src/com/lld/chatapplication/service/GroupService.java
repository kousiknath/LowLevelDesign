package com.lld.chatapplication.service;

import com.lld.chatapplication.exception.ServiceException;
import com.lld.chatapplication.model.Group;
import com.lld.chatapplication.model.User;

public interface GroupService {
    Group create(String name, String description);
    void joinGroup(Group group, User user) throws ServiceException;
    void leaveGroup(Group group, User user) throws ServiceException;
}
