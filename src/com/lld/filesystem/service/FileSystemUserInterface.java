package com.lld.filesystem.service;

import com.lld.filesystem.constant.DirectorySortingFilter;
import com.lld.filesystem.model.Directory;
import com.lld.filesystem.model.File;
import com.lld.filesystem.model.FileSystemObject;

import javax.naming.OperationNotSupportedException;
import java.util.List;

public interface FileSystemUserInterface {
    Directory createDirectory(String name, Directory parent);
    File createFile(String name, String extension, Directory parent);
    Directory sortDirectory(Directory directory, DirectorySortingFilter sortBy) throws OperationNotSupportedException;

    List<FileSystemObject> getChildren(Directory directory);
    Long getSize(FileSystemObject object);
}
