package com.lld.notificationsystem.service.notifier.impl;

import com.lld.notificationsystem.client.EmailClient;
import com.lld.notificationsystem.client.impl.EmailClientImpl;

public class EmailNotifier extends BaseNotifier {
    private EmailClient emailClient;

    public EmailNotifier(BaseNotifier notifier) {
        this.nextNotifier = notifier;
        this.emailClient = new EmailClientImpl();
    }

    @Override
    public void send(String message) {
        this.emailClient.sendEmail("", "", "", message);
    }
}
