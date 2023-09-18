package com.lld.notificationsystem.service.notifier.impl;

import com.lld.notificationsystem.client.WhatsappClient;
import com.lld.notificationsystem.client.impl.WhatsappClientImpl;

public class WhatsappNotifier extends BaseNotifier {
    private WhatsappClient whatsappClient;

    public WhatsappNotifier(BaseNotifier notifier) {
        this.nextNotifier = notifier;
        this.whatsappClient = new WhatsappClientImpl();
    }

    @Override
    public void send(String message) {
        this.whatsappClient.sendNotification("", message);
    }
}
