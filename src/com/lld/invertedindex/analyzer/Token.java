package com.lld.invertedindex.analyzer;

import java.util.List;
import java.util.Set;

public class Token<K extends Comparable<K>, V extends Comparable<V>> {
    private K key;
    private Set<V> values;
    public Token(K key, Set<V> values) {
        this.key = key;
        this.values = values;
    }

    public K getKey() {
        return key;
    }

    public Set<V> getValues() {
        return values;
    }
}
