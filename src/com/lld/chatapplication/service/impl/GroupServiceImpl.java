package com.lld.chatapplication.service.impl;

import com.lld.chatapplication.exception.ServiceException;
import com.lld.chatapplication.model.Group;
import com.lld.chatapplication.model.User;
import com.lld.chatapplication.repository.GroupRepository;
import com.lld.chatapplication.service.GroupService;

public class GroupServiceImpl implements GroupService {
    private GroupRepository groupRepository;

    public GroupServiceImpl() {
        this.groupRepository = new GroupRepository();
    }

    @Override
    public Group create(String name, String description) {
        Group group = new Group(name, description);
        return this.groupRepository.create(group);
    }

    @Override
    public void joinGroup(Group group, User user) throws ServiceException {
        Group existingGroup = this.groupRepository.get(group);
        if (existingGroup == null) {
            throw new ServiceException("Group could not be found");
        }

        existingGroup.addParticipant(user);
    }

    @Override
    public void leaveGroup(Group group, User user) throws ServiceException {
        Group existingGroup = this.groupRepository.get(group);
        if (existingGroup == null) {
            throw new ServiceException("Group could not be found");
        }

        existingGroup.removeParticipant(user);
    }
}
