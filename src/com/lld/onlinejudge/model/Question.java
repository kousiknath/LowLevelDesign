package com.lld.onlinejudge.model;

import java.util.UUID;

public class Question {
    private String id;
    private String title;
    private String description;
    private CommonMetadata metadata;

    public Question(String title, String description, User author) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.metadata = new CommonMetadata(author);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public CommonMetadata getMetadata() {
        return metadata;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }

        if (this == o) {
            return true;
        }

        return this.id.equals(((Question) o).id);
    }

    @Override
    public String toString() {
        return "Title: " + title + ", Description: " + description;
    }
}
