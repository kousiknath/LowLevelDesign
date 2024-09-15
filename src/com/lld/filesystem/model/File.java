package com.lld.filesystem.model;

public interface File extends FileSystemObject {
    void addContent(byte[] bytes);
    byte[] getContent();
    String getExtension();
}
