package com.lld.googlecalendar.model;

import com.lld.googlecalendar.service.visitor.EventVisitor;

public class Member extends Participant {
    public Member(String name, String email, Organization org) {
        super(name, email, org);
    }

    @Override
    public void updateEvent(Event event, EventVisitor eventVisitor) {
        eventVisitor.updateEvent(this, event);
    }

    @Override
    public void deleteEvent(Event event, EventVisitor eventVisitor) {
        eventVisitor.deleteEvent(this, event);
    }
}
