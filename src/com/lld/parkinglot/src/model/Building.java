package com.lld.parkinglot.src.model;

import com.lld.parkinglot.src.config.Config;

import java.util.ArrayList;
import java.util.List;

public class Building {
    private String name; // Assumed to be unique name just to identify the building
    private int numLevels;
    private List<Level> levels;
    private List<Entry> entries;
    private List<Exit> exits;

    public Building(String name, int numLevels, Entry entry, Exit exit) {
        this.name = name;
        this.numLevels = numLevels;

        this.levels = new ArrayList<>();
        this.entries = new ArrayList<>();
        this.exits = new ArrayList<>();

        addEntry(entry);
        addExit(exit);
    }

    public boolean addLevel(Level level) {
        if (this.levels.size() < Math.min(numLevels, Config.MAXIMUM_LEVELS_PER_BUILDING)) {
            this.levels.add(level);
            return true;
        }

        return false;
    }

    public void addEntry(Entry entry) {
        this.entries.add(entry);
    }

    public void addExit(Exit exit) {
        this.exits.add(exit);
    }

    public String getName() {
        return name;
    }

    public List<Level> getLevels() {
        return this.levels;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public List<Exit> getExits() {
        return exits;
    }
}
