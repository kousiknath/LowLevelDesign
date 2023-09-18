package com.lld.notificationsystem.service.notifier.impl;

import com.lld.notificationsystem.client.MobilePushNotifierClient;
import com.lld.notificationsystem.client.impl.MobilePushNotifierClientImpl;

public class MobilePushNotifier extends BaseNotifier {
    private MobilePushNotifierClient mobilePushNotifierClient;

    public MobilePushNotifier(BaseNotifier notifier) {
        this.nextNotifier = notifier;
        this.mobilePushNotifierClient = new MobilePushNotifierClientImpl();
    }

    @Override
    public void send(String message) {
        this.mobilePushNotifierClient.sendPush("", message);
    }
}
