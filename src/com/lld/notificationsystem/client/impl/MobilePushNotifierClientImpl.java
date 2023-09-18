package com.lld.notificationsystem.client.impl;

import com.lld.notificationsystem.client.MobilePushNotifierClient;

public class MobilePushNotifierClientImpl implements MobilePushNotifierClient {
    @Override
    public void sendPush(String deviceToken, String message) {
        System.out.println("Mobile push notification sent");
    }
}
