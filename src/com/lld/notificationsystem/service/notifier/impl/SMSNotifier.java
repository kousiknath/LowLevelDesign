package com.lld.notificationsystem.service.notifier.impl;

import com.lld.notificationsystem.client.SMSClient;
import com.lld.notificationsystem.client.impl.SMSClientImpl;

public class SMSNotifier extends BaseNotifier {
    private SMSClient smsClient;

    public SMSNotifier(BaseNotifier notifier) {
        this.nextNotifier = notifier;
        this.smsClient = new SMSClientImpl();
    }

    @Override
    public void send(String message) {
        this.smsClient.sendSMS("", message);
    }
}
