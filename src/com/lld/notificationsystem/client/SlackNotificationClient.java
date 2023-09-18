package com.lld.notificationsystem.client;

public interface SlackNotificationClient {
    void sendSlack(String userDetailsEncrypted, String message);
}
