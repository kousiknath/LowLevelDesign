package com.lld.notificationsystem.client;

public interface FacebookClient {
    void sendNotification(String userDetailsEncrypted, String message);
}
