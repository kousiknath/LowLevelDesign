package com.lld.notificationsystem.service.impl;

import com.lld.notificationsystem.constant.MessageType;
import com.lld.notificationsystem.model.Template;
import com.lld.notificationsystem.repository.TemplateRepository;
import com.lld.notificationsystem.service.MessageTemplateService;

public class MessageTemplateServiceImpl implements MessageTemplateService {
    private TemplateRepository templateRepository;

    public MessageTemplateServiceImpl() {
        this.templateRepository = new TemplateRepository();
    }

    @Override
    public Template createTemplate(MessageType messageType, String template) {
        return templateRepository.create(messageType, template);
    }

    @Override
    public Template getTemplate(String templateId) {
        return this.templateRepository.get(templateId);
    }
}
