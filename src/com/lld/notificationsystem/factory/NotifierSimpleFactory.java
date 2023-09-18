package com.lld.notificationsystem.factory;

import com.lld.notificationsystem.constant.NotificationChannel;
import com.lld.notificationsystem.service.notifier.impl.*;

public class NotifierSimpleFactory {
    public static BaseNotifier getNotifier(NotificationChannel channel, BaseNotifier notifier) {
        switch (channel) {
            case SMS -> {
                return new SMSNotifier(notifier);
            }

            case EMAIL -> {
                return new EmailNotifier(notifier);
            }

            case SLACK -> {
                return new SlackNotifier(notifier);
            }

            case FACEBOOK -> {
                return new FacebookNotifier(notifier);
            }

            case WEB_PUSH -> {
                return new WebPushNotifier(notifier);
            }

            case WHATSAPP -> {
                return new WhatsappNotifier(notifier);
            }

            case INSTAGRAM -> {
                return new InstagramNotifier(notifier);
            }

            case MOBILE_PUSH -> {
                return new MobilePushNotifier(notifier);
            }

            default -> {
                return null;
            }
        }
    }
}
