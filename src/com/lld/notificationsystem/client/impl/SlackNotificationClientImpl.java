package com.lld.notificationsystem.client.impl;

import com.lld.notificationsystem.client.SlackNotificationClient;

public class SlackNotificationClientImpl implements SlackNotificationClient {
    @Override
    public void sendSlack(String userDetailsEncrypted, String message) {
        System.out.println("Sent slack notification");
    }
}
