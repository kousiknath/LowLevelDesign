package lld.kafka.model;

import java.util.UUID;

public class Message {
    String id;
    String message;

    public Message(String msg) {
        this.id = UUID.randomUUID().toString();
        this.message = msg;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
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

        if (o == this) {
            return true;
        }

        Message other = (Message) o;
        return this.id.equals(other.id);
    }

    @Override
    public String toString() {
        return this.message;
    }
}
