package com.lld.googlecalendar.model;

import com.lld.googlecalendar.service.visitor.EventVisitor;

public abstract class Participant {
    public Participant(String name, String email, Organization org) {
        this.name = name;
        this.email = email;
        this.organization = org;
    }
    protected String name;
    protected String email;
    protected Organization organization;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return this.email;
    }

    public Organization getOrganization() {
        return organization;
    }

    public abstract void updateEvent(Event event, EventVisitor eventVisitor);

    public abstract void deleteEvent(Event event, EventVisitor eventVisitor);
}
