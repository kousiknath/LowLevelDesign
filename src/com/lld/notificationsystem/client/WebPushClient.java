package com.lld.notificationsystem.client;

public interface WebPushClient {
    void sendNotification(String userDetailsEncrypted, String message);
}
