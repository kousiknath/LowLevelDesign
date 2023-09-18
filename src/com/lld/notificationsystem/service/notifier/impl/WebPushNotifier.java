package com.lld.notificationsystem.service.notifier.impl;

import com.lld.notificationsystem.client.WebPushClient;
import com.lld.notificationsystem.client.impl.WebPushClientImpl;

public class WebPushNotifier extends BaseNotifier {
    private WebPushClient webPushClient;

    public WebPushNotifier(BaseNotifier notifier) {
        this.nextNotifier = notifier;
        this.webPushClient = new WebPushClientImpl();
    }

    @Override
    public void send(String message) {
        this.webPushClient.sendNotification("", message);
    }
}
