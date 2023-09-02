package com.lld.googlecalendar.service;

import com.lld.googlecalendar.model.Event;
import com.lld.googlecalendar.model.Member;

import java.util.List;

public interface EventService {
    List<Event> events(Member member, Long start, Long end);
    Event createEvent(Event event);
    void updateEvent(Event event);
    void deleteEvent(Event event);

    void acceptEvent(Event event, Member member);
    void rejectEvent(Event event, Member member);
}
