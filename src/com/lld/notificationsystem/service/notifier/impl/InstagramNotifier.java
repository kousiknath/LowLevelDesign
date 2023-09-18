package com.lld.notificationsystem.service.notifier.impl;

import com.lld.notificationsystem.client.InstagramClient;
import com.lld.notificationsystem.client.impl.InstagramClientImpl;

public class InstagramNotifier extends BaseNotifier {
    private InstagramClient instagramClient;

    public InstagramNotifier(BaseNotifier notifier) {
        this.nextNotifier = notifier;
        this.instagramClient = new InstagramClientImpl();
    }


    @Override
    public void send(String message) {
        this.instagramClient.sendNotification("", message);
    }
}
