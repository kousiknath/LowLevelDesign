package com.lld.notificationsystem.service.impl;

import com.lld.notificationsystem.constant.NotificationChannel;
import com.lld.notificationsystem.factory.NotifierSimpleFactory;
import com.lld.notificationsystem.factory.PriorityHandlerSimpleFactory;
import com.lld.notificationsystem.model.EndUser;
import com.lld.notificationsystem.model.Message;
import com.lld.notificationsystem.repository.EndUserSubscriptionRepository;
import com.lld.notificationsystem.service.NotificationService;
import com.lld.notificationsystem.service.notifier.impl.BaseNotifier;

import java.util.List;

public class NotificationServiceImpl implements NotificationService {
    private EndUserSubscriptionRepository subscriptionRepository;

    public NotificationServiceImpl() {
        this.subscriptionRepository = new EndUserSubscriptionRepository();
    }

    @Override
    public void subscribeToChannels(EndUser endUser, List<NotificationChannel> channels) {
        this.subscriptionRepository.addSubscriptions(endUser, channels);
    }

    @Override
    public void sendMessageTo(EndUser endUser, Message message) {
        List<NotificationChannel> channels = this.subscriptionRepository.getSubscriptions(endUser);
        if (!channels.isEmpty()) {
            BaseNotifier previousNotifier = null;

            for (NotificationChannel channel: channels) {
                previousNotifier = NotifierSimpleFactory.getNotifier(channel, previousNotifier);

                if (previousNotifier != null) {
                    previousNotifier.setPriorityHandler(PriorityHandlerSimpleFactory.getHandler(message.getMessageType()));
                }
            }

            if (previousNotifier != null) {
                previousNotifier.sendNotification(message.getMessage());
            }
        }
    }
}
