package com.lld.filesystem.model;

import java.time.LocalDateTime;

public interface Metadata {
    LocalDateTime created();
    LocalDateTime modified();
    LocalDateTime lastOpened();

    void updateModified();
    void updateLastOpened();
}
