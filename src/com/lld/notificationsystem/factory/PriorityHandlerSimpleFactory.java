package com.lld.notificationsystem.factory;

import com.lld.notificationsystem.constant.MessageType;
import com.lld.notificationsystem.service.priority.PriorityHandler;
import com.lld.notificationsystem.service.priority.impl.HighPriorityHandlerImpl;

import java.util.Objects;

public class PriorityHandlerSimpleFactory {
    public static PriorityHandler getHandler(MessageType messageType) {
        if (Objects.requireNonNull(messageType) == MessageType.OTP) {
            return new HighPriorityHandlerImpl();
        }

        return null; // No priority handler needed
    }
}
