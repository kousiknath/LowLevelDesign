package com.lld.notificationsystem.client;

public interface InstagramClient {
    void sendNotification(String userDetailsEncrypted, String message);
}
