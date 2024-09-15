package com.lld.filesystem.model.impl;

import com.lld.filesystem.constant.ObjectType;
import com.lld.filesystem.model.Directory;
import com.lld.filesystem.model.FileSystemObject;
import com.lld.filesystem.model.Metadata;
import com.lld.filesystem.model.strategy.SortingStrategy;

import java.util.ArrayList;
import java.util.List;

public class DirectoryImpl implements Directory {
    private String name;
    private Directory parent;
    private long size;
    private List<FileSystemObject> children;
    private ObjectType type;
    private Metadata metadata;
    public DirectoryImpl(String name, Directory parent) {
        this.name = name;
        this.parent = parent;
        this.type = ObjectType.DIRECTORY;
        this.children = new ArrayList<>();
        this.metadata = new MetadataImpl();

        if (this.parent != null) {
            this.parent.addObject(this);
        }
    }

    @Override
    public void addObject(FileSystemObject object) {
        if (object == null) {
            throw new RuntimeException("Can not add null children.");
        }

        this.children.add(object);

        // Update the current directory size
        updateSize(object.getSize());
        this.metadata.updateModified();
    }

    @Override
    public List<FileSystemObject> getContent() {
        this.metadata.updateLastOpened();
        return this.children;
    }

    @Override
    public void updateSize(long size) {
        this.size += size;
        if (this.parent != null) {
            this.parent.updateSize(size);
        }

        this.metadata.updateModified();
    }

    @Override
    public void sort(SortingStrategy strategy) {
        this.children = strategy.sort(this.children);
        this.metadata.updateModified();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public FileSystemObject getParent() {
        return this.parent;
    }

    @Override
    public long getSize() {
        return this.size;
    }

    @Override
    public ObjectType getType() {
        return this.type;
    }

    @Override
    public void rename(String name) {
        this.name = name;
    }

    @Override
    public Metadata getProperties() {
        return this.metadata;
    }

    @Override
    public String toString() {
        return "DirectoryImpl{" +
                "name='" + getName() + '\'' +
                ", parent=" + getParent() +
                ", size=" + getSize() +
                '}';
    }
}
