package com.lld.amazon.model;

public class KV {
    private String key;
    private Object val;

    public KV(String key, Object val) {
        this.key = key;
        this.val = val;
    }

    public String getKey() {
        return key;
    }

    public Object getVal() {
        return val;
    }
}
