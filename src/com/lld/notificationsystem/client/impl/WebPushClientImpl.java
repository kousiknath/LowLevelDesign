package com.lld.notificationsystem.client.impl;

import com.lld.notificationsystem.client.WebPushClient;

public class WebPushClientImpl implements WebPushClient {
    @Override
    public void sendNotification(String userDetailsEncrypted, String message) {
        System.out.println("Web push notification sent");
    }
}
