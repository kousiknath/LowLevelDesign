package com.lld.invertedindex.document;

import java.util.Objects;

public class Document {
    private int id;
    private String content;

    public Document(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Document document)) return false;
        return getId() == document.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", content=" + content +
                '}';
    }
}
