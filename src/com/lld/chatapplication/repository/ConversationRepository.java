package com.lld.chatapplication.repository;

import com.lld.chatapplication.model.Group;
import com.lld.chatapplication.model.GroupConversation;
import com.lld.chatapplication.model.PrivateConversation;
import com.lld.chatapplication.model.UserPair;

import java.util.HashMap;
import java.util.Map;

public class ConversationRepository {
    private Map<Group, GroupConversation> groupConversations;
    private Map<UserPair, PrivateConversation> privateConversations;

    public ConversationRepository() {
        this.groupConversations = new HashMap<>();
        this.privateConversations = new HashMap<>();
    }

    public PrivateConversation retrieveConversation(UserPair userPair) {
        this.privateConversations.putIfAbsent(userPair, new PrivateConversation(userPair));
        return this.privateConversations.get(userPair);
    }

    public GroupConversation retrieveConversation(Group group) {
        this.groupConversations.putIfAbsent(group, new GroupConversation(group));
        return this.groupConversations.get(group);
    }
}
