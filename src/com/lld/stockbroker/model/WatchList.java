package com.lld.stockbroker.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WatchList {
    private List<Entry> entries;

    public WatchList() {
        this.entries = new ArrayList<>();
    }

    public void addEntry(Entry entry) {
        this.entries.add(entry);
    }

    @Override
    public String toString() {
        return Arrays.deepToString(entries.toArray());
    }
}
