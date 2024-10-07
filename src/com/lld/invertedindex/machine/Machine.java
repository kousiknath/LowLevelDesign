package com.lld.invertedindex.machine;

import java.util.Objects;

public class Machine {
    private int id;

    public Machine(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Machine machine)) return false;
        return getId() == machine.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
