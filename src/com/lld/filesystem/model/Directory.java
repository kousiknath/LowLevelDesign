package com.lld.filesystem.model;

import com.lld.filesystem.model.strategy.SortingStrategy;

import java.util.List;

public interface Directory extends FileSystemObject {
    void addObject(FileSystemObject object);
    List<FileSystemObject> getContent();
    void updateSize(long size);
    void sort(SortingStrategy strategy);
}
