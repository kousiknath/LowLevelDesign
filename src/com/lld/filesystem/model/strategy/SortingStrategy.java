package com.lld.filesystem.model.strategy;

import com.lld.filesystem.model.FileSystemObject;

import java.util.List;

public interface SortingStrategy {
    List<FileSystemObject> sort(List<FileSystemObject> objects);
}
