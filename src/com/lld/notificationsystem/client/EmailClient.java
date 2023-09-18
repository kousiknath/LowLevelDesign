package com.lld.notificationsystem.client;

public interface EmailClient {
    void sendEmail(String address, String cc, String bcc, String content);
}
