package com.lld.notificationsystem.service.notifier.impl;

import com.lld.notificationsystem.client.SlackNotificationClient;
import com.lld.notificationsystem.client.impl.SlackNotificationClientImpl;

public class SlackNotifier extends BaseNotifier {
    private SlackNotificationClient slackNotificationClient;

    public SlackNotifier(BaseNotifier notifier) {
        this.nextNotifier = notifier;
        this.slackNotificationClient = new SlackNotificationClientImpl();
    }

    @Override
    public void send(String message) {
        this.slackNotificationClient.sendSlack("", message);
    }
}
