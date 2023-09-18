package com.lld.notificationsystem;

import com.lld.notificationsystem.constant.MessageType;
import com.lld.notificationsystem.constant.NotificationChannel;
import com.lld.notificationsystem.model.EndUser;
import com.lld.notificationsystem.model.Message;
import com.lld.notificationsystem.model.Template;
import com.lld.notificationsystem.service.MessageTemplateService;
import com.lld.notificationsystem.service.NotificationService;
import com.lld.notificationsystem.service.impl.MessageTemplateServiceImpl;
import com.lld.notificationsystem.service.impl.NotificationServiceImpl;

import java.util.Arrays;

public class MainApplication {

    private static void test1() {
        MessageTemplateService messageTemplateService = new MessageTemplateServiceImpl();

        // Template creation is one time activity
        String strTemplate = "Get straight %s rupee discount on ABC today. Special offer.";
        Template template = messageTemplateService.createTemplate(MessageType.INFORMATIONAL, strTemplate);

        String formattedMessage = String.format(template.getTemplate(), "100");
        EndUser endUser = new EndUser("test1", "test1@test.com");

        NotificationService notificationService = new NotificationServiceImpl();
        notificationService.subscribeToChannels(endUser,
                Arrays.asList(NotificationChannel.WHATSAPP, NotificationChannel.FACEBOOK, NotificationChannel.INSTAGRAM));
        notificationService.sendMessageTo(endUser, new Message(formattedMessage, template.getMessageType()));
    }

    private static void test2() {
        MessageTemplateService messageTemplateService = new MessageTemplateServiceImpl();

        // Template creation is one time activity
        String strTemplate = "Your OTP for transaction amount of INR %s is %s";
        Template template = messageTemplateService.createTemplate(MessageType.OTP, strTemplate);

        String formattedMessage = String.format(template.getTemplate(), "100000", "6839");
        EndUser endUser = new EndUser("test2", "test2@test.com");

        NotificationService notificationService = new NotificationServiceImpl();
        notificationService.subscribeToChannels(endUser,
                Arrays.asList(NotificationChannel.SMS, NotificationChannel.EMAIL));
        notificationService.sendMessageTo(endUser, new Message(formattedMessage, template.getMessageType()));
    }

    public static void main(String[] args) {
        test1();
        test2();
    }
}
