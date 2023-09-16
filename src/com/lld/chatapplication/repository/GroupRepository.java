package com.lld.chatapplication.repository;

import com.lld.chatapplication.model.Group;

import java.util.HashMap;
import java.util.Map;

public class GroupRepository {
    private Map<String, Group> groups;

    public GroupRepository() {
        this.groups = new HashMap<>();
    }

    public Group create(Group group) {
        this.groups.putIfAbsent(group.getId(), group);
        return this.groups.get(group.getId());
    }

    public Group get(Group group) {
        return this.groups.get(group.getId());
    }
}
