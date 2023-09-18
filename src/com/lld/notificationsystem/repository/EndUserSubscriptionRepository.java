package com.lld.notificationsystem.repository;

import com.lld.notificationsystem.constant.NotificationChannel;
import com.lld.notificationsystem.model.EndUser;

import java.util.*;

public class EndUserSubscriptionRepository {
    private Map<EndUser, List<NotificationChannel>> endUserSubscriptions;

    public EndUserSubscriptionRepository() {
        this.endUserSubscriptions = new HashMap<>();
    }

    public void addSubscriptions(EndUser endUser, List<NotificationChannel> channels) {
        this.endUserSubscriptions.putIfAbsent(endUser, new ArrayList<>());
        this.endUserSubscriptions.get(endUser).addAll(channels);
    }

    public List<NotificationChannel> getSubscriptions(EndUser endUser) {
        return this.endUserSubscriptions.getOrDefault(endUser, Collections.emptyList());
    }
}
