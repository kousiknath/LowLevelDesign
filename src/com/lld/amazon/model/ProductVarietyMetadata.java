package com.lld.amazon.model;

import java.util.ArrayList;
import java.util.List;

public class ProductVarietyMetadata {
    private List<KV> metadata;

    public ProductVarietyMetadata() {
        this.metadata = new ArrayList<>();
    }

    public void addMetadata(String key, String value) {
        this.metadata.add(new KV(key, value));
    }
}
