package com.lld.notificationsystem.client.impl;

import com.lld.notificationsystem.client.EmailClient;

public class EmailClientImpl implements EmailClient  {
    @Override
    public void sendEmail(String address, String cc, String bcc, String content) {
        // Integrate with external email service
        System.out.println("Sent email message");
    }
}
