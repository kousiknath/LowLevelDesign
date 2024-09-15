package com.lld.filesystem.model;

import com.lld.filesystem.constant.ObjectType;

public interface FileSystemObject {
    String getName();
    FileSystemObject getParent();
    // size is expressed in bytes
    long getSize();
    ObjectType getType();
    void rename(String name);
    Metadata getProperties();
}
