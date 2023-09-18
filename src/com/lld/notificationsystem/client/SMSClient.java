package com.lld.notificationsystem.client;

public interface SMSClient {
    void sendSMS(String phoneNo, String message);
}
