package com.lld.notificationsystem.client.impl;

import com.lld.notificationsystem.client.InstagramClient;

public class InstagramClientImpl implements InstagramClient {
    @Override
    public void sendNotification(String userDetailsEncrypted, String message) {
        System.out.println("Sent Instagram notification");
    }
}
