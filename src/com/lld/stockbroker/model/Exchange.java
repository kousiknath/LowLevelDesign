package com.lld.stockbroker.model;

import java.util.Objects;

public class Exchange {
    private String name;

    public Exchange(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Exchange exchange)) return false;
        return Objects.equals(getName(), exchange.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public String toString() {
        return "Exchange{" +
                "name='" + name + '\'' +
                '}';
    }
}
