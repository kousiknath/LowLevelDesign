package com.lld.notificationsystem.client;

public interface MobilePushNotifierClient {
    void sendPush(String deviceToken, String message);
}
