package com.lld.notificationsystem.service.priority.impl;

import com.lld.notificationsystem.service.notifier.impl.BaseNotifier;
import com.lld.notificationsystem.service.priority.PriorityHandler;

public class HighPriorityHandlerImpl implements PriorityHandler {
    @Override
    public void handlePriority(BaseNotifier notifier, String message) {
        // Since it's a high priority message, we will retry sending it 3 times by default.
        // It's just a dummy implementation
        // If still the message pass fails, we can put the message the dead letter queue (DLQ)

        System.out.println("Handling high priority message with notifier: " + notifier.getClass().getSimpleName());

        for (int count = 0; count < 3; count++) {
            try {
                notifier.send(message);
            } catch (Exception ex) {
                System.out.println("Retrying. Times = " + (count + 1));
                continue;
            }

            break;
        }
    }
}
