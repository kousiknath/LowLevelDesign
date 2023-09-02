package com.lld.googlecalendar.service.visitor.impl;

import com.lld.googlecalendar.model.DLList;
import com.lld.googlecalendar.model.Event;
import com.lld.googlecalendar.model.Member;
import com.lld.googlecalendar.repository.EventRepository;
import com.lld.googlecalendar.service.visitor.EventVisitor;

public class EventVisitorImpl implements EventVisitor {
    private EventRepository eventRepository;

    public EventVisitorImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void updateEvent(Member member, Event event) {
        this.eventRepository.updateEvent(member, event);
    }

    @Override
    public void updateEvent(DLList dlList, Event event) {
        this.eventRepository.updateEvent(dlList, event);
    }

    @Override
    public void deleteEvent(Member member, Event event) {
        this.eventRepository.deleteEvent(member, event);
    }

    @Override
    public void deleteEvent(DLList dlList, Event event) {
        this.eventRepository.deleteEvent(dlList, event);
    }
}
