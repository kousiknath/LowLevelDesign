package com.lld.filesystem;

import com.lld.filesystem.constant.DirectorySortingFilter;
import com.lld.filesystem.model.Directory;
import com.lld.filesystem.model.File;
import com.lld.filesystem.model.FileSystemObject;
import com.lld.filesystem.service.FileSystemUserInterface;
import com.lld.filesystem.service.impl.FileSystemUserInterfaceImpl;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;


public class Main {
    private static void test1() throws OperationNotSupportedException {
        FileSystemUserInterface fileSystem = new FileSystemUserInterfaceImpl();
        Directory root = fileSystem.createDirectory("root", null);

        File rootFile1 = fileSystem.createFile("root:file 1", "pdf", root);
        System.out.println("root:file 1 size = " + rootFile1.getSize());

        System.out.println("After adding root file 1, root size: " + root.getSize());

        Directory dir1 = fileSystem.createDirectory("dir 1", root);
        Directory dir2 = fileSystem.createDirectory("dir 2", root);
        Directory dir3 = fileSystem.createDirectory("dir 3", dir2); // root -> children 2 -> children 3

        File dir3File1 = fileSystem.createFile("dir 3:file 1", "mp4", dir3);
        System.out.println("dir3File1 size = " + dir3File1.getSize());

        System.out.println("Size of directory 3 after adding dir 3:file 1 = " + dir3.getSize());
        System.out.println("Size of directory 2 after adding dir 3:file 1 = " + dir2.getSize());
        System.out.println("Size of root after adding dir 3:file 1 " + root.getSize());

        File dir2File1 = fileSystem.createFile("dir 2:file 1", "parquet", dir2);
        System.out.println("dir2File1 size = " + dir2File1.getSize());

        System.out.println("Size of directory 2 after adding dir 2:file 1 = " + dir2.getSize());
        System.out.println("Size of root after adding dir 2:file 1 " + root.getSize());

        System.out.println("Size of directory 1 = " + dir1.getSize()); // Should be 0 since no object is added yet

        System.out.println("Sorting the root directory based on size");
        Directory sortedRoot = fileSystem.sortDirectory(root, DirectorySortingFilter.BY_SIZE);
        System.out.println(Arrays.deepToString(sortedRoot.getContent().toArray(new FileSystemObject[0])));
    }

    public static void main(String[] args) throws OperationNotSupportedException {
        test1();
    }
}
