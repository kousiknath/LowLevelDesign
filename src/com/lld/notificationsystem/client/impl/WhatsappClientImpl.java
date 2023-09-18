package com.lld.notificationsystem.client.impl;

import com.lld.notificationsystem.client.WhatsappClient;

public class WhatsappClientImpl implements WhatsappClient {
    @Override
    public void sendNotification(String phoneNo, String message) {
        System.out.println("Sent Whatsapp notification");
    }
}
