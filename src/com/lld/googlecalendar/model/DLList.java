package com.lld.googlecalendar.model;

import com.lld.googlecalendar.service.visitor.EventVisitor;

import java.util.ArrayList;
import java.util.List;

public class DLList extends Participant {
    private List<Member> members;

    public DLList(String name, String email, Organization org) {
        super(name, email, org);
        this.members = new ArrayList<>();
    }

    public List<Member> getMembers() {
        return this.members;
    }

    public void addMember(Member member) {
        this.members.add(member);
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
