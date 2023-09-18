package com.lld.notificationsystem.service.priority;

import com.lld.notificationsystem.service.notifier.impl.BaseNotifier;

public interface PriorityHandler {
    void handlePriority(BaseNotifier notifier, String message);
}
