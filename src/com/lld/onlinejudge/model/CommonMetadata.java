package com.lld.onlinejudge.model;

import java.time.Instant;

public class CommonMetadata {
    private User author;
    private Instant creationTime;
    private Instant lastUpdateTime;

    public CommonMetadata(User author) {
        this.author = author;
        this.creationTime = Instant.now();
        this.lastUpdateTime = Instant.now();
    }

    public User getAuthor() {
        return author;
    }

    public Instant getCreationTime() {
        return creationTime;
    }

    public Instant getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime() {
        this.lastUpdateTime = Instant.now();
    }
}
