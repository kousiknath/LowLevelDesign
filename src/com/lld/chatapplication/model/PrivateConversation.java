package com.lld.chatapplication.model;

/*
    A journal of private conversations between two users
 */
public class PrivateConversation extends Conversation {
    private final UserPair userPair;

    public PrivateConversation(UserPair userPair) {
        this.userPair = userPair;
    }

    public UserPair getUserPair() {
        return this.userPair;
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        if (this == o) {
            return true;
        }

        return this.userPair.equals((((PrivateConversation) o).userPair));
    }
}
