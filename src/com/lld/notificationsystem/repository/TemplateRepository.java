package com.lld.notificationsystem.repository;

import com.lld.notificationsystem.constant.MessageType;
import com.lld.notificationsystem.model.Template;

import java.util.HashMap;
import java.util.Map;

public class TemplateRepository {
    private Map<String, Template> templates;

    public TemplateRepository() {
        this.templates = new HashMap<>();
    }

    public Template create(MessageType messageType, String templateStr) {
        Template template = new Template(messageType, templateStr);
        this.templates.put(template.getId(), template);
        return template;
    }

    public Template get(String id) {
        return templates.get(id);
    }
}
