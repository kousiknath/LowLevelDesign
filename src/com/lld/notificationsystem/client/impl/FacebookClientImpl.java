package com.lld.notificationsystem.client.impl;

import com.lld.notificationsystem.client.FacebookClient;

public class FacebookClientImpl implements FacebookClient {
    @Override
    public void sendNotification(String userDetailsEncrypted, String message) {
        // Integrate with facebook notification API
        System.out.println("Sent facebook notification");
    }
}
