package com.lld.filesystem.model.strategy.impl;

import com.lld.filesystem.model.FileSystemObject;
import com.lld.filesystem.model.strategy.SortingStrategy;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SizeBasedSortingStrategy implements SortingStrategy {
    @Override
    public List<FileSystemObject> sort(List<FileSystemObject> objects) {
        if (objects == null || objects.isEmpty()) {
            return Collections.emptyList();
        }

        objects.sort(Comparator.comparingLong(FileSystemObject::getSize)
                .thenComparing(FileSystemObject::getType));

        return objects;
    }
}
