package com.lld.filesystem.service.impl;

import com.lld.filesystem.constant.DirectorySortingFilter;
import com.lld.filesystem.model.Directory;
import com.lld.filesystem.model.File;
import com.lld.filesystem.model.FileSystemObject;
import com.lld.filesystem.model.impl.DirectoryImpl;
import com.lld.filesystem.model.impl.FileImpl;
import com.lld.filesystem.model.strategy.impl.FileTypeBasedSortingStrategy;
import com.lld.filesystem.model.strategy.impl.NameBasedSortingStrategy;
import com.lld.filesystem.model.strategy.impl.SizeBasedSortingStrategy;
import com.lld.filesystem.service.FileSystemUserInterface;

import javax.naming.OperationNotSupportedException;
import java.util.List;

public class FileSystemUserInterfaceImpl implements FileSystemUserInterface {
    @Override
    public Directory createDirectory(String name, Directory parent) {
        return new DirectoryImpl(name, parent);
    }

    @Override
    public File createFile(String name, String ext, Directory parent) {
        return new FileImpl(name, ext, parent);
    }

    @Override
    public Directory sortDirectory(Directory directory, DirectorySortingFilter sortBy) throws OperationNotSupportedException {
        if (sortBy == DirectorySortingFilter.BY_NAME) {
            directory.sort(new NameBasedSortingStrategy());
            return directory;
        }

        if (sortBy == DirectorySortingFilter.BY_SIZE) {
            directory.sort(new SizeBasedSortingStrategy());
            return directory;
        }

        if (sortBy == DirectorySortingFilter.BY_TYPE) {
            directory.sort(new FileTypeBasedSortingStrategy());
            return directory;
        }

        throw new OperationNotSupportedException("Could not sort directory");
    }

    @Override
    public List<FileSystemObject> getChildren(Directory directory) {
        return directory.getContent();
    }

    @Override
    public Long getSize(FileSystemObject object) {
        return object.getSize();
    }
}
