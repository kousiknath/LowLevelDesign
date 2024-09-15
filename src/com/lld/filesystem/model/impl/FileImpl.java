package com.lld.filesystem.model.impl;

import com.lld.filesystem.constant.ObjectType;
import com.lld.filesystem.model.Directory;
import com.lld.filesystem.model.File;
import com.lld.filesystem.model.FileSystemObject;
import com.lld.filesystem.model.Metadata;

import java.util.Objects;
import java.util.Random;

public class FileImpl implements File {
    private String name;
    private String extension;
    private ObjectType type;
    private Directory parent;
    private long size;
    private byte[] content;
    private Metadata metadata;
    public FileImpl(String name, String extension, Directory parent) {
        Objects.requireNonNull(parent);

        this.extension = extension;
        this.name = name + "." + extension;
        this.type = ObjectType.FILE;
        this.parent = parent;
        this.size = 1000 + new Random().nextLong(10000); // Random file size
        this.parent.addObject(this);
        this.content = new byte[0];
        this.metadata = new MetadataImpl();
    }

    @Override
    public void addContent(byte[] bytes) {
        this.content = bytes;
        this.metadata.updateModified();
    }

    @Override
    public byte[] getContent() {
        this.metadata.updateLastOpened();
        return this.content;
    }

    @Override
    public String getExtension() {
        return this.extension;
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
        return "FileImpl{" +
                "name='" + getName() + '\'' +
                ", extension='" + getExtension() + '\'' +
                ", parent=" + getParent().getName() +
                ", size=" + getSize() +
                '}';
    }
}
