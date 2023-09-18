package com.lld.notificationsystem.service.notifier.impl;

import com.lld.notificationsystem.service.priority.PriorityHandler;

/*
    This notifier does not send any notification. It just starts the chain of other notifiers
    as per the end user subscription.

    We will use Chain of Responsibility pattern to model sending notifications through different notifiers.
 */
public abstract class BaseNotifier {
    protected BaseNotifier nextNotifier;
    protected PriorityHandler priorityHandler;

    public void setPriorityHandler(PriorityHandler priorityHandler) {
        this.priorityHandler = priorityHandler;
    }

    public void sendNotification(String message) {
        if (this.priorityHandler != null) {
            this.priorityHandler.handlePriority(this, message);
        } else {
            send(message);
        }

        if (this.nextNotifier != null) {
            this.nextNotifier.sendNotification(message);
        }
    }

    public abstract void send(String message);
}
