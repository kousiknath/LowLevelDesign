package com.lld.notificationsystem.service;

import com.lld.notificationsystem.constant.MessageType;
import com.lld.notificationsystem.model.Template;

public interface MessageTemplateService {
    Template createTemplate(MessageType messageType, String template);
    Template getTemplate(String templateId);
}
