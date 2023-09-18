package com.lld.notificationsystem.service.notifier.impl;

import com.lld.notificationsystem.client.FacebookClient;
import com.lld.notificationsystem.client.impl.FacebookClientImpl;

public class FacebookNotifier extends BaseNotifier {
    private FacebookClient facebookClient;

    public FacebookNotifier(BaseNotifier notifier) {
        this.nextNotifier = notifier;
        this.facebookClient = new FacebookClientImpl();
    }

    @Override
    public void send(String message) {
        this.facebookClient.sendNotification("", message);
    }
}
