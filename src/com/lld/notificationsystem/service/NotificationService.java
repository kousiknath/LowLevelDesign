package com.lld.notificationsystem.service;

import com.lld.notificationsystem.constant.NotificationChannel;
import com.lld.notificationsystem.model.EndUser;
import com.lld.notificationsystem.model.Message;

import java.util.List;

public interface NotificationService {
    // End users like me, and you can subscribe to specific channels
    void subscribeToChannels(EndUser endUser, List<NotificationChannel> channels);

    // Send message to end user, internally figure out what all channels to use for sending
    // the notification
    void sendMessageTo(EndUser endUser, Message message);
}
