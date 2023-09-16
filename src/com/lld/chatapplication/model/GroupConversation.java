package com.lld.chatapplication.model;

/*
    A journal of conversations among group members
 */
public class GroupConversation extends Conversation {
    private Group group; // Group and GroupConversation have 1:1 mapping

    public GroupConversation(Group group) {
        this.group = group;
    }

    @Override
    public int hashCode() {
        return this.group.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        if (this == o) {
            return true;
        }

        return this.group.equals(((GroupConversation) o).group);
    }
}
