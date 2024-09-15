package com.lld.filesystem.model.impl;

import com.lld.filesystem.model.Metadata;

import java.time.LocalDateTime;

public class MetadataImpl implements Metadata {
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastOpened;

    public MetadataImpl() {
        this.created = LocalDateTime.now();
        this.modified = LocalDateTime.now();
        this.lastOpened = LocalDateTime.now();
    }

    @Override
    public LocalDateTime created() {
        return this.created;
    }

    @Override
    public LocalDateTime modified() {
        return this.modified;
    }

    @Override
    public LocalDateTime lastOpened() {
        return this.lastOpened;
    }

    @Override
    public void updateModified() {
        this.modified = LocalDateTime.now();
    }

    @Override
    public void updateLastOpened() {
        this.lastOpened = LocalDateTime.now();
    }

}
