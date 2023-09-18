package com.lld.notificationsystem.client.impl;

import com.lld.notificationsystem.client.SMSClient;

public class SMSClientImpl implements SMSClient {
    @Override
    public void sendSMS(String phoneNo, String message) {
        throw new RuntimeException("SMS server down"); // Simulating failure
    }
}
